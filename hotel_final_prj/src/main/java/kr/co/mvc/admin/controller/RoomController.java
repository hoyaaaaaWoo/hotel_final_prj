package kr.co.mvc.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.mvc.admin.service.ImgUploadService;
import kr.co.mvc.admin.service.RoomService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class RoomController {

	@Autowired
	private RoomService roomSev;
	
	@Autowired
	private ImgUploadService imgSev;

	/**
	 * ���ǰ��� ����
	 */
	@RequestMapping(value = "search_room.do", method = GET)
	public String removeRes(String rName, Model model) {
		System.out.println(rName);
		
		//���� ������ ��û �� temp ���� �����ϰ� ����
	    if(imgSev.searchImgList() != null && imgSev.searchImgList().size() != 0) {
	    	imgSev.removeTempImg(null);
	   	}//end if
		
	    model.addAttribute("rName", rName);
	    
	    //���Ǹ��� ��ܺο� ��Ÿ�� ��ü ���� ��ȸ
	    model.addAttribute("roomList", roomSev.searchRoomInfo(rName));
	    //Ư������ ��ȸ
	    if(rName != null || !("".equals(rName))) {
	    	model.addAttribute("rmVO", roomSev.searchRoomInfo(rName));
	    	model.addAttribute("imgList", roomSev.searchOtherImg(rName));
	    }//end if
	    
		return "admin_room_main";
	}// removeRes
	
	
}//class
