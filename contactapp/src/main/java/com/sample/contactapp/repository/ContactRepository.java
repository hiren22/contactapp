package com.sample.contactapp.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.sample.contactapp.entity.Contact;


public interface ContactRepository extends PagingAndSortingRepository<Contact, Integer> {
	
	
	public Contact findContactByEmail(String email);
	
	@Query("SELECT c FROM contact c WHERE (:name is null or c.name = :name) and (:email is null"
			  + " or c.email = :email)")
	public List<Contact> findCustomerByNameAndEmail(@Param("name") String name, @Param("email") String email, Pageable pageable);

	@Query("SELECT c FROM contact c WHERE (:name is null or c.name like '%'||:name||'%') and (:email is null"
			  + " or c.email like '%'||:email||'%')")
	public List<Contact> searchCustomerByNameAndEmail(@Param("name") String name, @Param("email") String email, Pageable pageable);

}
