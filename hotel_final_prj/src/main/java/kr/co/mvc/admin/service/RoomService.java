package kr.co.mvc.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import kr.co.mvc.admin.dao.RoomDAO;
import kr.co.mvc.admin.vo.OtherImgVO;
import kr.co.mvc.admin.vo.RoomVO;

@Component
public class RoomService {

	@Autowired
	private RoomDAO roomDAO;
	
	/**
	 * 등록된 모든 룸 상세정보 조회
	 * @param rName
	 * @return
	 */
	public List<RoomVO> searchRoomInfo(String rName) {
		List<RoomVO> roomList = null;
		try {
			roomList = roomDAO.selectRoomInfo(rName);
		}catch(DataAccessException dae) {
			dae.printStackTrace();
		}//end catch
		
		return roomList;
	}//searchRoomInfo
	
	
	/**
	 * 특정객실의 기타이미지 조회
	 * @param rName
	 * @return
	 */
	public List<OtherImgVO> searchOtherImg(String rName) {
		List<OtherImgVO> imgList = null;
		
		try {
			imgList = roomDAO.selectOtherImg(rName);
		}catch(DataAccessException dae) {
			dae.printStackTrace();
		}//end catch
		
		return imgList;
	}//searchOtherImg
	
}// class
