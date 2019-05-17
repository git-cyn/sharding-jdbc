package com.cyn.shard.mapper;

import com.cyn.shard.bean.Address;

public interface AddressMapper {
	/**
	 * 保存
	 */
	void save(Address address);
	
	/**
	 * 查询
	 * @param id
	 * @return
	 */
	Address get(Long id);
}
