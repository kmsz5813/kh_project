package com.myweb.home.upload.model;

public class FileUploadDTO {
	
	private int id;
	private int bId;
	private String fileName;
	private String uuidName;
	private String location;
	private String url;
	private int fileSize;
	private String contentType;
	
	public FileUploadDTO() {}
	
	public FileUploadDTO(int bId, String location, String url) {
		this.bId = bId;
		this.location = location;
		this.url = url;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
		return "FileUploadDTO [id=" + id + ", bId=" + bId + ", fileName=" + fileName + ", uuidName=" + uuidName
				+ ", location=" + location + ", url=" + url + ", fileSize=" + fileSize + ", contentType=" + contentType
				+ "]";
	}

	public int getbId() {
		return bId;
	}

	public void setbId(int bId) {
		this.bId = bId;
	}
}

