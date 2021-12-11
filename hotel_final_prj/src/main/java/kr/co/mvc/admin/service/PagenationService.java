package kr.co.mvc.admin.service;

import org.springframework.stereotype.Component;

/**
 * ���������̼� ���� Ŭ����
 * @author user
 */

@Component
public class PagenationService {
	
	private int pageScale;
	
	/** 
	 * pageScale setting
	 * @param pageScale
	 */
	public void setPageScale(int pageScale) {
		this.pageScale = pageScale;
	}//setPageScale
	
	/**
	 * ��ü ������ �� 
	 * @param totalCnt
	 * @return
	 */
	public int getTotalPage(int totalCnt) {
		int totalPage = 0;
		
		totalPage=(int) Math.ceil((double)totalCnt/pageScale);
		return totalPage;
	}//findTotalPage

	
	/**
	 * ������������ ���� ���۹�ȣ ���ϱ�
	 * @param currentPage
	 * @param pageScale
	 * @return
	 */
	public int getStartNum(String currentPage) {
		int startNum =0;
		
		try {
			startNum = Integer.parseInt(currentPage)*pageScale-pageScale+1;
		}catch(NumberFormatException nfe) {
			startNum = 1;
		}//end catch
		
		return startNum;
	}//getStartNum

	
	/**
	 * ����ȣ ���ϱ�
	 * @param startNum
	 * @param pageScale
	 * @return
	 */
	public int getEndNum(int startNum) {
		int endNum = startNum + pageScale-1;
		return endNum;
	}//getEndNum
	

}//class
