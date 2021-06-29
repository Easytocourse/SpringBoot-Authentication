package com.easytocourse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.easytocourse.model.User;
import com.easytocourse.repository.UserRepository;

@RestController
public class AppController {

	@Autowired
	private UserRepository userRepo;

	@GetMapping("")
	public String viewHomePage() {
		return "Welcome to Home page";
	}

	@PostMapping("/save")
	public String save(@RequestBody User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);

		userRepo.save(user);

		return "saved";
	}

	@PostMapping("/register")
	public String processRegister(@RequestBody User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);

		userRepo.save(user);

		return "register_success";
	}

	@GetMapping("/allusers")
	public List<User> allUsers() {
		return userRepo.findAll();

	}

	@GetMapping("/users")
	public List<User> listUsers() {
		return userRepo.findAll();

	}

	@GetMapping("/admin")
	public String secured() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString() + "  else";
		}

		return username;

	}

}