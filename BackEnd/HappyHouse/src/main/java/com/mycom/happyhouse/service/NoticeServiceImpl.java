package com.mycom.happyhouse.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.happyhouse.dao.NoticeDao;
import com.mycom.happyhouse.dto.NoticeDto;
import com.mycom.happyhouse.dto.NoticeFileDto;
import com.mycom.happyhouse.dto.NoticeParamDto;
import com.mycom.happyhouse.dto.NoticeResultDto;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeDao dao;
	String uploadFolder = "upload";
	
	String uploadPath = "D:" + File.separator + "SSAFY" + File.separator + "SpringBoot"
			+ File.separator + "HappyHouse" 
			+ File.separator + "src" 
			+ File.separator + "main"
			+ File.separator + "resources"
			+ File.separator + "static";

	/* 업로드 후 upload 폴더 refresh 하거나 preferences / workspace - refresh... 2개 option check */
	private static final int SUCCESS = 2;
	private static final int FAIL = -1;
	
	@Override
	@Transactional
	public NoticeResultDto noticeInsert(NoticeDto dto, MultipartHttpServletRequest request) {

		NoticeResultDto noticeResultDto = new NoticeResultDto();
		
		try {
			dao.noticeInsert(dto);
			
			List<MultipartFile> fileList = request.getFiles("file");
			
			File uploadDir = new File(uploadPath + File.separator + uploadFolder);
			if (!uploadDir.exists()) uploadDir.mkdir();

			for (MultipartFile part : fileList) {

				int noticeSeq = dto.getNoticeSeq();
				
				String fileName = part.getOriginalFilename();
				
				//Random File Id
				UUID uuid = UUID.randomUUID();
				
				//file extension
				String extension = FilenameUtils.getExtension(fileName); // vs FilenameUtils.getBaseName()
			
				String savingFileName = uuid + "." + extension;
			
				File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
				
				System.out.println(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
				part.transferTo(destFile);
		    
			    // Table Insert
			    NoticeFileDto noticeFileDto = new NoticeFileDto();
			    noticeFileDto.setNoticeSeq(noticeSeq);
			    noticeFileDto.setFileName(fileName);
			    noticeFileDto.setFileSize(part.getSize());
			    noticeFileDto.setFileContentType(part.getContentType());
				String noticeFileUrl = uploadFolder + "/" + savingFileName;
				noticeFileDto.setFileUrl(noticeFileUrl);
				
				dao.noticeFileInsert(noticeFileDto);
			}

			noticeResultDto.setResult(SUCCESS);
			
		}catch(IOException e) {
			e.printStackTrace();
			noticeResultDto.setResult(FAIL);
		}
		return noticeResultDto;
		}
	

	@Override
    @Transactional
    public NoticeResultDto noticeUpdate(NoticeDto dto, MultipartHttpServletRequest request) {
            
        NoticeResultDto noticeResultDto = new NoticeResultDto();
        
        try {
            dao.noticeUpdate(dto);
            
            List<MultipartFile> fileList = request.getFiles("file");
            
            File uploadDir = new File(uploadPath + File.separator + uploadFolder);
            if (!uploadDir.exists()) uploadDir.mkdir();
            
            List<String> fileUrlList = dao.noticeFileUrlDeleteList(dto.getNoticeSeq());    
            for(String fileUrl : fileUrlList) {    
                File file = new File(uploadPath + File.separator, fileUrl);
                if(file.exists()) {
                    file.delete();
                }
            }
            
            dao.noticeFileDelete(dto.getNoticeSeq());
            
            for (MultipartFile part : fileList) {

                int noticeSeq = dto.getNoticeSeq();
                
                String fileName = part.getOriginalFilename();
                
                //Random File Id
                UUID uuid = UUID.randomUUID();
                
                //file extension
                String extension = FilenameUtils.getExtension(fileName); // vs FilenameUtils.getBaseName()
            
                String savingFileName = uuid + "." + extension;
            
                File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
                
                System.out.println(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
                part.transferTo(destFile);
            
                // Table Insert
                NoticeFileDto noticeFileDto = new NoticeFileDto();
                noticeFileDto.setNoticeSeq(noticeSeq);
                noticeFileDto.setFileName(fileName);
                noticeFileDto.setFileSize(part.getSize());
                noticeFileDto.setFileContentType(part.getContentType());
                String noticeFileUrl = uploadFolder + "/" + savingFileName;
                noticeFileDto.setFileUrl(noticeFileUrl);
                
                dao.noticeFileInsert(noticeFileDto);
            }

            noticeResultDto.setResult(SUCCESS);
            
        }catch(IOException e) {
            e.printStackTrace();
            noticeResultDto.setResult(FAIL);
        }
        return noticeResultDto;
    }

	@Override
	public NoticeResultDto noticeDelete(int noticeSeq) {
		NoticeResultDto noticeResultDto = new NoticeResultDto();
		
		try {
			List<String> fileUrlList = dao.noticeFileUrlDeleteList(noticeSeq);
			for(String fileUrl : fileUrlList) {
				File file = new File(uploadPath + File.separator, fileUrl);				
				if(file.exists()) {
					file.delete();
				}
			}
			
			dao.noticeFileDelete(noticeSeq);
			dao.noticeReadCountDelete(noticeSeq);
			dao.noticeDelete(noticeSeq);		
			noticeResultDto.setResult(SUCCESS);
			
		}catch(Exception e) {
			e.printStackTrace();
			noticeResultDto.setResult(FAIL);
		}
		
		return noticeResultDto;
	}

	@Override
	public NoticeResultDto noticeList(NoticeParamDto noticeParamDto) {

		NoticeResultDto noticeResultDto = new NoticeResultDto();
		
		try {
			List<NoticeDto> list = dao.noticeList(noticeParamDto);
			list.forEach( el -> System.out.println(el) );
			int count = dao.noticeListTotalCount();
			noticeResultDto.setList(list);
			noticeResultDto.setCount(count);
			noticeResultDto.setResult(SUCCESS)	;
			
		}catch(Exception e) {
			e.printStackTrace();
			noticeResultDto.setResult(FAIL);
		}
		
		return noticeResultDto;
	}

	@Override
	@Transactional
	public NoticeResultDto noticeDetail(NoticeParamDto noticeParamDto) {
		NoticeResultDto noticeResultDto = new NoticeResultDto();
		
		try {
			int userReadCnt = dao.noticeUserReadCount(noticeParamDto);
			if( userReadCnt == 0 ) {
				dao.noticeUserReadInsert(noticeParamDto.getNoticeSeq(), noticeParamDto.getUserSeq());
				dao.noticeReadCountUpdate(noticeParamDto.getNoticeSeq());
			}
			
			NoticeDto dto = dao.noticeDetail(noticeParamDto);
			List<NoticeFileDto> fileList = dao.noticeDetailFileList(dto.getNoticeSeq());

			dto.setFileList(fileList);
			noticeResultDto.setDto(dto);
			
			noticeResultDto.setResult(SUCCESS);
			
		}catch(Exception e) {
			e.printStackTrace();
			noticeResultDto.setResult(FAIL);
		}
		
		return noticeResultDto;
	}

	@Override
	public NoticeResultDto noticeListSearchWord(NoticeParamDto noticeParamDto) {
		NoticeResultDto noticeResultDto = new NoticeResultDto();
		
		try {
			List<NoticeDto> list = dao.noticeListSearchWord(noticeParamDto);
			int count = dao.noticeListSearchWordTotalCount(noticeParamDto);
			
			noticeResultDto.setList(list);
			noticeResultDto.setCount(count);
			
			noticeResultDto.setResult(SUCCESS);
		
		}catch(Exception e) {
			e.printStackTrace();
			noticeResultDto.setResult(FAIL);
		}
		
		return noticeResultDto;
	}
	
}
