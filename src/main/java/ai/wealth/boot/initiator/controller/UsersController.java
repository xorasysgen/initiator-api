package ai.wealth.boot.initiator.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ai.wealth.boot.initiator.configuration.security.model.Users;
import ai.wealth.boot.initiator.dto.User;
import ai.wealth.boot.initiator.dto.UsersDetail;
import ai.wealth.boot.initiator.exception.CommonException;
import ai.wealth.boot.initiator.service.UserService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class UsersController {
//https://spring.io/guides/tutorials/rest/
	@Autowired
	private UserService userService;
	
	
	private final UserService userService2;
	
	

	@Autowired
	public UsersController(UserService userService2) {
		this.userService2 = userService2;
	}

	@PostMapping("/users")
	@ApiOperation(value = "createUser", notes="create user", response=String.class)
	public String createUser(@RequestBody @Valid Users users) {
		System.out.println(users);
		Users retrivedObject=userService.createUserService(users);
		return "User created with user name: " + retrivedObject.getUsername();
	}

	@GetMapping("/users")
	@ApiOperation(value = "list_of_Users", notes="list of users", response=UsersDetail.class)
	public UsersDetail getAllUsers() {
		UsersDetail usersDetail=new UsersDetail();
		List<User> user= (userService.getUsers().stream().map(p-> new User(p)).collect(Collectors.toList()));
		usersDetail.setUser(user);
		return usersDetail;
	}

	@GetMapping("/users/{userName}")
	@ApiOperation(value = "getUserByUserName", notes="user detail", response=User.class)
	public User getUser(@PathVariable("userName") String userName) {
		Optional<Users> user=userService2.getUserByUserName(userName);
		user.orElseThrow(() -> new CommonException("User " + userName + " does not exist"));
		return user.map(User::new).get();
	}

	@PutMapping("/users/{userId}")
	@ApiOperation(value = "update user By UserId", notes="Update user detail", response=User.class)
	public String updateUser(@RequestBody Users user, @PathVariable("userId") String userName) {
		userService.updateUserService(user, userName);
		return "user updated successfully!";
	}
	
	@DeleteMapping("/users/{userId}")
	@ApiOperation(value = "delete user User Id", notes="delete user", response=User.class)
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
