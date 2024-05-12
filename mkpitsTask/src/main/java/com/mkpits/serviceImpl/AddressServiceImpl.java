package com.mkpits.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkpits.model.Address;
import com.mkpits.repository.AddressRepository;
import com.mkpits.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService{

	@Autowired
	public AddressRepository addressRepository;
	
	public AddressServiceImpl(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

	@Override
	public void deleteAll(List<Address> addresses) {
		addressRepository.deleteAll(addresses);
	}

	@Override
	public Address createAddress(Address address) {
		return addressRepository.save(address);
	}

	@Override
	public Optional<Address> findById(Long addressId) {
		return addressRepository.findById(addressId);
	}

}
