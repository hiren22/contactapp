package com.sample.contactapp.api;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sample.contactapp.entity.Contact;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
public class ContactVO {

	@NotBlank(message = "Email is mandatory")
	private String email;
	
	@NotBlank(message = "Name is mandatory")
	private String name;

	private String lastName;
	 
	private List<PhoneNumberVO> phoneNumbers;
	
	public ContactVO(Contact contact){
		this.email = contact.getEmail();
		this.name = contact.getName();
		this.lastName = contact.getLastName();
		this.phoneNumbers =contact.getPhoneNumbers().stream().map(s->new PhoneNumberVO(s)).collect(Collectors.toList());
	}
	
	public static List<ContactVO> ofList(List<Contact> contacts){
		return contacts.stream().map(c->new ContactVO(c)).collect(Collectors.toList());
	}
}
