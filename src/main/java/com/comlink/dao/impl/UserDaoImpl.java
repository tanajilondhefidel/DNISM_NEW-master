package com.comlink.dao.impl;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.comlink.dao.UserDao;
import com.comlink.model.User;
import com.comlink.rowmapper.UserListMapper;

public class UserDaoImpl implements UserDao {

	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
   
 
	public int registerUser(User user) {
		 
		return jdbcTemplate.update("call register_user(?,?,?,?,?)",user.getFirstName(),user.getLastName(),user.getPassword(),user.getUserName(),user.getType());
	}

	public User login(String username, String password) {
		User user = new User();

		
		List<Map<String, Object>> userList = jdbcTemplate.queryForList("call login(?,?)",username,password);
		if (userList.size() > 0) {
			user.setId(userList.get(0).get("id").toString());
			user.setFirstName(userList.get(0).get("firstname").toString());
			user.setLastName(userList.get(0).get("lastname").toString());
			user.setPassword(userList.get(0).get("password").toString());
			user.setUserName(userList.get(0).get("username").toString());
			user.setType(userList.get(0).get("type").toString());
			return user;
		}
		return null;
	}

	
	public User getUserById(String id) {
		User user = new User();
		 
		List<Map<String, Object>> userList = jdbcTemplate.queryForList("call get_user_by_id(?)",Long.valueOf(id));
		if (userList.size() > 0) {
			user.setId(userList.get(0).get("id").toString());
			user.setFirstName(userList.get(0).get("firstname").toString());
			user.setLastName(userList.get(0).get("lastname").toString());
			user.setPassword(userList.get(0).get("password").toString());
			user.setUserName(userList.get(0).get("username").toString());
			user.setType(userList.get(0).get("type").toString());
			return user;
		}
		return null;
	}

	
	public List<User> getAllUser() {
		 
		List<User> list = jdbcTemplate.query("call get_all_users()", new UserListMapper());
		return list;
	}

	
	public int editUser(User user) {
		 
		return jdbcTemplate.update("call edit_user(?,?,?,?,?)",user.getFirstName(),user.getLastName(),user.getUserName(),user.getId(),user.getPassword());
	}

	
	public int deleteUser(String id) {
		 
		return jdbcTemplate.update("call delete_user(?)",Long.valueOf(id));
	}

	
	public int updateUser(String id, String password) {
		 
		return jdbcTemplate.update("call update_user(?,?)",Long.valueOf(id),password);
	}

}
