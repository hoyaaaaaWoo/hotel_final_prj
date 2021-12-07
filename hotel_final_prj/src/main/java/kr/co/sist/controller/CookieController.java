package kr.co.sist.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("cookie/")
public class CookieController {

	@RequestMapping(value = "cookie_set.do", method = GET)
	public String addCookie( HttpServletResponse response ) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		
		// 1. ��Ű ����
		Cookie nameCookie = new Cookie("name", "����ȣ");
		Cookie dateCookie = new Cookie("date", sdf.format(new Date()));
		
		// 2. �����ð�����
		nameCookie.setMaxAge(60*60*24*1); //1�� ����
		dateCookie.setMaxAge(60*60*24*1); //1�� ����
		
		// 3. ��Ű �ɱ�
		response.addCookie( nameCookie );
		response.addCookie( dateCookie );
		
		return"cookie/cookie_result";
	}//addCookie
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	@RequestMapping(value = "cookie_get.do", method = GET)
	public String getCookie( HttpServletRequest request, Model model) {
		
		// ��Ű�� �о� Model interface�� ����
		Cookie[] cookies = request.getCookies();
		
		if( cookies != null ) {//�о���� ��Ⱑ �����Ѵ�.
			for( Cookie cookie : cookies ) {
				
				if( "name".equals(cookie.getName()) ) {
					model.addAttribute("req_name", cookie.getValue() );
				}//end if
				
				if( "date".equals(cookie.getName()) ) {
					model.addAttribute("req_date", cookie.getValue() );
				}//end if
				
			}//end for
		}//end if

		return "cookie/read_cookie";
	}//getCookie
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	@RequestMapping(value = "cookie_value_get.do", method = GET)
	public String getCookieValue(@CookieValue(value = "name", defaultValue = "�͸�") String name, 
			@CookieValue(value = "date", defaultValue = "none")String date, Model model) {
		
		model.addAttribute( "req_name", "cookie_value_get : " + name );
		model.addAttribute( "req_date", "cookie_value_get : " + date );
		
		return "cookie/read_cookie";
	}//getCookieValue
	
}//class
