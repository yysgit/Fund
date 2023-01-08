package com.yys.fund.service.impl;

import com.yys.fund.entity.DbUser;
import com.yys.fund.mapper.DbUserMapper;
import com.yys.fund.service.DbUserService;
import com.yys.fund.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserServiceImpl implements DbUserService {
	@Autowired
	private DbUserMapper userMapper;

	/**
	 * 管理员登陆
	 */
	@Override
	public DbUser login(String username, String password) {
		//对密码加密
		password= MD5.MD5Pwd(username,password);
		Map map=new HashMap();
		map.put("userName",username);
		map.put("userPassword",password);
		List<DbUser>  userList = userMapper.findUserByUsernamePassword(map);
		if ( userList!= null && userList.size() > 0) {
			return userList.get(0);
		}
		return null;
	}

	
	@Override
	public int addUser(DbUser User) {
		return userMapper.addUser(User);
	}

	@Override
	public List<DbUser> findUserByUsernamePassword(Map map) {
		return null;
	}


	@Override
	public int updateUser(DbUser User) {
		return userMapper.updateUser(User);
	}

	@Override
	public int updateUserLoginTime(DbUser User) {
		return userMapper.updateUserLoginTime(User);
	}
	@Override
	public int deleteUser(DbUser User) {
		return userMapper.deleteUser(User);
	}

	@Override
	public List<DbUser> findUserByUserName(Integer UserId,String username) {
		return userMapper.findUserByUserName(UserId,username);
	}


}
