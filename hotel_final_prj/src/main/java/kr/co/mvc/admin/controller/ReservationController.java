package kr.co.mvc.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class ReservationController {

	@RequestMapping(value="test.do", method=GET)
	public String searchTodayRes() {
		return "test";
	}//
	
}//class
