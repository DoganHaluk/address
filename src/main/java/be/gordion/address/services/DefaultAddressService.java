package be.gordion.address.services;

import be.gordion.address.domain.Address;
import be.gordion.address.repositories.AddressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class DefaultAddressService implements AddressService {
    private final AddressRepository addressRepository;

    public DefaultAddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Address> findById(long id) {
        return addressRepository.findById(id);
    }

    @Override
    public Address create(Address address) {
        return addressRepository.save(address);
    }
}
