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
	 * @param rName
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "search_room.do", method = GET)
	public String searchRoomInfo(String rName, Model model) {
		// ������ ��û �� temp ���� �����ϰ� ����
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
	
	@RequestMapping(value = "add_room_form.do", method = GET)
	public String addRoomForm() {
		// ������ ��û �� temp ���� �����ϰ� return
		if(imgSev.searchImgList() != null && imgSev.searchImgList().size() != 0) {
			imgSev.removeTempImg(null);
		}//end if
		
		return "admin/admin_room/admin_room_add";
	}
	
	
}//class
