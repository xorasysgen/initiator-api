package ai.wealth.boot.initiator.health;

import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.MeterRegistry;

@Configuration
public class MicrometerConfiguration {

	
	@Bean
	MeterRegistryCustomizer<?> meterRegistryCustomizer(MeterRegistry meterRegistry){
		return meterRegistry_1 ->{
			meterRegistry.config()
			.commonTags("application","micrometer-custom");
			
			
		};
	}
}
