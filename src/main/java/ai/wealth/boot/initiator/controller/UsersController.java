package ai.wealth.boot.initiator.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ai.wealth.boot.initiator.configuration.security.model.Users;
import ai.wealth.boot.initiator.controller.service.UserService;

@RestController
@RequestMapping("/users-endpoint")
public class UsersController {
//https://spring.io/guides/tutorials/rest/
	@Autowired
	private UserService userService;

	@PostMapping("/users/")
	public String createUser(@RequestBody @Valid Users users) {
		System.out.println(users);
		Users retrivedObject=userService.createUserService(users);
		return "User created with user name: " + retrivedObject.getUsername();
	}

	@GetMapping("/users")
	public List<Users> getAllUsers() {
		return userService.getUsers();
	}

	@GetMapping("/users/{userName}")
	public ResponseEntity<Users> getUser(@PathVariable("userName") String userName) {
		return ResponseEntity.ok(userService.getUserByUserName(userName));
	}

	@PutMapping("/users/{userId}")
	public String updateUser(@RequestBody Users user, @PathVariable("userId") String userId) {
		return "";
	}
	
	@DeleteMapping("/users/{userId}")
	public String deleteUser(@PathVariable("userId") Integer userId) {
		userService.deleteUserService(userId);
		return userId + " Deleted";
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
