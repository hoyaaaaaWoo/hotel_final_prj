package kr.co.mvc.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.mvc.user.service.MainService;

@Controller
public class MainController {
	
	@Autowired
	private MainService mainService;

	@RequestMapping(value="user/user_main/Hotel_Ritz_Seoul.do", method=RequestMethod.GET)
	public String main(Model model) {
		model.addAttribute("mainRooms", mainService.searchMainRooms());
		
		return "user/user_main/Hotel_Ritz_Seoul";
	}//main
	
	
	
}//class
