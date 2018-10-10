package com.comlink.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.comlink.model.DNISTarget;
import com.comlink.model.GATEWAYS;
import com.comlink.model.GATEWAYSHost;

public class GatewaysHostMapper implements RowMapper<GATEWAYSHost> {

	
	public GATEWAYSHost mapRow(ResultSet resultSet, int rowCount) throws SQLException {
		GATEWAYSHost gatewaysHost = new GATEWAYSHost();  
		gatewaysHost.setId(resultSet.getString("id"));
		gatewaysHost.setHost(resultSet.getString("host"));
		gatewaysHost.setTrunkGroupId(resultSet.getString("trunkGroupId"));
		return gatewaysHost;
	}

}
