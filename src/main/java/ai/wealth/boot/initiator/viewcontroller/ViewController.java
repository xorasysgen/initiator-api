package ai.wealth.boot.initiator.viewcontroller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {

	
	@Value("${spring.application.name}")
    String appName;
	
	@GetMapping("/default")
	public String defaultWelcome(@RequestParam(name = "message" , required=false, defaultValue="Howdy") String message, Model model, Principal principal) {
		model.addAttribute("message", message);
		model.addAttribute("greetings", "Welcome");
		model.addAttribute("appName", appName);
		model.addAttribute("user", principal.getName());
		return "default";
	}
	
	@GetMapping("index")
	public String index() {
		return "index";
	}
	
	
}
