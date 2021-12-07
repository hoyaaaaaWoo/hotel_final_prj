package kr.co.sist.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class TestService2 {

	
	public List<String> subjectList(){
		
		List<String> list = new ArrayList<String>();
		list.add("Java SE - Desktop 에서 동작하는 프로그램 ");
		list.add("Oracle DBMS - 저보를 저장하고 관리");
		list.add("JDBC - Javs와 DBMS 연동");
		list.add("HTML - 웹 페이지 구조 설정"); //정적 - 누구한테나 같은 결과를 보여준다. 
		list.add("CSS - 웹 페이지의 통일성있는 디자인");
		list.add("JavaScript = 웹 페이지 유효성 검증");
		list.add("Java EE(Servlet/JSP) - HTML을 동적으로 생성하기 위해서"); //동적 - 접속자에 맞도록 결과를 보여준다.
		list.add("Model1 - 간단한 구조로 프로그램 작성");
		list.add("Model2 - 유지보수를 고려하여 구조적으로 프로그램 작성");
		
		return list;
	}//subjectList
	
}//class
