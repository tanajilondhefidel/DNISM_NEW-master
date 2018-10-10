package com.comlink.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.comlink.model.DNISTarget;
import com.comlink.model.GATEWAYS;

public class GatewaysMapper implements RowMapper<GATEWAYS> {

	
	public GATEWAYS mapRow(ResultSet resultSet, int rowCount) throws SQLException {
		System.out.println("\n resultSet-------->>"+resultSet.getString("RoutingIP1"));
		GATEWAYS gateways = new GATEWAYS();  
		gateways.setGatewayGroupName(resultSet.getString("GatewayGroupName"));
		gateways.setRoutingIP1(resultSet.getString("RoutingIP1"));
		gateways.setRoutingIP2(resultSet.getString("RoutingIP2"));
		gateways.setRoutingIP3(resultSet.getString("RoutingIP3"));
		gateways.setRoutingIP4(resultSet.getString("RoutingIP4"));
		gateways.setRoutingIP5(resultSet.getString("RoutingIP5"));
		gateways.setRoutingIP6(resultSet.getString("RoutingIP6"));
		gateways.setRoutevalue1(resultSet.getString("GatewayID1Value"));
		gateways.setRoutevalue2(resultSet.getString("GatewayID2Value"));
		gateways.setRoutevalue3(resultSet.getString("GatewayID3Value"));
		gateways.setRoutevalue4(resultSet.getString("GatewayID4Value"));
		gateways.setRoutevalue5(resultSet.getString("GatewayID5Value"));
		gateways.setRoutevalue6(resultSet.getString("GatewayID6Value"));
		return gateways;
	}

}
