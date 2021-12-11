package kr.co.mvc.admin.vo;

import org.springframework.stereotype.Component;

/**
 * üũ�� ���ڷ� ������ ��ȸ�� ����� VO
 * @author user
 */

@Component
public class ChkInDateVO {
	
	private String year, month, day;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	@Override
	public String toString() {
		return "ChkInDateVO [year=" + year + ", month=" + month + ", day=" + day + "]";
	}

}//class
