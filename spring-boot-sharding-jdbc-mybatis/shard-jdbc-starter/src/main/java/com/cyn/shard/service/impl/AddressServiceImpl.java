package com.cyn.shard.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyn.shard.bean.Address;
import com.cyn.shard.mapper.AddressMapper;
import com.cyn.shard.service.IAddressService;

@Service
public class AddressServiceImpl implements IAddressService{
	
	@Autowired
	private AddressMapper addressMapper;

	@Override
	public void save(Address address) {
		// TODO Auto-generated method stub
		addressMapper.save(address);	
	}

	@Override
	public Address get(Long id) {
		// TODO Auto-generated method stub
		return addressMapper.get(id);
	}

}
