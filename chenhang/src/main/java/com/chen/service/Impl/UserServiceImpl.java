package com.chen.service.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chen.mapper.UserMapper;
import com.chen.model.User;
import com.chen.service.UserServiceI;
@Service("userService")
public class UserServiceImpl implements UserServiceI {
	@Resource
	UserMapper userMapper;

	@Override
	public void excuteSql(String s) {
		String [] str = s.split(";");
		ArrayList<Object> list = new ArrayList<>();
		for(String ss : str){
			list.add(ss);
//			System.out.println(ss);
		}
		userMapper.runSql(list);
	}


	
	
}
