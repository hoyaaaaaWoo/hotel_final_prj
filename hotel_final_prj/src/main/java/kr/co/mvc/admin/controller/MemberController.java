package kr.co.mvc.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.mvc.admin.service.MemberService;

@Controller
public class MemberController {

	private MemberService mSev;
@RequestMapping(value="search_member.do")
public String searchMember(String id, Model model) {
	return "";
}
@RequestMapping(value="search_deleteMember.do")
public String searchDeleteMember(String id,Model model) {
	return"";
}
@RequestMapping(value="change_member.do")
public String changeMember(String id,Model model) {
	return"";
}
}//class

