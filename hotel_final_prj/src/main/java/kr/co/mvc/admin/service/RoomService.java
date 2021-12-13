package kr.co.mvc.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import kr.co.mvc.admin.dao.RoomDAO;
import kr.co.mvc.admin.vo.ImgFormVO;
import kr.co.mvc.admin.vo.OtherImgVO;
import kr.co.mvc.admin.vo.RoomVO;

@Component
public class RoomService {

	@Autowired
	private RoomDAO roomDAO;
	
	/**
	 * ��ϵ� ��� �� ������ ��ȸ
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
	 * Ư�������� ��Ÿ�̹��� ��ȸ
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
	
	
	/**
	 * ���� �߰� ��, ���� ����ȣ�� RoomNo�� ��ȸ�Ͽ� ���
	 * @return
	 */
	public int searchLastRoomNo(){
		int num=0;
		try {
			num = roomDAO.selectLastRoomNo();
		}catch(DataAccessException dae) {
			dae.printStackTrace();
		}//end catch
		return num;
	}//searchLastRoomNo
	
	
	/**
	 * ���� �߰�
	 * @param rmVO
	 * @param lastRoomNo
	 * @param imgFrmVO
	 * @return
	 */
	public boolean addRoom(RoomVO rmVO, int lastRoomNo, ImgFormVO imgFrmVO) {
		boolean flag = false;
		try {
			flag = roomDAO.insertRoom(rmVO, lastRoomNo, imgFrmVO);
		}catch(DataAccessException dae) {
			dae.printStackTrace();
		}//end catch
		return flag;
	}//addRoom 
	
	
	/**
	 * ��Ÿ�̹��� ���� ��, �̹��� �߰�
	 * @param rmVO
	 * @param imgFrmVO
	 * @return
	 */
	public boolean addOtherImg(RoomVO rmVO, ImgFormVO imgFrmVO) {
		boolean flag = false;
		try {
			flag = roomDAO.insertOtherImg(rmVO, imgFrmVO);
		}catch(DataAccessException dae) {
			dae.printStackTrace();
		}//end catch
		return flag;
	}//addOtherImg
	

	/**
	 * ���� ���� ��, �ߺ� ���Ǹ� ��ȸ
	 * @param rmVO
	 * @return
	 */
	public List<String> searchDupRoomName(RoomVO rmVO) {
		List<String> list = null;
		try {
			list = roomDAO.selectDupRoomName(rmVO);
		}catch(DataAccessException dae) {
			dae.printStackTrace();
		}//end catch
		return list;
	}//searchDupRoomName
	
}// class

