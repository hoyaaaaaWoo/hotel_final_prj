package kr.co.sist.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import kr.co.sist.dao.EmpDAO;
import kr.co.sist.vo.EmpVO;

@Controller
public class EmpService {

	@Autowired //서비스는 의존성 주입 받을 거야 
	private EmpDAO eDAO;
	
	public List<EmpVO> searchAllEmp() throws SQLException{
		
		List<EmpVO> list = null;
		
		list = eDAO.selectAllEmp();
		
		return list;
		
	}//searchAllEmp


	
}//class
