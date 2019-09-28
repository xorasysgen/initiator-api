package ai.wealth.boot.initiator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ai.wealth.boot.initiator.model.ServerStatus;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/wealth-api")
public class PrimaryController {
	
	@ResponseBody
	@ApiOperation(value = "Get Server information", notes="no addition parameters required", response=ServerStatus.class)
	@GetMapping("/info")
	public ServerStatus root() {
		return new ServerStatus();
	}
	
	
	@ResponseBody
	@ApiOperation(value = "say hi", notes="addition parameters required", response=String.class)
	@GetMapping("/{name}")
	public String hi(@PathVariable("name") String name)  {
		return "Hello " +  name;
	}
//https://api.wealthkernel.com/swagger/v2/index.html

}
