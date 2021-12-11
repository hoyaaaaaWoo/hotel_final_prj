package kr.co.mvc.user.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Component;

import kr.co.mvc.user.dao.MainDAO;
import kr.co.mvc.user.vo.MainRoomVO;
import kr.co.mvc.user.vo.RoomVO;

@Component
public class MainService {

	private MainDAO mainDAO;
	
	public List<MainRoomVO> searchMainRooms(){
		List<MainRoomVO> mrList = null;
		
		try {
			mrList = mainDAO.selectMainRoom();
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		return mrList;
		
	}//searchMainiRooms

}//class