package kr.co.mvc.admin.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.mvc.admin.service.ReservationService;
import kr.co.mvc.admin.vo.ChkInDateVO;
import kr.co.mvc.admin.vo.ReservationUpdateVO;

@Controller
public class ReservationController {

	@Autowired
	private ReservationService resSev;

	/**
	 * ������ ����
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "search_today_res_list.do", method = GET)
	public String searchTodayRes(Model model) {

		// �������� ����
		Calendar cal = Calendar.getInstance();
		int nowYear = cal.get(Calendar.YEAR);
		int nowMonth = cal.get(Calendar.MONTH) + 1;
		int nowDay = cal.get(Calendar.DAY_OF_MONTH);
		// ������ üũ�� ���ڷ� �����Ͽ� VO ����
		ChkInDateVO date = new ChkInDateVO();
		date.setYear(String.valueOf(nowYear));
		date.setMonth(String.valueOf(nowMonth));
		date.setDay(String.format("%02d", nowDay));

		model.addAttribute("todayList", resSev.searchRes(date));
		model.addAttribute("today", date);

		return "admin/common/admin_main";
	}// searchTodayRes
	
	/**
	 * ������� ����
	 * @param date
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "search_res_list.do", method = {GET,POST})
	public String searchRes(ChkInDateVO date, Model model) {
		model.addAttribute("resList", resSev.searchRes(date));
		
		return "admin/admin_reservation/admin_reservation_main";
	}// searchRes
	
	
	/**
	 * �������
	 * @param date
	 * @param delResNum
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "delete_res.do", method = POST)
	public String removeRes(ChkInDateVO date, String delResNum, Model model) {

		int result = resSev.removeRes(delResNum);
		if (result == 1) {
			model.addAttribute("delResult", true);
		} else {
			model.addAttribute("delResult", false);
		}//end else
		model.addAttribute("resList", resSev.searchRes(date));
		
		return "admin/admin_reservation/admin_reservation_main";
	}// removeRes

	
	/**
	 * ������� form
	 * @param resNum
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "chagne_res_form.do", method = {GET, POST})
	public String chagneResForm(String resNum, Model model) {
		
		model.addAttribute("resNum", resNum);
		model.addAttribute("ruVO", resSev.searchOneRes(resNum));
		model.addAttribute("roomList", resSev.searchAvailableRoomList());
		
		return "admin/admin_reservation/admin_reservation_change";
	}// chagneResForm

	/**
	 * �������
	 * @param resNum
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "change_res_process.do", method = {GET,POST})
	public String chagneResProcess(ReservationUpdateVO ruVO, Model model) {
		int result =0;

		boolean upateResult = false; 
		model.addAttribute("upateResult", upateResult);
		
		//���� �ִ� ���� �ο� üũ
		int maxGuest = resSev.searchMaxGuest(ruVO);
		int totalGuest = ruVO.getAdult() + ruVO.getChild();
		List<String> resList = resSev.searchStayDateList(ruVO);
		
		if(totalGuest > maxGuest) {
			model.addAttribute("msg", ruVO.getrName() +"�� �ִ� �����ο��� " + maxGuest+"�Դϴ�.");
		} else if (!(resList.isEmpty())) {
			model.addAttribute("msg", "�ش�Ⱓ �� �ٸ� ������ �����մϴ�.");
		}//end if 
		
		if(!upateResult) {// if ��� �� update ����
			result = resSev.changeRes(ruVO);
			if(result == 1) {
				upateResult = true;
				model.addAttribute("upateResult", upateResult);
				model.addAttribute("msg", "������ ���������� ����Ǿ����ϴ�.");
			} else {
				model.addAttribute("msg", "�˼��մϴ�. ��� �� �ٽ� �õ����ּ���.");
			}//end else
		}//end if

		return "redirect:search_res_list.do";
	}//chagneResProcess
	
	
}// class
