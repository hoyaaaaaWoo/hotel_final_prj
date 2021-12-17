package kr.co.mvc.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.mvc.user.service.UserLoginService;
import kr.co.mvc.user.vo.UserLoginVO;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;


@Controller
public class UserLoginController {

	@Autowired(required=false)
	private UserLoginService uLogService;
	
	/**
	 * 사용자 로그인 화면
	 * @return
	 */
	@RequestMapping(value = "user/user_login/user_login.do", method = {GET,POST})
	public String UserLoginForm() {
		return "/user/user_login/user_login";
	}//UserLoginForm
	
	
	@RequestMapping(value = "user/user_login/user_login_process.do", method = POST)
	public String UserLoginProcess(UserLoginVO uVO, Model model) {
		
		return "/user/user_login/user_login_result";
	}//UserLoginProcess
	
	
	
}//
