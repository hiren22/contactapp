package com.sample.contactapp.service.impl;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.sample.contactapp.api.ContactVO;
import com.sample.contactapp.api.PhoneNumberVO;
import com.sample.contactapp.entity.Contact;
import com.sample.contactapp.entity.PhoneNoType;
import com.sample.contactapp.entity.PhoneNumber;
import com.sample.contactapp.exception.InvalidContactException;
import com.sample.contactapp.repository.ContactRepository;
import com.sample.contactapp.service.ContactBookService;

public class ContactBookServiceImplTest {

	private ContactBookService contactBookService;

	private ContactRepository contactRepositoryMock;
	@Before
	public void onSetup(){
		contactRepositoryMock = mock(ContactRepository.class);
		contactBookService = new ContactBookServiceImpl(contactRepositoryMock);
	}

	@Rule
	public ExpectedException exception = ExpectedException.none();

	private ContactVO getContactVO(){
		ContactVO contact = new ContactVO();
		contact.setEmail("testemail");
		contact.setName("testuser");
		contact.setLastName("lastName");
		PhoneNumberVO phoneNumberVO = new PhoneNumberVO();
		phoneNumberVO.setPhone("111");
		phoneNumberVO.setPhoneNoType(PhoneNoType.WORK);
		contact.setPhoneNumbers(Arrays.asList(phoneNumberVO));
		return contact;
	}
	
	private Contact getContact(){
		PhoneNumber p = new PhoneNumber();
		p.setId(1);
		p.setPhone("111");
		p.setPhoneNoType(PhoneNoType.WORK);
		return new Contact(1, "testemail", "testuser", "lastName", Arrays.asList(p));
	}
	
	@Test
	public void testDeleteContactThrowExceptionWhenEmailDoesNotExist(){
		when(contactRepositoryMock.findContactByEmail("testemail")).thenReturn(null);
		exception.expect(InvalidContactException.class);
		contactBookService.deleteContact("testemail");
	}
	
	@Test
	public void testCreateContactThrowExceptionWhenEmailExist(){
		ContactVO contact = getContactVO();
		Contact c =  getContact();
		when(contactRepositoryMock.findContactByEmail("testemail")).thenReturn(c);
		exception.expect(InvalidContactException.class);
		contactBookService.createContact(contact);
	}
	
	@Test
	public void testCreateContactSaveSuccess(){
		ContactVO contact = getContactVO();
		when(contactRepositoryMock.findContactByEmail("testemail")).thenReturn(null);
		Contact c =  getContact();
		when(contactRepositoryMock.save(any(Contact.class))).thenReturn(c);
		contactBookService.createContact(contact);
		
	}
}
