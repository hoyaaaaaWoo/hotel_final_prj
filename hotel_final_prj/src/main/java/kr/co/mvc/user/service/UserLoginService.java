package kr.co.mvc.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.mvc.user.dao.UserLoginDAO;

@Component
public class UserLoginService {

	@Autowired
	private UserLoginDAO loginDAO;
	
	
}
