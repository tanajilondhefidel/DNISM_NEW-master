package com.comlink.dao.impl;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.comlink.dao.ConfigDao;

public class ConfigDaoImpl  implements ConfigDao
{

	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	public int uploadconfigFile(String s) {
		
		return jdbcTemplate.update("call uploadconfigFile(?)",s);
	}
	
}
