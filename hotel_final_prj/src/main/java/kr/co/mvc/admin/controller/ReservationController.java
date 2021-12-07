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
	 * 오늘의 예약
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "search_today_res_list.do", method = GET)
	public String searchTodayRes(Model model) {

		// 현재일자 설정
		Calendar cal = Calendar.getInstance();
		int nowYear = cal.get(Calendar.YEAR);
		int nowMonth = cal.get(Calendar.MONTH) + 1;
		int nowDay = cal.get(Calendar.DAY_OF_MONTH);
		// 오늘을 체크인 일자로 투입하여 VO 생성
		ChkInDateVO date = new ChkInDateVO();
		date.setYear(String.valueOf(nowYear));
		date.setMonth(String.valueOf(nowMonth));
		date.setDay(String.format("%02d", nowDay));

		model.addAttribute("todayList", resSev.searchRes(date));
		model.addAttribute("today", date);

		return "admin/common/admin_main";
	}// searchTodayRes
	
	/**
	 * 예약관리 메인
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
	 * 예약삭제
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
	 * 예약수정 form
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
	 * 예약수정
	 * @param resNum
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "change_res_process.do", method = {GET,POST})
	public String chagneResProcess(ReservationUpdateVO ruVO, Model model) {
		int result =0;

		boolean upateResult = false; 
		model.addAttribute("upateResult", upateResult);
		
		//객실 최대 수용 인원 체크
		int maxGuest = resSev.searchMaxGuest(ruVO);
		int totalGuest = ruVO.getAdult() + ruVO.getChild();
		List<String> resList = resSev.searchStayDateList(ruVO);
		
		if(totalGuest > maxGuest) {
			model.addAttribute("msg", ruVO.getrName() +"의 최대 수용인원은 " + maxGuest+"입니다.");
		} else if (!(resList.isEmpty())) {
			model.addAttribute("msg", "해당기간 중 다른 예약이 존재합니다.");
		}//end if 
		
		if(!upateResult) {// if 통과 시 update 진행
			result = resSev.changeRes(ruVO);
			if(result == 1) {
				upateResult = true;
				model.addAttribute("upateResult", upateResult);
				model.addAttribute("msg", "예약이 정상적으로 변경되었습니다.");
			} else {
				model.addAttribute("msg", "죄송합니다. 잠시 후 다시 시도해주세요.");
			}//end else
		}//end if

		return "redirect:search_res_list.do";
	}//chagneResProcess
	
	
}// class
