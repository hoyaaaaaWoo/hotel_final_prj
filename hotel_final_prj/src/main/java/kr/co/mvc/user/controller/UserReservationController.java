package kr.co.mvc.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.mvc.user.service.UserReservationService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class UserReservationController {

	@Autowired
	private UserReservationService resService;
	
	@RequestMapping(value="user/user_room/room_intro.do", method=GET)
	public String reserve_intro(Model model, String rName, String rStatus) {
		model.addAttribute("roomList", resService.searchAllRooms(null, "Y"));
		model.addAttribute("newLineChar", "\n");
		model.addAttribute("imgVOList", resService.searchOtherImgs(rName, rStatus));
		model.addAttribute("cnt", resService.roomCnt(rName, rStatus));
		
		return "user/user_room/room_intro";
	}//reserve_intro
	
	
}//class
