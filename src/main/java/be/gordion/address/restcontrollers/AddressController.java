package be.gordion.address.restcontrollers;

import be.gordion.address.domain.Address;
import be.gordion.address.exceptions.AddressNotFoundException;
import be.gordion.address.services.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/address")
class AddressController {
    private final AddressService addressService;
    Logger logger = LoggerFactory.getLogger(AddressController.class);

    AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("{id}")
    Address getAddress(@PathVariable long id) {
        logger.info("Address id: " + id + " requested");
        return addressService.findAddressById(id).orElseThrow(AddressNotFoundException::new);
    }

    @ExceptionHandler(AddressNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void productNotFound() {
        logger.error("Unexisted address is requested");
    }

    @PostMapping
    ResponseEntity<Address> postAddress(@RequestBody @Valid Address address) {
        return new ResponseEntity<>(addressService.createAddress(address), HttpStatus.CREATED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Map<String, String> verkeerdeData(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
    }
}
