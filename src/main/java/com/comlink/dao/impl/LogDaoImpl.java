package com.comlink.dao.impl;


import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.comlink.dao.LogDao;
import com.comlink.model.Log;
import com.comlink.rowmapper.LogListMapper;

public class LogDaoImpl implements LogDao {
	
	
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	public List<Log> getAllLogs() {
		 
	    List<Log> list = jdbcTemplate.query("call get_all_logs()",  new LogListMapper());
		return list;
	}

	
	public int LogEvent(String firstName, String lastName, String userId,String activity) {
		 
		return jdbcTemplate.update("call log_event(?,?,?,?)",firstName,lastName,userId,activity);
	}	
	

	
	
	


public  List<Log> getLogonDates(String sqlDates, String sqlDatee) {

	List<Log> userList = jdbcTemplate.query("call getLogonDates(?,?)",new LogListMapper(),sqlDates,sqlDatee);
	return userList;
}
 
}
