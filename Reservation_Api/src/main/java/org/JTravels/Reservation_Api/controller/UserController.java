package org.JTravels.Reservation_Api.controller;

import org.JTravels.Reservation_Api.Dto.User;
import org.JTravels.Reservation_Api.Dto.ResponseStructure;
import org.JTravels.Reservation_Api.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController {
	@Autowired
	private Userservice ser;
	@PostMapping(value = "/user")
	public ResponseEntity<ResponseStructure<User>> save(@RequestBody User ad) {
		return ser.save(ad);
	}

	@PutMapping(value = "/user")
	public ResponseEntity<ResponseStructure<User>> Upadte(@RequestBody User ad) {
		return ser.Update(ad);
	}

	@GetMapping(value = "/user/verifyphone")
	public ResponseEntity<ResponseStructure<User>> verifyUser(@RequestParam long phone,
			@RequestParam String password) {
		return ser.verifyUser(phone, password);
	}

	@GetMapping(value = "/user/verifyemail")
	public ResponseEntity<ResponseStructure<User>> verifyUser(@RequestParam String email,
			@RequestParam String password) {
		return ser.verifyUser(email, password);
	}
	@GetMapping(value = "/user/{id}")
	public ResponseEntity<ResponseStructure<User>> findbyid(@PathVariable int id) {
		return ser.findbyid(id);
	}
	

}
