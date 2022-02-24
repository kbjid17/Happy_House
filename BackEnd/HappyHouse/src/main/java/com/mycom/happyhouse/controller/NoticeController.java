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

import com.mycom.happyhouse.dto.NoticeDto;
import com.mycom.happyhouse.dto.NoticeParamDto;
import com.mycom.happyhouse.dto.NoticeResultDto;
import com.mycom.happyhouse.dto.UserDto;
import com.mycom.happyhouse.service.NoticeService;

@CrossOrigin(
		origins = "http://localhost:5500",
		allowCredentials = "true", //axios 사용 시 세션 아이디를 하나로 고정하기 위함(해당 고정을 허락하는 기능)
		allowedHeaders = "*",
		methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT,
				RequestMethod.DELETE,RequestMethod.HEAD,RequestMethod.OPTIONS})

@RestController
public class NoticeController {

	@Autowired
	private NoticeService service;
	
	private static final int SUCCESS = 2;
	
//	@GetMapping(value="/board")
//	private String board() {
//		return "board/boardMain.html";
//	}
	
	@GetMapping(value="/notices")
	public ResponseEntity<NoticeResultDto> noticeList(NoticeParamDto noticeParamDto){
		System.out.println(noticeParamDto);
		NoticeResultDto noticeResultDto;

		if( noticeParamDto.getSearchWord().isEmpty() ) {
			noticeResultDto = service.noticeList(noticeParamDto);
		}else {
			noticeResultDto = service.noticeListSearchWord(noticeParamDto);
		}
		
		if( noticeResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@GetMapping(value="/notices/{noticeSeq}")
	public ResponseEntity<NoticeResultDto> noticeDetail(@PathVariable int noticeSeq, HttpSession session){

		NoticeParamDto noticeParamDto = new NoticeParamDto();
		noticeParamDto.setNoticeSeq(noticeSeq);
		
		NoticeResultDto noticeResultDto = service.noticeDetail(noticeParamDto);
		
		System.out.println( "접속한 유저 : " + ((UserDto) session.getAttribute("userDto")).getUserSeq() );
		System.out.println( "글 작성한 유저 : " + noticeResultDto.getDto().getUserSeq() );
		
		
		// 게시글 작성자와 현 사용자가 동일
		if( ((UserDto) session.getAttribute("userDto")).getUserSeq() == noticeResultDto.getDto().getUserSeq() ) {
			noticeResultDto.setOwner(true);
		}				
				
		if( noticeResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}		 
	}
	
	@PostMapping(value="/notices")
	public ResponseEntity<NoticeResultDto> noticeInsert(
			NoticeDto noticeDto, 
			MultipartHttpServletRequest request) {
		NoticeResultDto noticeResultDto = null;
		
		noticeDto.setUserSeq( ((UserDto) request.getSession().getAttribute("userDto")).getUserSeq());
		noticeDto.setUserClsf( ((UserDto) request.getSession().getAttribute("userDto")).getuserClsf());

		
		// 관리자가 아니면 돌려보냄
		if( !noticeDto.getUserClsf().equals("0") ) 
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.FORBIDDEN); // 403
		
		noticeResultDto = service.noticeInsert(noticeDto, request);
		
		if( noticeResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}		 
	}
	
	// PUT + multipart/form-data (X)
	// In RESTful,
	// PUT & DELETE methods are defined to be idempotent
	
	@PostMapping(value="/notices/{noticeSeq}") 
	public ResponseEntity<NoticeResultDto> noticeUpdate(
			NoticeDto noticeDto, 
			MultipartHttpServletRequest request){
		NoticeResultDto noticeResultDto = null;
		
		UserDto user = (UserDto) request.getSession().getAttribute("userDto");
		
		System.out.println(user);
		
		// 관리자가 아니면 돌려보냄
		if( !(user.getuserClsf().equals("0")) ) 
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.FORBIDDEN); // 403
		
		noticeDto.setUserSeq( user.getUserSeq() );
		noticeResultDto = service.noticeUpdate(noticeDto, request);
		
		if( noticeResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@DeleteMapping(value="/notices/{noticeSeq}") 
	public ResponseEntity<NoticeResultDto> noticeDelete(@PathVariable(value="noticeSeq") int noticeSeq, HttpSession session){
		NoticeResultDto noticeResultDto = null;
		
		UserDto user = (UserDto) session.getAttribute("userDto");
		
		System.out.println(user);
		
		// 관리자가 아니면 돌려보냄
		if( !(user.getuserClsf().equals("0")) ) 
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.FORBIDDEN); // 403
		
		noticeResultDto = service.noticeDelete(noticeSeq);
		
		if( noticeResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}		 
	}
}
