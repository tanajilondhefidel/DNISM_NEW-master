package com.comlink.rowmapper;
 

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.comlink.model.Log;
 
public class LogListMapper implements RowMapper<Log> {

	
	public Log mapRow(ResultSet resultSet, int rowCount) throws SQLException {
		Log log = new Log();
		log.setFname(resultSet.getString("fname"));
		log.setLname(resultSet.getString("lname"));
		log.setUserid(resultSet.getString("userid"));
		log.setTime(resultSet.getString("time"));
		log.setActivity(resultSet.getString("activity"));
		return log;
	}

}
