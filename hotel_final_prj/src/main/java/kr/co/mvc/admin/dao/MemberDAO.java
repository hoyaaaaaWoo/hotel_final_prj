package kr.co.mvc.admin.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

public class MemberDAO {

	private JdbcTemplate jt;
	
	public List<String> selectMember(String id){
		List<String> list = null;
		
		return list;
	}
	public List<String> selectDelMember(String id){
		List<String> list = null;
		
		return list;
	}
	public int updateMemberProcess(String id){
		int cnt =0;
		return cnt;
	}
}//class
