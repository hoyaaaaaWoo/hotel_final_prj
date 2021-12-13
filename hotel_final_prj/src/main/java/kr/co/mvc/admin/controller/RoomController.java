package kr.co.mvc.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.mvc.admin.dao.RoomDAO;
import kr.co.mvc.admin.service.ImgUploadService;
import kr.co.mvc.admin.service.RoomService;
import kr.co.mvc.admin.vo.ImgFormVO;
import kr.co.mvc.admin.vo.OtherImgVO;
import kr.co.mvc.admin.vo.RoomVO;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;
import java.util.List;

@Controller
public class RoomController {

	@Autowired
	private RoomService roomSev;
	
	@Autowired
	private ImgUploadService imgSev;

	/**
	 * ���ǰ��� ����
	 * @param rName
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "search_room.do", method = {GET,POST})
	public String searchRoomInfo(String rName, Model model) {
		// ������ ��û �� temp ���� ����
	    if(imgSev.searchImgList() != null && imgSev.searchImgList().size() != 0) {
	    	imgSev.removeTempImg(null);
	   	}//end if
		
	    model.addAttribute("rName", rName);
	    
	    //���Ǹ��� ��ܺο� ��Ÿ�� ��ü ���� ��ȸ
	    model.addAttribute("roomList", roomSev.searchRoomInfo(null));
	    
	    //Ư������ ��ȸ
	    if(rName != null || !("".equals(rName))) {
	    	model.addAttribute("rmVO", roomSev.searchRoomInfo(rName));
	    	model.addAttribute("imgList", roomSev.searchOtherImg(rName));
	    }//end if

	    return "admin/admin_room/admin_room_main";
	}// searchRoomInfo
	
	/**
	 * �����߰� ����
	 * @return
	 */
	@RequestMapping(value = "add_room_form.do", method = {GET,POST})
	public String addRoomForm() {
		//������ ��û �� temp ���� ����
		if(imgSev.searchImgList() != null && imgSev.searchImgList().size() != 0) {
			imgSev.removeTempImg(null);
		}//end if
		
		return "admin/admin_room/admin_room_add";
	}//addRoomForm
	
	
	/**
	 * �����߰� process
	 * @param rmVO
	 * @param imgFrmVO
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "add_room_process.do", method = POST)
	public String addRoomProcess(RoomVO rmVO, ImgFormVO imgFrmVO, Model model) {
		//default return page : �����߰�������
		String returnPage="forward:add_room_form.do";
		List<RoomVO> dupList = roomSev.searchRoomInfo(rmVO.getRoomName());

		if(dupList != null) {
			if(!dupList.isEmpty()) {
				model.addAttribute("dupRName", true);
				model.addAttribute("rmVO", rmVO);
			}//end if
		}//end if
		
		//�ߺ� ���Ǹ��� ����� insert ����
		if(dupList == null || dupList.isEmpty()) {
			int lastRoomNo = roomSev.searchLastRoomNo();
			returnPage="forward:search_room.do";
			if((roomSev.addRoom(rmVO, lastRoomNo, imgFrmVO))&&(roomSev.addOtherImg(rmVO, imgFrmVO))) {
					imgSev.moveImgtoRoomImg();
					imgSev.removeTempImg(null);
					model.addAttribute("insertResult", true);
			} else {
				model.addAttribute("insertResult", false);
			}//end else
		}//end else
		return returnPage;
	}//addRoomProcess
	
	/**
	 * �����߰� ����
	 * @return
	 */
	@RequestMapping(value = "change_room_form.do", method = {GET,POST})
	public String changeRoomForm(String selectedRName, ImgFormVO imgFrmVO, Model model) {
		// ������ ��û �� temp ���� ����
		if(imgSev.searchImgList() != null && imgSev.searchImgList().size() != 0) {
			imgSev.removeTempImg(null);
		}//end if

		model.addAttribute("selectedRName",selectedRName);
		//��ȸ�� ��������
		model.addAttribute("rList", roomSev.searchRoomInfo(selectedRName));
		//��ȸ�� ��Ÿ �̹��� ����Ʈ
		model.addAttribute("otherImgList", imgFrmVO);
		
		//imgSev.moveImgtoTemp(imgFrmVO);
		
		return "admin/admin_room/admin_room_change";
	}//addRoomForm
	
}//class

