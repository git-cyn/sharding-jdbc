package com.cyn.shard.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyn.shard.bean.User;
import com.cyn.shard.mapper.UserMapper;
import com.cyn.shard.service.IUserService;


@Service
public class UserServiceImpl implements IUserService{

	
		@Autowired
		private UserMapper userMapper;

		@Override
		public void save(User user) {
			user.setCreateTime(new Date());
			userMapper.save(user);
		}

		@Override
		public User get(Long id) {
			// TODO Auto-generated method stub
			return userMapper.get(id);
		}
		
		
}
