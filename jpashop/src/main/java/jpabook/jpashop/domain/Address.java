package jpabook.jpashop.domain;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import sun.security.util.Length;

@Embeddable
public class Address {

    @Column(length =  10)
    private String city;
    @Column(length =  20)
    private String street;
    @Column(name = "zip_code", length = 5)
    private String zipCode;

    public Address() {
    }

    public Address(String city, String street, String zipCode) {
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }

    public String fullAddress() {
        return getCity() + " " + getStreet() + " " + getZipCode();
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getZipCode() {
        return zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Address address = (Address) o;
        return Objects.equals(getCity(), address.getCity()) && Objects.equals(
                getStreet(), address.getStreet()) && Objects.equals(getZipCode(),
                address.getZipCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCity(), getStreet(), getZipCode());
    }
}
