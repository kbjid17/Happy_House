package com.mycom.happyhouse.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.happyhouse.dto.BoardDto;
import com.mycom.happyhouse.dto.BoardParamDto;
import com.mycom.happyhouse.dto.BoardResultDto;
import com.mycom.happyhouse.dto.UserDto;
import com.mycom.happyhouse.service.BoardService;

@CrossOrigin(
		origins = "http://localhost:5500",
		allowCredentials = "true", //axios 사용 시 세션 아이디를 하나로 고정하기 위함(해당 고정을 허락하는 기능)
		allowedHeaders = "*",
		methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT,
				RequestMethod.DELETE,RequestMethod.HEAD,RequestMethod.OPTIONS})

@RestController
public class BoardController {

	@Autowired
	BoardService service;
	
	private static final int SUCCESS = 1;
	
//	@GetMapping(value="/board")
//	private String board() {
//		return "board/boardMain.html";
//	}
	
	
	@GetMapping(value="/boards")
	public ResponseEntity<BoardResultDto> boardList(BoardParamDto boardParamDto){
		
		BoardResultDto boardResultDto;
		
		System.out.println(boardParamDto);
		if( boardParamDto.getSearchWord().isEmpty() ) {
			
			boardResultDto = service.boardList(boardParamDto);
			
			boardResultDto.getList().forEach( el -> System.out.println(el) );
		}else {
			boardResultDto = service.boardListSearchWord(boardParamDto);
		}
		
		if( boardResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@GetMapping(value="/boards/{boardId}")
	public ResponseEntity<BoardResultDto> boardDetail(@PathVariable int boardId, HttpSession session){

		BoardParamDto boardParamDto = new BoardParamDto();
		boardParamDto.setBoardId(boardId);

		System.out.println(boardId);
		BoardResultDto boardResultDto = service.boardDetail(boardParamDto);
		// 게시글 작성자와 현 사용자가 동일
		if( ((UserDto) session.getAttribute("userDto")).getUserSeq() == boardResultDto.getDto().getUserSeq() ) {
			boardResultDto.setOwner(true);
		}				
				
		if( boardResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}		 
	}
	
	@PostMapping(value="/boards")
	public ResponseEntity<BoardResultDto> boardInsert(
			BoardDto boardDto, 
			MultipartHttpServletRequest request) {
		
		System.out.println(1234);
		boardDto.setUserSeq( ((UserDto) request.getSession().getAttribute("userDto")).getUserSeq());
		BoardResultDto boardResultDto = service.boardInsert(boardDto, request);
		
		if( boardResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}		 
	}
	
	// PUT + multipart/form-data (X)
	// In RESTful,
	// PUT & DELETE methods are defined to be idempotent
	
	@PostMapping(value="/boards/{boardId}") 
	public ResponseEntity<BoardResultDto> boardUpdate(
			BoardDto boardDto, 
			MultipartHttpServletRequest request){

		BoardResultDto boardResultDto = service.boardUpdate(boardDto, request);
		boardDto.setUserSeq( ((UserDto) request.getSession().getAttribute("userDto")).getUserSeq());
		
		if( boardResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@DeleteMapping(value="/boards/{boardId}") 
	public ResponseEntity<BoardResultDto> boardDelete(@PathVariable(value="boardId") int boardId){
		BoardResultDto boardResultDto = service.boardDelete(boardId);
		
		if( boardResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}		 
	}
}
