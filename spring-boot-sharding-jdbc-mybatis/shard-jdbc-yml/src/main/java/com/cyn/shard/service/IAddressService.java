package com.cyn.shard.service;

import com.cyn.shard.bean.Address;

public interface IAddressService {
	void save(Address address);

	Address get(Long id);
}
