package be.gordion.address.services;

import be.gordion.address.domain.Address;

import java.util.Optional;

public interface AddressService {
    Optional<Address> findAddressById(long id);

    Address createAddress(Address address);
}
