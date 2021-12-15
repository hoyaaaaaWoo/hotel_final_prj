package kr.co.mvc.admin.vo;

import java.util.Arrays;

public class ImgFormVO {
	
	private String mainImg;
	private String[] otherImgs;
	
	
	
	@Override
	public String toString() {
		return "ImgFormVO [mainImg=" + mainImg + ", otherImgs=" + Arrays.toString(otherImgs) + "]";
	}
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
