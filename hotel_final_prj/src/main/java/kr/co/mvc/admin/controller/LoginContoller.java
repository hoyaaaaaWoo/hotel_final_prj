package kr.co.mvc.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import kr.co.mvc.admin.service.LoginService;
import kr.co.mvc.admin.vo.ManagerVO;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;;
@Controller
@SessionAttributes("id")
public class LoginContoller {
	
	private LoginService lSev;
	
@RequestMapping(value="admin_login_form.do", method = POST)
public String moveAdminLoginForm() {
		return"";
	}//moveAdminLoginForm
@RequestMapping(value="admin_login.do")
public String adminLogin(ManagerVO mVO) {
	return"";
}
@RequestMapping(value="admin_logout.do")
public String adminLogOut(SessionStatus ss) {
	return "";
}
	
}//class
