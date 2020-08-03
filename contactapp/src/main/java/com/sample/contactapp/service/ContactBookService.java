package com.sample.contactapp.service;

import java.util.List;

import com.sample.contactapp.api.ContactVO;
import com.sample.contactapp.api.PageRequestVO;

public interface ContactBookService {

	public void createContact(ContactVO contact);
	
	public List<ContactVO> getContact(String email, String name, PageRequestVO pageRequest);
	
	public List<ContactVO> searchContact(String email, String name, PageRequestVO pageRequest);

	public void updateContact(ContactVO contact);
	
	public void deleteContact(String email);
	
}
