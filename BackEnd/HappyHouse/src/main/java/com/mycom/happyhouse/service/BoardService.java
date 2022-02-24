package com.mycom.happyhouse.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.happyhouse.dto.BoardDto;
import com.mycom.happyhouse.dto.BoardParamDto;
import com.mycom.happyhouse.dto.BoardResultDto;

public interface BoardService {
	//상세
	public BoardResultDto boardDetail(BoardParamDto boardParamDto);
	
	//수정
	public BoardResultDto boardUpdate(BoardDto dto, MultipartHttpServletRequest request);
	
	//삭제
	public BoardResultDto boardDelete(int boardId);
	
	//등록
	public BoardResultDto boardInsert(BoardDto dto, MultipartHttpServletRequest request);
	
	//목록
	public BoardResultDto boardList(BoardParamDto boardParamDto);
	
	//검색
	public BoardResultDto boardListSearchWord(BoardParamDto boardParamDto);
}
