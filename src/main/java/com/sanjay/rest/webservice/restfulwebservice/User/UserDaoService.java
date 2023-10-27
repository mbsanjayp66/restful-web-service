package com.sanjay.rest.webservice.restfulwebservice.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	private static List<User>users = new ArrayList<>();
	private static int  count = 0;
	static {
		users.add(new User(++count, "Sanjay", LocalDate.now().minusYears(20)));
		users.add(new User(++count, "Animesh", LocalDate.now().minusYears(19)));
		users.add(new User(++count, "Pankaj", LocalDate.now().minusYears(22)));
	}
	
	public List<User>findAll(){
		return users;
	}

	public User findById(int id) {
		return users.stream().filter(user->{
			return user.getId()==id;
		}).findFirst().orElse(null);
	}
	
	public void DeleteById(int id) {
		users.removeIf(user->user.getId().equals(id));
	}
	
	public User saveUser(User user) {
		user.setId(++count);
		users.add(user);
		return user;
	}
	
	
}
