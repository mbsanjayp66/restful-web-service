package com.sanjay.rest.webservice.restfulwebservice.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanjay.rest.webservice.restfulwebservice.User.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
