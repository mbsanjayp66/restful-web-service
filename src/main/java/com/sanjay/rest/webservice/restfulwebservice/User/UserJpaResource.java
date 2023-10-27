package com.sanjay.rest.webservice.restfulwebservice.User;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import com.sanjay.rest.webservice.restfulwebservice.jpa.PostRepository;
import com.sanjay.rest.webservice.restfulwebservice.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserJpaResource {
	
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PostRepository postRepository;
	
	@GetMapping("/Jpa/user")
	public List<User>retrieveAllUser(){
		return userRepository.findAll();
	}
	
	@GetMapping("/Jpa/user/{id}")
	public EntityModel<User> findById(@PathVariable int id){
		Optional<User> usr=  userRepository.findById(id);
		if(usr==null) {
			throw new UserNotFoundException("id:"+id);
		}
		EntityModel<User>entityModel = EntityModel.of(usr.get());
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUser());
		entityModel.add(link.withRel("all-user"));
		return entityModel;
	}
	
	@GetMapping("/Jpa/user/{id}/post")
	public List<Post> getPost(@PathVariable int id){
		Optional<User> usr=  userRepository.findById(id);
		if(usr==null) {
			throw new UserNotFoundException("id:"+id);
		}
		return usr.get().getPosts();
	}
	
	
	@DeleteMapping("/Jpa/user/{id}")
	public void deleteById(@PathVariable int id){
		userRepository.deleteById(id);
	}
	
	@PostMapping("/Jpa/user")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User u1= userRepository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(u1.getId());
		return ResponseEntity.created(location).build();
	}
	
	@PostMapping("/Jpa/user/{id}/post")
	public ResponseEntity<Object> createPost(@PathVariable int id,@Valid @RequestBody Post post){
		Optional<User> usr=  userRepository.findById(id);
		if(usr==null) {
			throw new UserNotFoundException("id:"+id);
		}
		post.setUser(usr.get());
		Post p = postRepository.save(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(p.getId());
		return ResponseEntity.created(location).build();
	}
}
