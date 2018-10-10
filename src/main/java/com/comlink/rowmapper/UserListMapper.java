 package com.comlink.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.comlink.model.User;

public class UserListMapper implements RowMapper<User> {

	
	public User mapRow(ResultSet resultSet, int rowCount) throws SQLException {
		User user = new User();
		user.setId(resultSet.getString("id"));
		user.setFirstName(resultSet.getString("firstname"));
		user.setLastName(resultSet.getString("lastname"));
		user.setUserName(resultSet.getString("username"));
		user.setType(resultSet.getString("type"));
		return user;
	}

}
