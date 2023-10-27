package com.sanjay.rest.webservice.restfulwebservice.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanjay.rest.webservice.restfulwebservice.User.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
