package kr.co.sist.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class TestService2 {

	
	public List<String> subjectList(){
		
		List<String> list = new ArrayList<String>();
		list.add("Java SE - Desktop ���� �����ϴ� ���α׷� ");
		list.add("Oracle DBMS - ������ �����ϰ� ����");
		list.add("JDBC - Javs�� DBMS ����");
		list.add("HTML - �� ������ ���� ����"); //���� - �������׳� ���� ����� �����ش�. 
		list.add("CSS - �� �������� ���ϼ��ִ� ������");
		list.add("JavaScript = �� ������ ��ȿ�� ����");
		list.add("Java EE(Servlet/JSP) - HTML�� �������� �����ϱ� ���ؼ�"); //���� - �����ڿ� �µ��� ����� �����ش�.
		list.add("Model1 - ������ ������ ���α׷� �ۼ�");
		list.add("Model2 - ���������� ����Ͽ� ���������� ���α׷� �ۼ�");
		
		return list;
	}//subjectList
	
}//class
