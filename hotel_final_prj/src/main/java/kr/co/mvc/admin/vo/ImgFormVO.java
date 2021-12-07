package kr.co.mvc.admin.vo;

import org.springframework.stereotype.Component;

@Component
public class ImgFormVO {
	
	private String mainImg;
	private String[] otherFile;
	
	public String getMainImg() {
		return mainImg;
	}
	public void setMainImg(String mainImg) {
		this.mainImg = mainImg;
	}
	public String[] getOtherFile() {
		return otherFile;
	}
	public void setOtherFile(String[] otherFile) {
		this.otherFile = otherFile;
	}
	
}
