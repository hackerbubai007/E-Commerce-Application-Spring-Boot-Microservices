package com.e_com.request;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsersRequest {

	@NotBlank(message = "First name is required")
	private String userFirstName;

	@NotBlank(message = "Last name is required")
	private String userLastName;

	@NotBlank(message = "Username is required")
	private String userName;

	@NotBlank(message = "Password is required")
	@Size(min = 6, message = "Password must be at least 6 characters")
	private String password;

	@Email(message = "Invalid email format")
	@NotBlank(message = "Email is required")
	private String emailId;

	@NotBlank(message = "Phone number is required")
	private String phoneNumber;

	private List<Address> address;

	private String gender;
	private LocalDate dateOfBirth;

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public UsersRequest() {
		// TODO Auto-generated constructor stub
	}

	public UsersRequest(@NotBlank(message = "First name is required") String userFirstName,
			@NotBlank(message = "Last name is required") String userLastName,
			@NotBlank(message = "Username is required") String userName,
			@NotBlank(message = "Password is required") @Size(min = 6, message = "Password must be at least 6 characters") String password,
			@Email(message = "Invalid email format") @NotBlank(message = "Email is required") String emailId,
			@NotBlank(message = "Phone number is required") String phoneNumber, List<Address> address, String gender,
			LocalDate dateOfBirth) {
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userName = userName;
		this.password = password;
		this.emailId = emailId;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
	}

}
