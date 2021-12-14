package kr.co.mvc.admin.vo;

import org.springframework.stereotype.Component;

@Component
public class ImgFormVO {
	
	private String mainImg;
	private String[] otherImgs;
	
	public String getMainImg() {
		return mainImg;
	}
	public void setMainImg(String mainImg) {
		this.mainImg = mainImg;
	}
	public String[] getOtherImgs() {
		return otherImgs;
	}
	public void setOtherImgs(String[] otherImgs) {
		this.otherImgs = otherImgs;
	}

}
