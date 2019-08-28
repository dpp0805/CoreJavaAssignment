package com.greatLearning.fsd.model;

import java.util.Objects;

public class Address implements Comparable<Address> {
    private int flat;
    private String apartment;
    private String street;
    private String city;

	public Address(int flat, String apartment, String street, String city) {
        this.flat = flat;
        this.apartment = apartment;
        this.street= street;
        this.city = city;
    }

	public int getFlat() {
		return flat;
	}

	public String getApartment() {
		return apartment;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	@Override
	public int compareTo(Address other) {
		return this.city.compareTo(other.getCity());
	} 
	
    @Override
	public int hashCode() {
		return Objects.hash(apartment, city, flat, street);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(apartment, other.apartment) && Objects.equals(city, other.city) && flat == other.flat
				&& Objects.equals(street, other.street);
	}

    @Override
    public String toString() {
        String address = "Flat:" + flat + ", " 
                        + "Apartment: " + apartment + ", " 
                        + "Street: " + street + ", " 
                        + "City: " + city;

        return address;
    }
}