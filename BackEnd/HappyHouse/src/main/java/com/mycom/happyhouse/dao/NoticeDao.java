package com.mycom.happyhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycom.happyhouse.dto.NoticeDto;
import com.mycom.happyhouse.dto.NoticeFileDto;
import com.mycom.happyhouse.dto.NoticeParamDto;

@Mapper
public interface NoticeDao {
	// 공지사항 등록
	public int noticeInsert(NoticeDto noticeDto);
	// 공지사항 파일 등록
	public int noticeFileInsert(NoticeFileDto noticeFileDto);
	
	// 공지사항 수정
	public int noticeUpdate(NoticeDto noticeDto);
	
	// 공지사항 삭제
	public int noticeDelete(int noticeSeq);
	//공지사항 파일 삭제
	public int noticeFileDelete(int noticeSeq);
	public List<String> noticeFileUrlDeleteList(int noticeSeq);
	
	
	// 공지사항 10개
	public List<NoticeDto> noticeList(NoticeParamDto noticeParamDto);
	public int noticeListTotalCount();
	
	// 공지사항 상세보기
	public NoticeDto noticeDetail(NoticeParamDto noticeParamDto);
	public List<NoticeFileDto> noticeDetailFileList(int noticeId);

	
	//공지사항 검색
	public List<NoticeDto> noticeListSearchWord(NoticeParamDto noticeParamDto);
	public int noticeListSearchWordTotalCount(NoticeParamDto noticeParamDto);
	
//------------------------부가 기능----------------------//
	
	//게시물 열람 횟수 구하기
	public int noticeUserReadCount(NoticeParamDto noticeParamDto);
	
	//
	public int noticeUserReadInsert(
			@Param("noticeSeq") int noticeSeq,
			@Param("userSeq") int userSeq);
			
	//열람 횟수 갱신
	public int noticeReadCountUpdate(int noticeSeq);
	
	//열람 횟수 초기화
	public int noticeReadCountDelete(int noticeSeq);

}
