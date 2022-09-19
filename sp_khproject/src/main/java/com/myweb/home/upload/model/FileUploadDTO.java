package com.myweb.home.upload.model;

public class FileUploadDTO {
	
	private int file_id;
	private int file_bId;
	private String fileName;
	private String uuidName;
	private String location;
	private String url;
	private int fileSize;
	private String contentType;
	
	public FileUploadDTO() {}
	
	public FileUploadDTO(int file_bId, String location, String url) {
		this.file_bId = file_bId;
		this.location = location;
		this.url = url;
	}
	
	public int getFile_Id() {
		return file_id;
	}
	
	public void setFile_Id(int file_id) {
		this.file_id = file_id;
	}
	
	public int getFile_bId() {
		return file_bId;
	}
	
	public void setFile_bId(int file_bId) {
		this.file_bId = file_bId;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getUuidName() {
		return uuidName;
	}

	public void setUuidName(String uuidName) {
		this.uuidName = uuidName;
	}

	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public int getFileSize() {
		return fileSize;
	}
	
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	
	public String getContentType() {
		return contentType;
	}
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	@Override
	public String toString() {
		return "FileUploadDTO [file_id=" + file_id + ", file_bId=" + file_bId + ", fileName=" + fileName + ", uuidName=" + uuidName
				+ ", location=" + location + ", url=" + url + ", fileSize=" + fileSize + ", contentType=" + contentType
				+ "]";
	}

}

