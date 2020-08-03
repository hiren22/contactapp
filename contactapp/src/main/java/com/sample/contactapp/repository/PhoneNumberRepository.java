package com.sample.contactapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.contactapp.entity.PhoneNumber;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Integer> {

}
