package com.sample.contactapp.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.sample.contactapp.api.ContactVO;
import com.sample.contactapp.api.PageRequestVO;
import com.sample.contactapp.entity.Contact;
import com.sample.contactapp.exception.InvalidContactException;
import com.sample.contactapp.repository.ContactRepository;
import com.sample.contactapp.service.ContactBookService;

import constants.AppConstant;

@Service
public class ContactBookServiceImpl implements ContactBookService{

	private static final Logger LOGGER = LoggerFactory.getLogger(ContactBookServiceImpl.class);

	private ContactRepository contactRepository;
	
	@Autowired
	public ContactBookServiceImpl(ContactRepository c){
		contactRepository = c;
	}
	
	public void createContact(ContactVO contact) {
		if(contactRepository.findContactByEmail(contact.getEmail())!=null){
			LOGGER.info("Trying to create contact {} which same email", contact);
			throw new InvalidContactException(AppConstant.EMAIL_EXIST_EXCEPTION);
		}
		contactRepository.save(new Contact(contact));
	}
	
	public void deleteContact(String email){
		Contact contact = contactRepository.findContactByEmail(email);
		if(contact == null){
			LOGGER.info("Trying to delete contact {} which does not exist", contact);
			throw new InvalidContactException(AppConstant.CONTACT_DOES_NOT_EXIST_EXCEPTION);
		}
		contactRepository.delete(contact);
	}
	
	
	public void updateContact(ContactVO contact) 
	{
		Contact c = contactRepository.findContactByEmail(contact.getEmail());
		if(c ==null){
			LOGGER.info("Trying to update contact {} which does not exist", contact);
			throw new InvalidContactException(AppConstant.CONTACT_DOES_NOT_EXIST_EXCEPTION);
		}
		contactRepository.delete(c);
		contactRepository.save(new Contact(contact));
	}

	public List<ContactVO> getContact(String email, String name, PageRequestVO pageRequest) {
		LOGGER.info("Searching for contact with email {}, name {}, page {}", email, name, pageRequest);
		List<Contact> contacts = contactRepository.findCustomerByNameAndEmail(name, email, new PageRequest(
				pageRequest.getPage()-1,pageRequest.getSize()));
		return ContactVO.ofList(contacts);
		 
	}

	public List<ContactVO> searchContact(String email, String name,
			PageRequestVO pageRequest) {
		LOGGER.info("Searching for contact with email {}, name {}, page {}", email, name, pageRequest);
		List<Contact> contacts = contactRepository.searchCustomerByNameAndEmail(name, email, new PageRequest(
				pageRequest.getPage()-1,pageRequest.getSize()));
		return ContactVO.ofList(contacts);
	}

}
