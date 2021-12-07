package kr.co.sist.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class carDAO {

	@Autowired(required = false)
	private JdbcTemplate jt;
	
	public List<String> selectMaker( String country ){
		List<String> makerList = null;
		
		StringBuilder selectMaker = new StringBuilder();
		selectMaker
		.append("	select maker	")
		.append("	from car_country	")
		.append("	where country = ? 	")
		;
		makerList = jt.query(selectMaker.toString(), new Object[] { country } , new RowMapper<String>() { //bind변수의 값은 Object[]로 들어가요 

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				String maker = "";
				maker = rs.getString("maker");
				return maker;
			}//mapRow
		});
		return makerList;
	}//selectMaker
	
	
	
	public List<String> selectModel( String maker ) throws DataAccessException{
		List<String> modelList = null;
		
		StringBuilder selectModel = new StringBuilder();
		selectModel
		.append("	select model	")
		.append("	from car_maker	")
		.append("	where maker = ? 	")
		;
		
		modelList = jt.query(selectModel.toString(), new Object[] { maker } , new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				String model = "";
				model = rs.getString("model");
				return model;
			}//mapRow
		});
		return modelList;
	}//selectMaker
}//class
