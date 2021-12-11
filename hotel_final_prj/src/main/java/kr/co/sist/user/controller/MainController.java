package kr.co.sist.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@RequestMapping(value="user/user_main/test.do", method=RequestMethod.GET)
	public String main() {
		return "user/user_main/test";
	}//main
	
}//class
