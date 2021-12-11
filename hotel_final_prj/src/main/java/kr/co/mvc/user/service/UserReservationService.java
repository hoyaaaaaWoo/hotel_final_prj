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
	 * �� ���� ��ȸ(intro�� ����)
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
	 * �� ��Ÿ�̹��� ��ȸ(intro�� ����)
	 * @param rName
	 * @return
	 */
	public List<List<OtherImgVO>> searchOtherImgs(String rName, String rStatus){
		List<RoomVO> rList = null;
		rList = resDAO.selectRoomInfo(rName, rStatus);
		
		List<List<OtherImgVO>> imgVOList = new ArrayList<List<OtherImgVO>>();
		List<OtherImgVO> list = null; //���Ǻ� ��ȸ�� �� 
		for(RoomVO rVO : rList){
			list = new ArrayList<OtherImgVO>();
			list = (resDAO.selectOtherImg(rVO.getRoomName()));
			imgVOList.add(list);
		}//end for
		
		return imgVOList;
	}//searchOtherImgs
	
	
	/**
	 * ���Ǵ� �̹��� ����(intro�� ����)
	 * @param rName
	 * @param rStatus
	 * @return
	 */
	public List<Integer> roomCnt(String rName, String rStatus){
		List<RoomVO> rList = null;
		rList = resDAO.selectRoomInfo(rName, rStatus);
		List<OtherImgVO> list = null; //���Ǻ� ��ȸ�� �� 
		List<Integer> cnt = new ArrayList<Integer>(); //���Ǵ� �̹��� ����
		
		for(RoomVO rVO : rList){
			list = new ArrayList<OtherImgVO>();
			list = (resDAO.selectOtherImg(rVO.getRoomName()));
			cnt.add(Integer.valueOf(list.size()));
			
		}//end for
		
		return cnt;
	}//roomCnt
	
}//class
