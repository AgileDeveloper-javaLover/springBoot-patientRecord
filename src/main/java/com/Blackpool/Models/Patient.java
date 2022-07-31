package com.Blackpool.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Patient {
	
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@Size(min=3, message="Name should have atleast 3 characters")
	@ApiModelProperty(notes="name should have atleast 5 characters")
	private String name;
	
	@Size(min=10, message="Address should have atleast 10 characters")
	@ApiModelProperty(notes="Address should have atleast 10 characters")
	private String address;
	
	@Email(message = "Email should be in valid formate")
	@ApiModelProperty(notes="Email should be in valid formate")
	private String email;
	
	@Size(min=10,message="Phone Number should have country code and 10 numbers")
	@ApiModelProperty(notes="Phone Number should have country code and 10 numbers")
	private String phoneNO;
	
	@Future
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(notes="Date should be in format- yyyy-MM-dd HH:mm:ss and it should be of future")
	private java.util.Date appointment;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNO() {
		return phoneNO;
	}

	public void setPhoneNO(String phoneNO) {
		this.phoneNO = phoneNO;
	}

	public java.util.Date getAppointment() {
		return appointment;
	}

	public void setAppointment(java.util.Date appointment) {
		this.appointment = appointment;
	}
	
	
		
}
