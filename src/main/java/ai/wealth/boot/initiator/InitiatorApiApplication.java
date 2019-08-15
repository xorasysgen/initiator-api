package ai.wealth.boot.initiator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ai.wealth.boot.initiator.model.ServerStatus;
/**************************************************************************
@SpringBootApplication = @Configuration + @ComponentScan + @EnableAutoConfiguration
@Configuration to enable Java-based configuration. 
@ComponentScan to enable component scanning, All the@Controller classes you write are discovered by this annotation.  
@EnableAutoConfiguration to enable Spring Boot's auto-configuration feature.
@SpringBootApplication is a 3-in-1 annotation that combines the functionality of @Configuration, @ComponentScan, and @EnableAutoConfiguration.
****************************************************************************/

/************************************************************************************************
@Scope=singleton #Scopes a single bean definition to a single object instance per Spring IoC container.
@Scope=prototype #Scopes a single bean definition to any number of object instances.
@Scope=request   #Scopes a single bean definition to the lifecycle of a single HTTP request; that is each and every HTTP request will have its own instance of a bean created off the back of a single bean definition. Only valid in the context of a web-aware Spring ApplicationContext.
@Scope=session   #Scopes a single bean definition to the lifecycle of a HTTP Session. Only valid in the context of a web-aware Spring ApplicationContext.
@Scope=global session #Scopes a single bean definition to the lifecycle of a global HTTP Session. Typically only valid when used in a portlet context. Only valid in the context of a web-aware Spring ApplicationContext.used in portlet context.

@RestController is a specialized version of the controller. 
It includes the @Controller and @ResponseBody annotations and
as a result, simplifies the controller implementation:
*************************************************************************************************/
@RestController
@SpringBootApplication
public class InitiatorApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(InitiatorApiApplication.class, args);
	}
	
	
	@RequestMapping("/")
	@ResponseBody
	ServerStatus root() {
		return new ServerStatus();
	}
		

}
