package com.comlink.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.comlink.model.DNISTarget;

public class DnisTargetMapper implements RowMapper<DNISTarget> {

	
	public DNISTarget mapRow(ResultSet resultSet, int rowCount) throws SQLException {
		DNISTarget dnis = new DNISTarget();
		dnis.setDNIS(resultSet.getString("dnis"));
		dnis.setTarget(resultSet.getString("target"));
		return dnis;
	}

}
