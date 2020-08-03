package com.sample.contactapp.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sample.contactapp.entity.PhoneNoType;
import com.sample.contactapp.entity.PhoneNumber;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
public class PhoneNumberVO {

	private String phone;
	
	private PhoneNoType phoneNoType;
	
	public PhoneNumberVO(PhoneNumber p){
		this.phone = p.getPhone();
		this.phoneNoType = p.getPhoneNoType();
	}
	
	
}
