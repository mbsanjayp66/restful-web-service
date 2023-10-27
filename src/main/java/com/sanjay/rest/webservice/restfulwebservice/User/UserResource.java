package com.sanjay.rest.webservice.restfulwebservice.User;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserResource {
	
	@Autowired
	UserDaoService userDaoService;
	
	@GetMapping("/user")
	public List<User>retrieveAllUser(){
		return userDaoService.findAll();
	}
	
	@GetMapping("/user/{id}")
	public EntityModel<User> findById(@PathVariable int id){
		User usr=  userDaoService.findById(id);
		if(usr==null) {
			throw new UserNotFoundException("id:"+id);
		}
		EntityModel<User>entityModel = EntityModel.of(usr);
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUser());
		entityModel.add(link.withRel("all-user"));
		return entityModel;
	}
	
	@DeleteMapping("/user/{id}")
	public void deleteById(@PathVariable int id){
		userDaoService.DeleteById(id);
	}
	
	@PostMapping("/user")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User u1= userDaoService.saveUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(u1.getId());
		return ResponseEntity.created(location).build();
	}
}
