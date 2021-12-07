package kr.co.sist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.sist.vo.ParamVO;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpServletRequest;

@Controller //Handler 가 찾게하좌~
public class ParamController {
	
	@RequestMapping(value="param/param_form.do", method=GET)
	public String paramForm() {
		return "param/param_form";
	}//paramForm
	
	
	@RequestMapping(value="param/param_form_process.do", method={GET, POST})
	public String paramFormProcess( HttpServletRequest request) {
//		//post방식의 한글처리 : request가 사용되기 전 실행되어야 함
//		try {
//			request.setCharacterEncoding("UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		
		//파라메터 받기
		System.out.println("요청방식 : " + request.getMethod());
		
		//파라메터 값 받기
		// 유일
		System.out.println("이름 : " + request.getParameter("name"));
		//중복 : 배열로 처리
		String[] hobby=request.getParameterValues("hobby");
		if( hobby != null ) {
			for( String value : hobby ) {
				System.out.println(value + " " );
			}//end for
			System.out.println();
		}else {
			System.out.println("선택 취미 없음");
		}//end else
		
		
		return "param/param_form_process";
	}//paramFormProcess
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	
	@RequestMapping(value="param/param_form2.do", method=GET)
	public String paramForm2() {
		return "param/param_form2";
	}//paramForm // form불러오기
	
	@RequestMapping(value="param/param_form_process2.do", method={GET, POST})
	//public String paramFormProcess2( String name, String[] hobby, String age ) {
		// web parameter는 String형으로만 전송되지만, int로 매개변수를 설정하면 
		// Spring에서 parseInt method를 사용하여 변환 후 입력해준다. 
	public String paramFormProcess2( String name, String[] hobby, 
			@RequestParam(required = false, defaultValue = "0")int age ) {
		
		System.out.println("단일형 데이터형을 사용한 파라메터 값 받기");
		System.out.println("이름 : " + name);
		//System.out.println("나이 : " + Integer.parseInt(age));
		System.out.println("나이 : " + age);
		
		if( hobby != null ) {
			for( String value : hobby ) {
				System.out.println(value + " " );
			}//end for
			System.out.println();
		}else {
			System.out.println("선택 취미 없음");
		}//end else
		
		return "param/param_form_process2";
	}//paramFormProcess2
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	@RequestMapping(value="param/param_form_test.do", method=GET)
	public String paramForm_test() {
		return "param/param_form_test";
	}//paramForm // form불러오기
	
	@RequestMapping(value="param/param_form_process_test.do", method=GET)
	public String paramFormProcess_test( String name, String age, String phoneNum ) {
		
		System.out.println("이름 : " + name);
		System.out.println("나이 : " + Integer.parseInt(age));
		System.out.println("전화번호 : " +  phoneNum);
		
		return "param/param_form_process_test";
	}//paramFormProcess3
	
	
///////////////////////////////////////////////  11-30-2021 코드  ///////////////////////////////////////	

	@RequestMapping(value="param/param_form3.do", method=GET)
	public String paramForm3() {
		return "param/param_form3";
	}//paramForm // form불러오기
	
	@RequestMapping(value="param/param_form_process3.do", method={GET, POST})
	public String paramFormProcess3( HttpServletRequest request, ParamVO pVO ) {
		
		pVO.setIp(request.getRemoteAddr()); // 아이피 변조가 불가능
		
		System.out.println("VO를 사용한 파라메터 값 받기");
		System.out.println("이름 : " + pVO.getName());
		//System.out.println("나이 : " + Integer.parseInt(age));
		System.out.println("나이 : " + pVO.getAge());
		System.out.println("접속자의 ip address : " + pVO.getIp());
		
		String[] hobby = pVO.getHobby();
				
		if( hobby != null ) {
			for( String value : hobby ) {
				System.out.println(value + " " );
			}//end for
			System.out.println();
		}else {
			System.out.println("선택 취미 없음");
		}//end else
		
		return "param/param_form_process3";
	}//paramFormProcess2
	
}//class
