package com.sample.contactapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.sample.contactapp.api.PhoneNumberVO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "PhoneNumber")
@Table(name = "phonenumber")
public class PhoneNumber {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	private String phone;
	
	@NotNull
	@Column(name = "phone_no_type")
	private PhoneNoType phoneNoType;
	
	public PhoneNumber(PhoneNumberVO phoneNumber){
		this.phone = phoneNumber.getPhone();
		this.phoneNoType = phoneNumber.getPhoneNoType();	
	}
	
}
