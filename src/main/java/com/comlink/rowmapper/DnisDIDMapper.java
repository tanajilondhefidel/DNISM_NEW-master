package com.comlink.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class DnisDIDMapper  implements RowMapper<String> {

	
	public String mapRow(ResultSet resultSet, int rowCount) throws SQLException {
		return resultSet.getString("DIDnumber");
	}

}