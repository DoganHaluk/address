package be.gordion.address.services;

import be.gordion.address.domain.Address;

import java.util.Optional;

public interface AddressService {
    Optional<Address> findById(long id);

    Address create(Address address);
}
