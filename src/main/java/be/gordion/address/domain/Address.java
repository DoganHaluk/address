package be.gordion.address.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private long addressId;
    @NotBlank
    @Column(name = "municipality_name")
    private String municipalityName;
    @NotNull
    @Min(1000)
    @Max(9999)
    @Column(name = "postal_code")
    private int postalCode;
    @NotBlank
    @Column(name = "street_name")
    private String streetName;
    @NotBlank
    @Column(name = "house_number")
    private String houseNumber;
    @Column(name = "box_number")
    private String boxNumber;
}
