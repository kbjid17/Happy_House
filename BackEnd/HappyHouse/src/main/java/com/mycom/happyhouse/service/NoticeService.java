package com.mycom.happyhouse.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.happyhouse.dto.NoticeDto;
import com.mycom.happyhouse.dto.NoticeParamDto;
import com.mycom.happyhouse.dto.NoticeResultDto;

public interface NoticeService {
	// 공지사항 등록
	public NoticeResultDto noticeInsert(NoticeDto noticeDto, MultipartHttpServletRequest request);
	
	// 공지사항 수정
	public NoticeResultDto noticeUpdate(NoticeDto noticeDto, MultipartHttpServletRequest request);
	
	// 공지사항 삭제
	public NoticeResultDto noticeDelete(int noticeSeq);
	
	// 공지사항 10개
	public NoticeResultDto noticeList(NoticeParamDto noticeParamDto);
	
	// 공지사항 상세보기
	public NoticeResultDto noticeDetail(NoticeParamDto noticeParamDto);
	
	public NoticeResultDto noticeListSearchWord(NoticeParamDto noticeParamDto);
}
