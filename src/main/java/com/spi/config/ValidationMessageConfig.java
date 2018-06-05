
package com.spi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({ "classpath:/validationMessages.properties" })
public class ValidationMessageConfig {

	

}
