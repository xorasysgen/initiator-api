package ai.wealth.boot.initiator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import ai.wealth.boot.initiator.model.ServerStatus;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class PrimaryController {
//https://api.wealthkernel.com/swagger/v2/index.html
	
	@ResponseBody
	@ApiOperation(value = "Get Server information", notes="no addition parameters required", response=ServerStatus.class)
	@GetMapping("/info")
	public ServerStatus root() {
		return new ServerStatus();
	}
	
	
	@ResponseBody
	@ApiOperation(value = "say hi", notes="addition parameters required", response=String.class)
	@GetMapping("/{name}")
	@HystrixCommand(fallbackMethod = "fallbackMethodFailure")
	public String hi(@PathVariable("name") String name)  {
		if(name!=null && !name.equals("name"))
		return "Hello " +  name;
		else
			throw new RuntimeException("Name should not be null");
	}
	
	public String fallbackMethodFailure(@PathVariable("name") String name) {
		return "Home failue due to Name should not be null";
	}

}
