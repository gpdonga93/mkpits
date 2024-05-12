package com.mkpits.service;

import java.util.List;
import java.util.Optional;

import com.mkpits.model.Address;

public interface AddressService {

	void deleteAll(List<Address> addresses);

	Address createAddress(Address address);

	Optional<Address> findById(Long addressId);

}
