package kr.co.mvc.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.mvc.user.service.UserReservationService;
import kr.co.mvc.user.vo.RoomVO;
import kr.co.mvc.user.vo.UserCardVO;
import kr.co.mvc.user.vo.UserMemberVO;
import kr.co.mvc.user.vo.UserResCheckVO;
import kr.co.mvc.user.vo.UserReservationVO;
import kr.co.mvc.user.vo.UserRoomVO;
import kr.co.sist.util.cipher.DataDecrypt;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class UserReservationController {

	@Autowired
	private UserReservationService resService;
	
	/**
	 * 객실소개
	 * @param model
	 * @param rName
	 * @param rStatus
	 * @return
	 */
	@RequestMapping(value="user/user_room/room_intro.do", method=GET)
	public String reserve_intro(Model model, String rName, String rStatus) {
		int i = 0;
		model.addAttribute("roomList", resService.searchAllRooms(null, "Y"));
		model.addAttribute("newLineChar", "\n");
		model.addAttribute("imgVOList", resService.searchOtherImgs(rName, rStatus));
		model.addAttribute("cnt", resService.roomCnt(rName, rStatus));
		
		return "user/user_room/room_intro";
	}//reserve_intro
	
	/**
	 * 날짜와 인원수를 받아 예약 가능한 방 조회하기
	 * @param start_date
	 * @param end_date
	 * @param adult
	 * @param child
	 * @return
	 */
	@RequestMapping(value="user/user_room/ajax_room_date.do",  method = POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String ajaxReq(String start_date, String end_date, String adult,String child) {
		
		if("".equals( adult)) {
			adult="0";
		}
		if("".equals( child)) {
			child="0";
		}

		String jsonObj = resService.searchAvaileReserve(start_date, end_date,  Integer.parseInt( adult), Integer.parseInt(child));
		
		return jsonObj;
	}//ajaxReq
	
	
	/**
	 * 예약 가능한 방의 목록
	 * @param start_date
	 * @param end_date
	 * @param adult
	 * @param child
	 * @param rev_room_num
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="user/user_room/room_reserve.do", method = POST)
	public String reserve_01Reserve(String start_date, String end_date,String adult, String child, String[] rev_room_num, Model model) throws ParseException {
		
		if("".equals( adult)) {
			adult="0";
		}
		if("".equals( child)) {
			child="0";
		}
		
		Date sdFormat = new SimpleDateFormat("yyyy.MM.dd").parse(start_date);//
		Date edFormat = new SimpleDateFormat("yyyy.MM.dd").parse(end_date);
		long diffDays = (edFormat.getTime() - sdFormat.getTime() )/1000/(24*60*60);
		
		model.addAttribute("paramSd", start_date);
		model.addAttribute("paramEd", end_date);
		model.addAttribute("paramAdult", adult);
		model.addAttribute("paramChild", child);
		model.addAttribute("paramRoomNo", rev_room_num);
		model.addAttribute("diffDays", diffDays);
		
		List<UserRoomVO> listRom =new ArrayList<UserRoomVO>();
		UserRoomVO urVO=null;
		String paramR_no = null;
		
		for( String room_no: rev_room_num ) {
			urVO=resService.searchRoomInfo(Integer.parseInt(room_no));//배열방번호 하나를 넣어서 방하나를 조회해서
			listRom.add( urVO );//조회된 방하나를 리스트에 넣는다.
		}//end for
		
		//for가 종료되면 리스트에는 예약가능한 모든 방이 들어간다.
		model.addAttribute("searchRoom",  listRom );//모든 방을 모델에 넣어서 JSP에서 출력할 수 있도록 해준다.
		model.addAttribute("room_no", rev_room_num);
		
		
		return "user/user_room/room_reserve";
	}//reserve_01Reserves
	
	
	
	/**
	 * 방의 상세페이지 - 요청사항 받음
	 * @param sd
	 * @param ed
	 * @param adult
	 * @param child
	 * @param room_no
	 * @param model
	 * @return
	 * @throws ParseException
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	@RequestMapping(value="user/user_room/room_reserve2.do", method = POST)
	public String reserve_02check(String sd, String ed, String adult, String child, int room_no, Model model) throws ParseException, NumberFormatException, SQLException {
		Date sdFormat = new SimpleDateFormat("yyyy.MM.dd").parse(sd);//
		Date edFormat = new SimpleDateFormat("yyyy.MM.dd").parse(ed);
		long diffDays = (edFormat.getTime() - sdFormat.getTime() )/1000/(24*60*60);

		
		if("".equals( adult)) {
			adult="0";
		}
		if("".equals( child)) {
			child="0";
		}
		
		UserRoomVO rVO = resService.searchRoomInfo(room_no);
		int cnt = resService.searchImgCnt(room_no);
		int price = rVO.getPrice();
		int tax = (int) (price*0.21);
		int totalP = (int)(price+tax);
		int daysPrice = price*(int)diffDays;
		int daysTax = tax*(int)diffDays;
		int daysTotal = (daysPrice + daysTax);
				
		model.addAttribute("paramSd", sd);
		model.addAttribute("paramEd", ed);
		model.addAttribute("paramAdult", adult);
		model.addAttribute("paramChild", child);
		model.addAttribute("diffDays", diffDays);
		model.addAttribute("rv", rVO);
		model.addAttribute("price", price);
		model.addAttribute("daysP", daysPrice);
		model.addAttribute("daysTax", daysTax);
		model.addAttribute("daysTotal", daysTotal);
		model.addAttribute("cnt", cnt);
		model.addAttribute("imgs", resService.searchSubImgs(room_no));
		

		return "user/user_room/room_reserve2";
	}//reserve_02check
	
	
	/**
	 * 카드정보 입력 단계
	 * @param sd
	 * @param ed
	 * @param adult
	 * @param child
	 * @param room_no
	 * @param diffDays
	 * @param addReq
	 * @param session
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws GeneralSecurityException
	 * @throws SQLException
	 */
	@RequestMapping(value="user/user_room/room_reserve3_card.do", method=POST)
	public String reserve_03card(String sd, String ed, String adult, String child, int room_no, long diffDays, String addReq, HttpSession session, Model model) throws UnsupportedEncodingException, GeneralSecurityException, SQLException {

		String id = (String) session.getAttribute("id");
		
		// 체크인월일 구하기 (res_no에 사용됨)
		String year = sd.substring(0, 4);
		String month = sd.substring(5,7);
		String day = sd.substring(8,10);
		
		// 룸넘버 0으로 두자리 만들기
		String zeroRoomNo = String.format("%02d", room_no);
		String zeroDiffDays = String.format("%03d", diffDays);
		
		//예약번호 생성 (년월일 - 박수(3자리) -R룸넘버)
		String strResNo = year + month + day + "-" + zeroDiffDays + "R" + zeroRoomNo;
		
		UserRoomVO rVO = resService.searchRoomInfo(room_no);
		UserMemberVO mVO = resService.DecryptSelectMemInfo(id);
		String savedFlag = resService.searchCardFlag(id);
		
		int price = rVO.getPrice();
		int tax = (int) (price*0.21);
		int totalP = (int)(price+tax);
		int daysPrice = price*(int)diffDays;
		int daysTax = tax*(int)diffDays;
		int daysTotal = (daysPrice + daysTax);
		
		model.addAttribute("paramSd", sd);
		model.addAttribute("paramEd", ed);
		model.addAttribute("paramAdult", adult);
		model.addAttribute("paramChild", child);
		model.addAttribute("diffDays", diffDays);
		model.addAttribute("addReq", addReq);
		model.addAttribute("rv", rVO);
		model.addAttribute("daysP", daysPrice);
		model.addAttribute("daysTax", daysTax);
		model.addAttribute("daysTotal", daysTotal);
		model.addAttribute("mv", mVO);
		model.addAttribute("saveFlag", savedFlag);
		model.addAttribute("strResNo", strResNo);
		
		 if( !savedFlag.equals("0")){//저장된 사용자의 카드 정보가 있다묜
			 // 사용자의 카드 정보 가져오기
			UserCardVO cVO = resService.searchCardInfo(id);
			String savedCard_no = cVO.getCard_no(); 
			String savedMM = cVO.getVal_mm();
			String savedYY = cVO.getVal_yy();
			String savedCompany = cVO.getCompany();
			model.addAttribute("savedCard_no", savedCard_no);
			model.addAttribute("savedMM", savedMM);
			model.addAttribute("savedYY", savedYY);
			model.addAttribute("savedCompany", savedCompany);
		} //end if
		
		return "user/user_room/room_reserve3_card";
	}//reserve_03card
	
	
	
	
	/**
	 * 예약완료
	 * @param sd
	 * @param ed
	 * @param adult
	 * @param child
	 * @param room_no
	 * @param diffDays
	 * @param addReq
	 * @param resNo
	 * @param card_no
	 * @param cardCompany
	 * @param val_MM
	 * @param val_YY
	 * @param saveYN
	 * @param ccYN
	 * @param piYN
	 * @param saveFlag
	 * @param session
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws GeneralSecurityException
	 * @throws SQLException
	 */
	@RequestMapping(value="user/user_room/room_reserve4_final.do", method=POST)
	public String reserve_04final(String sd, String ed, String adult, String child, int room_no, long diffDays, String addReq,
					String resNo, String card_no, String cardCompany, String val_MM, String val_YY,
					String saveYN, String ccYN, String piYN, String saveFlag, HttpSession session, Model model) throws UnsupportedEncodingException, GeneralSecurityException, SQLException {
	
		String id = (String) session.getAttribute("id");
		
		UserRoomVO rVO = resService.searchRoomInfo(room_no);
		model.addAttribute("rv", rVO);
		
		int price = rVO.getPrice();
		int tax = (int) (price*0.21);
		int totalP = (int)(price+tax);
		int daysPrice = price*(int)diffDays;
		int daysTax = tax*(int)diffDays;
		int daysTotal = (daysPrice + daysTax);
		
		UserMemberVO mVO = resService.DecryptSelectMemInfo(id);
		model.addAttribute("mv", mVO);
		
		model.addAttribute("paramSd", sd);
		model.addAttribute("paramEd", ed);
		model.addAttribute("paramAdult", adult);
		model.addAttribute("paramChild", child);
		model.addAttribute("diffDays", diffDays);
		model.addAttribute("addReq", addReq);
		model.addAttribute("daysP", daysPrice);
		model.addAttribute("daysTax", daysTax);
		model.addAttribute("daysTotal", daysTotal);
		
		model.addAttribute("strResNo", resNo);
		model.addAttribute("room_no", room_no);
		model.addAttribute("card_no ", card_no);
		model.addAttribute("cardCompany ", cardCompany);
		model.addAttribute("val_MM ", val_MM);
		model.addAttribute("val_YY ", val_YY);
		model.addAttribute("paramCardSave ", saveYN);
		model.addAttribute("paramCcAgree ", ccYN);
		model.addAttribute("paramPiAgree ", piYN);
		model.addAttribute("saveFlag ", saveFlag);
		
		//예약 insert 시작 --------------------
		UserReservationVO rsVO = new UserReservationVO();
		rsVO.setRes_no(resNo);
		rsVO.setId(id);
		rsVO.setRoom_no(room_no);
		rsVO.setAdult(Integer.parseInt(adult));
		rsVO.setChild(Integer.parseInt(child));
		rsVO.setChkin_date(sd);
		rsVO.setChkout_date(ed);
		rsVO.setAdd_req(addReq);
		rsVO.setCc_agree(ccYN);
		rsVO.setPi_agree(piYN);
		rsVO.setCard_no(card_no);
		rsVO.setCompany(cardCompany);
		resService.addReservation(rsVO);
		//--------------------------------------예약 insert 끝
				
		// 카드저장을 체크한, 기존 카드정보가 없는 사용자
		if ( saveYN.equals("Y") && saveFlag.equals("0")){
		 // 카드정보 insert
			UserCardVO cardVO = new UserCardVO();
			cardVO.setCard_no(card_no);
			cardVO.setCompany(cardCompany);
			cardVO.setId(id);
			cardVO.setRes_no(resNo);
			cardVO.setVal_mm(val_MM);
			cardVO.setVal_yy(val_YY);
			//카드정보 추가
			resService.addCardInfo(cardVO);
		}//end if
		
		// 카드저장을 체크한, 기존 카드정보가 있는 사용자
		if ( saveYN.equals("Y") && !saveFlag.equals("0")){
			// 카드정보 변경
			UserCardVO cardVO = new UserCardVO();
			cardVO.setCard_no(card_no);
			cardVO.setId(id);
			//카드정보 변경
			resService.modifyCardInfo(cardVO);
		}//end if

	return "user/user_room/room_reserve4_final";
	}//reserve_04final
	
	
	
	/**
	 * 예약조회
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping( value = "user/user_chk/reservation_inq.do", method = {GET, POST} )
	public String reservationChkList( HttpSession session, Model model ) {
		
		String id = (String) session.getAttribute("id");
		String url = "user/user_chk/reservation_inq";
		
		if( id == null ){//세션이 존재하지 않으면 
			url = "redirect:http://211.63.89.141/user/user_login/login.do";
		}else{//end if
		
			List<UserReservationVO> rList = null;
			rList = resService.searchResList(id);
			model.addAttribute("reserInq", rList);
		}
		return url;
	}//reservationChkList
	
	
	/**
	 * 예약조회 - 상세페이지
	 * @param session
	 * @param model
	 * @param res_no
	 * @return
	 * @throws ParseException
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 * @throws GeneralSecurityException
	 */
	@RequestMapping( value = "user/user_chk/reservation_confirm.do", method = {GET, POST} )
	public String reservationConfirm ( HttpSession session, Model model, String res_no ) throws ParseException, NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException {
		String id = (String) session.getAttribute("id");
		
		UserResCheckVO rVO = null;
		rVO = resService.searchResRoomInfo(res_no);
		
		Date sdFormat = new SimpleDateFormat("yyyy.MM.dd").parse(rVO.getChkin_date());
		Date edFormat = new SimpleDateFormat("yyyy.MM.dd").parse(rVO.getChkout_date());
		long diffDays = (edFormat.getTime() - sdFormat.getTime() )/1000/(24*60*60);
		
		int price = rVO.getPrice();
		int tax = (int) (price*0.21);
		int daysPrice = price*(int)diffDays;
		int daysTax = tax*(int)diffDays;
		int daysTotal = (daysPrice + daysTax);
		
		DataDecrypt dd=new DataDecrypt("AbcdEfgHiJkLmnOpQ");
	 	rVO.setTel(dd.decryption(rVO.getTel()));
		rVO.setEname_fst(dd.decryption(rVO.getEname_fst()));
		rVO.setEname_lst(dd.decryption(rVO.getEname_lst()));
		rVO.setEmail(dd.decryption(rVO.getEmail()));
		
		model.addAttribute("res_no", res_no);
		model.addAttribute("rVO", rVO);
		model.addAttribute("price", price);
		model.addAttribute("daysPrice", daysPrice);
		model.addAttribute("daysTax", daysTax);
		model.addAttribute("daysTotal", daysTotal);
		model.addAttribute("diffDays", diffDays);

		
		return "user/user_chk/reservation_confirm";
	}//reservationConfirm
	
	/**
	 * 예약취소
	 * @param res_no
	 * @param res_status
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "user/user_chk/res_cancel.do", method = {GET, POST})
	public String resCancelProcess( String res_no, String res_status, Model model) {
		boolean cFlag = resService.searchCancelFlag(res_no);
		String url = "";
		if( cFlag ) {
			url = "forward:reservation_inq.do";
		}
		return url;
	}//resCancelProcess
	
}//class
