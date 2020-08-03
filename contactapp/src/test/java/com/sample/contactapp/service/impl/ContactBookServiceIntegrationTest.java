package com.sample.contactapp.service.impl;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.sample.contactapp.App;
import com.sample.contactapp.api.ContactVO;
import com.sample.contactapp.api.PageRequestVO;
import com.sample.contactapp.api.PhoneNumberVO;
import com.sample.contactapp.entity.PhoneNoType;
import com.sample.contactapp.exception.InvalidContactException;
import com.sample.contactapp.repository.ContactRepository;
import com.sample.contactapp.service.ContactBookService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=App.class,webEnvironment = WebEnvironment.RANDOM_PORT)
public class ContactBookServiceIntegrationTest {

	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private ContactBookService contactBookService;

	
	@Before
	public void onSetup() {
		contactRepository.deleteAll();
	}

	@After
	public void onTeardown() {
		contactRepository.deleteAll();
	}
	
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
	
	@Test
	public void testCreateContactSave(){
		contactBookService.createContact(getContactVO());
		assertThat(contactRepository.count(), is(1L));
	}
	
	@Test
	public void testCreateContactThrowExceptionWhenEmailExist(){
		ContactVO contact = getContactVO();
		contactBookService.createContact(contact);
		exception.expect(InvalidContactException.class);
		contactBookService.createContact(contact);
	}
	
	@Test
	public void testUpdateContactThrowExceptionWhenEmailNotExist(){
		ContactVO contact = getContactVO();
		exception.expect(InvalidContactException.class);
		contactBookService.updateContact(contact);
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testDeleteContactThrowExceptionWhenEmailDoesNotExist(){
		exception.expect(InvalidContactException.class);
		contactBookService.deleteContact("emailnotexist");
	}
	
	@Test
	public void testGetContactMethod(){
		ContactVO c = getContactVO();
		contactBookService.createContact(c);
		c = getContactVO();
		c.setEmail("testemail2");
		contactBookService.createContact(c);
		assertThat(contactBookService.getContact("testemail", null, new PageRequestVO(1, 10)).size(), is(1));
		assertThat(contactBookService.getContact(null, "testuser", new PageRequestVO(1, 10)).size(), is(2));
		assertThat(contactBookService.getContact("testemail", null, new PageRequestVO(2, 10)).size(), is(0));
		assertThat(contactBookService.getContact(null, null, new PageRequestVO(1, 10)).size(), is(2));
	}
	
	@Test
	public void testSeachContactMethod(){
		ContactVO c = getContactVO();
		contactBookService.createContact(c);
		c = getContactVO();
		c.setEmail("testemail2");
		contactBookService.createContact(c);
		assertThat(contactBookService.searchContact("test", null, new PageRequestVO(1, 10)).size(), is(2));
		assertThat(contactBookService.searchContact(null, "user", new PageRequestVO(1, 10)).size(), is(2));
		assertThat(contactBookService.searchContact("test1", null, new PageRequestVO(1, 10)).size(), is(0));
		assertThat(contactBookService.searchContact(null, null, new PageRequestVO(1, 10)).size(), is(2));
	}
	
	
}
