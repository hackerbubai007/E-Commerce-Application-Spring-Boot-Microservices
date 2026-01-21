package com.e_com.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Address {

	@NotBlank(message = "Full name is required")
	private String fullName; // Receiver name

	@NotBlank(message = "Phone number is required")
	private String phoneNumber;

	@NotBlank(message = "Address line 1 is required")
	private String addressLine1; // House/Flat, Building

	private String addressLine2; // Area, Landmark

	@NotBlank(message = "City is required")
	private String city;

	@NotBlank(message = "State is required")
	private String state;

	@NotBlank(message = "Country is required")
	private String country;

	@NotBlank(message = "Pincode is required")
	@Size(min = 6, max = 6, message = "Invalid pincode")
	private String pincode;

	private boolean isDefault; // Default delivery address

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	public Address(@NotBlank(message = "Full name is required") String fullName,
			@NotBlank(message = "Phone number is required") String phoneNumber,
			@NotBlank(message = "Address line 1 is required") String addressLine1, String addressLine2,
			@NotBlank(message = "City is required") String city, @NotBlank(message = "State is required") String state,
			@NotBlank(message = "Country is required") String country,
			@NotBlank(message = "Pincode is required") @Size(min = 6, max = 6, message = "Invalid pincode") String pincode,
			boolean isDefault) {
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
		this.isDefault = isDefault;
	}

	public Address() {
		// TODO Auto-generated constructor stub
	}

}
