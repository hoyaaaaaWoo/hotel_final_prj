package kr.co.mvc.admin.vo;

import org.springframework.stereotype.Component;

/**
 * ���� ���� �̹��� �� ��Ÿ �̹����� ������ VO
 * @author user
 */

@Component
public class OtherImgVO {
	
	private String imgSrc;
	private int imgNo, roomNo;


	public String getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

	public int getImgNo() {
		return imgNo;
	}

	public void setImgNo(int imgNo) {
		this.imgNo = imgNo;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}


}
