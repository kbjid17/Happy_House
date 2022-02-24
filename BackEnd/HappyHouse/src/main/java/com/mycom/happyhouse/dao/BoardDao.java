package com.mycom.happyhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycom.happyhouse.dto.BoardDto;
import com.mycom.happyhouse.dto.BoardFileDto;
import com.mycom.happyhouse.dto.BoardParamDto;

@Mapper
public interface BoardDao {
	//게시글 등록
	public int boardInsert(BoardDto dto);
	public int boardFileInsert(BoardFileDto dto);
		
	//게시글 수정
	public int boardUpdate(BoardDto dto);
	
	//게시글 삭제
	public int boardDelete(int boardId);	
	
	//게시글 파일 삭제
	public int boardFileDelete(int boardId);
	public List<String> boardFileUrlDeleteList(int boardId);
	
	public List<BoardDto> boardList(BoardParamDto boardParamDto);
	public int boardListTotalCount();
	
	public BoardDto boardDetail(BoardParamDto boardParamDto);
	public List<BoardFileDto> boardDetailFileList(int boardId);

	public List<BoardDto> boardListSearchWord(BoardParamDto boardParamDto);
	public int boardListSearchWordTotalCount(BoardParamDto boardParamDto);
	
	// map - Dto
	public int boardUserReadCount(BoardParamDto boardParamDto); 
	
	// map - @param
	public int boardUserReadInsert(
			@Param("boardId") int boardId, 
			@Param("userSeq") int userSeq ); 
	
	public int boardReadCountUpdate(int boardId);
	
	
	public int boardReadCountDelete(int boardId);


}
