package kr.co.mvc.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.mvc.admin.vo.OtherImgVO;
import kr.co.mvc.admin.vo.RoomVO;
import kr.co.mvc.user.dao.UserReservationDAO;

@Component
public class UserReservationService {

	@Autowired
	private UserReservationDAO resDAO;
	
	/**
	 * 전 객실 조회(intro에 쓰임)
	 * @param rName
	 * @param rStatus
	 * @return
	 */
	public List<RoomVO> searchAllRooms(String rName, String rStatus){
		List<RoomVO> rList = null;

		rList = resDAO.selectRoomInfo(rName, rStatus);
		
		return rList;
	}//searchAllRooms
	
	/**
	 * 룸 기타이미지 조회(intro에 쓰임)
	 * @param rName
	 * @return
	 */
	public List<List<OtherImgVO>> searchOtherImgs(String rName, String rStatus){
		List<RoomVO> rList = null;
		rList = resDAO.selectRoomInfo(rName, rStatus);
		
		List<List<OtherImgVO>> imgVOList = new ArrayList<List<OtherImgVO>>();
		List<OtherImgVO> list = null; //객실별 조회할 거 
		for(RoomVO rVO : rList){
			list = new ArrayList<OtherImgVO>();
			list = (resDAO.selectOtherImg(rVO.getRoomName()));
			imgVOList.add(list);
		}//end for
		
		return imgVOList;
	}//searchOtherImgs
	
	
	/**
	 * 객실당 이미지 개수(intro에 쓰임)
	 * @param rName
	 * @param rStatus
	 * @return
	 */
	public List<Integer> roomCnt(String rName, String rStatus){
		List<RoomVO> rList = null;
		rList = resDAO.selectRoomInfo(rName, rStatus);
		List<OtherImgVO> list = null; //객실별 조회할 거 
		List<Integer> cnt = new ArrayList<Integer>(); //객실당 이미지 개수
		
		for(RoomVO rVO : rList){
			list = new ArrayList<OtherImgVO>();
			list = (resDAO.selectOtherImg(rVO.getRoomName()));
			cnt.add(Integer.valueOf(list.size()));
			
		}//end for
		
		return cnt;
	}//roomCnt
	
}//class
