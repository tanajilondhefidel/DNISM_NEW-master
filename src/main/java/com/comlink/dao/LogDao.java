package com.comlink.dao;


import java.sql.Date;
import java.util.List;

import com.comlink.model.Log;
 

public interface LogDao {	
	public List<Log> getAllLogs();
	public int LogEvent(String firstName,String lastName,String userId, String activity); 
	//List<Log> getLogonDates(String sqlDates, String sqlDatee);
	List<Log> getLogonDates(String sqlDates, String sqlDatee);
}
