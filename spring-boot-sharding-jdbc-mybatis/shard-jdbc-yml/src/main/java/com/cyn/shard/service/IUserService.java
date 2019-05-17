package com.cyn.shard.service;

import com.cyn.shard.bean.User;

public interface IUserService {
	
	void save(User user);

	User get(Long l);
}	
