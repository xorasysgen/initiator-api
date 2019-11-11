package ai.wealth.boot.initiator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ai.wealth.boot.initiator.dto.ServerStatus;
/**************************************************************************
@SpringBootApplication = @Configuration + @ComponentScan + @EnableAutoConfiguration
@Configuration to enable Java-based configuration. 
@ComponentScan to enable component scanning, All the@Controller classes you write are discovered by this annotation.  
@EnableAutoConfiguration to enable Spring Boot's auto-configuration feature.
@SpringBootApplication is a 3-in-1 annotation that combines the functionality of @Configuration, @ComponentScan, and @EnableAutoConfiguration.
****************************************************************************/
import springfox.documentation.swagger2.annotations.EnableSwagger2;

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
@EnableSwagger2
@EnableHystrix
@EnableHystrixDashboard
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
public class InitiatorApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(InitiatorApiApplication.class, args);
	}
	

	@Bean
	@Description("Message source")
	public ResourceBundleMessageSource messageSource() {
	    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	    messageSource.setBasename("Messages");
	    return messageSource;
	}
	
	@RequestMapping("/")
	@ResponseBody
	ServerStatus root() {
		return new ServerStatus();
	}
		

}
