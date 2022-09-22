package com.myweb.home.upload.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.myweb.home.upload.model.FileUploadDAO;
import com.myweb.home.upload.model.FileUploadDTO;


@Service
public class FileUploadService {
	
	@Autowired
	private FileUploadDAO dao;
	
	@Transactional
	public int upload(MultipartFile file, FileUploadDTO data) throws Exception {
		
		File folder = new File(data.getLocation());
		if(!folder.exists()) {
			folder.mkdirs();
		}
		
		UUID uuid = UUID.nameUUIDFromBytes(file.getBytes());
		
		data.setFileName(file.getOriginalFilename());
		data.setUuidName(uuid.toString());
		data.setFileSize((int)file.getSize());
		data.setContentType(file.getContentType());
		
		int count = dao.getCount(data.getFile_bId());
		
		if(count >= 5) {
			// 업로드 수량 초과.
			return -1;
		}
		
		boolean result = dao.insertData(data);
		if(result) {
			try {
				System.out.println("여기는실행되나????????");
				System.out.println(data.getLocation());
				System.out.println(data.getUuidName());
				file.transferTo(new File(data.getLocation() + File.separatorChar + data.getUuidName()));
				
				return 1;
			} catch (IOException e) {
				throw new Exception("서버에 파일 업로드를 실패하였습니다.");
			}
		} else {
			// 업로드 실패
			return 0;
		}
	}

	public List<FileUploadDTO> getDatas(int file_bId) {
		List<FileUploadDTO> datas = dao.selectDatas(file_bId);
		return datas;
	}
}
