package kr.co.mvc.admin.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.mvc.admin.service.MemberService;
import kr.co.mvc.admin.service.PagenationService;
import kr.co.mvc.admin.vo.MemberPagingVO;

@Controller
public class MemberController {
	@Autowired
	private MemberService memSev;
	
	@Autowired
	private PagenationService pageSev;

	/**
	 * ȸ������ ���� (����ȸ�� ��ȸ)
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "search_active_member.do", method = { GET, POST })
	public String searchActiveMember(MemberPagingVO mpVO, Model model) {
		String id = mpVO.getId();
		String page = mpVO.getPage();
		if (id != null && !"".equals(id)) {
			model.addAttribute("id", id);
		} // end if

		// ��û �� page�� ������ 1page�� setting
		if (page == null || "".equals(page)) {
			page = "1";
		} // end if

		int currentPage = pageSev.setCurrentpage(page);

		// ���������̼� �۾�
		pageSev.setPageScale(10);
		model.addAttribute("totalPage", pageSev.getTotalPage(memSev.searchAllMemberCnt(id, "Y")));
		model.addAttribute("currentPage", currentPage);

		// ��û ������ �ѹ��� ���� ��ȸ ���� ���ϱ�
		int startNum = pageSev.getStartNum(currentPage);
		int endNum = pageSev.getEndNum(startNum);

		model.addAttribute("memberList", memSev.searchActiveMember(id, startNum, endNum));
		
		return "/admin/admin_member/admin_member_main_active";
	}// searchActiveMember

	/**
	 * Ż��ȸ�� ��ȸ
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "search_inactive_member.do", method = { GET, POST })
	public String searchInactiveMember(MemberPagingVO mpVO, Model model) {
		String id = mpVO.getId();
		String page = mpVO.getPage();
		if (id != null && !"".equals(id)) {
			model.addAttribute("id", id);
		} // end if

		// ��û �� page�� ������ 1page�� setting
		if (page == null || "".equals(page)) {
			page = "1";
		} // end if

		int currentPage = pageSev.setCurrentpage(page);

		// ���������̼� �۾�
		pageSev.setPageScale(10);
		model.addAttribute("totalPage", pageSev.getTotalPage(memSev.searchAllMemberCnt(id, "N")));
		model.addAttribute("currentPage", currentPage);

		// ��û ������ �ѹ��� ���� ��ȸ ���� ���ϱ�
		int startNum = pageSev.getStartNum(currentPage);
		int endNum = pageSev.getEndNum(startNum);

		model.addAttribute("memberList", memSev.searchInactiveMember(id,startNum, endNum));
		return "/admin/admin_member/admin_member_sub_inactive";
	}// searchActiveMember

	
	
	@RequestMapping(value = "change_member.do", method = POST)
	public String changeMember(String id, Model model) {
		return "";
	}
}// class
