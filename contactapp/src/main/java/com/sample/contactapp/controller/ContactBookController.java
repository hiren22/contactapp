package com.sample.contactapp.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.contactapp.api.ContactVO;
import com.sample.contactapp.api.PageRequestVO;
import com.sample.contactapp.service.ContactBookService;

import constants.AppConstant;

@RequestMapping("/rest/contactapp/v1/")
@RestController
public class ContactBookController {

	@Autowired
	private ContactBookService contactBookService;
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes="application/json")	  
	public void createContact(@Valid @RequestBody ContactVO contact){
		contactBookService.createContact(contact);
	}
	
	@RequestMapping(value="contacts/{email}",method = RequestMethod.DELETE)	  
	public void deleteContact(@NotNull @PathVariable("email") String email){
		contactBookService.deleteContact(email);
	}
	
	@RequestMapping(method = RequestMethod.PUT, produces = "application/json", consumes="application/json")	  
	public void updateContact(@Valid @RequestBody ContactVO contact){
		contactBookService.updateContact(contact);
	}
	
	@RequestMapping(value="contacts",method = RequestMethod.GET, produces = "application/json")	  
	public List<ContactVO> getContact(@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "email", required = false) String email,
			@RequestParam(name = "page", required = false, defaultValue = "1") int page){
		return contactBookService.getContact(email, name, new PageRequestVO(page, AppConstant.PAGE_SIZE));
	}
	
	@RequestMapping(value="contacts/search",method = RequestMethod.GET, produces = "application/json")	  
	public List<ContactVO> searchContact(@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "email", required = false) String email,
			@RequestParam(name = "page", required = false, defaultValue = "1") int page){
		return contactBookService.searchContact(email, name, new PageRequestVO(page, AppConstant.PAGE_SIZE));
	}
	
}
