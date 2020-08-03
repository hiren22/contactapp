package com.sample.contactapp.entity;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.sample.contactapp.api.ContactVO;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="contact")
@Table(name="contact")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@NotNull
	@Column(unique = true)
	private String email;
	
	@NotNull
	private String name;

	private String lastName;
	 
	@OneToMany(
		        cascade = CascadeType.ALL,
		        orphanRemoval = true, fetch = FetchType.EAGER
		    )
	private List<PhoneNumber> phoneNumbers;
	
	public Contact(ContactVO contact){
		this.email = contact.getEmail();
		this.name = contact.getName();
		this.lastName = contact.getLastName();
		this.phoneNumbers = contact.getPhoneNumbers().stream().map(s->new PhoneNumber(s)).collect(Collectors.toList());
	}
		
}
