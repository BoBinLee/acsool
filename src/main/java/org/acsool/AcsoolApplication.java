package org.acsool;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@SpringBootApplication
public class AcsoolApplication extends SpringBootServletInitializer {
	@Value("${cloudinary.name}")
	String cloudName;
	@Value("${cloudinary.key}")
	String cloudKey;
	@Value("${cloudinary.secret}")
	String cloudSecret;
	
	public static void main(String[] args) {
		SpringApplication.run(AcsoolApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AcsoolApplication.class);
	}
	
	@Bean
	public Cloudinary cloudinary(){
		return new Cloudinary(ObjectUtils.asMap(
				  "cloud_name", cloudName,
				  "api_key", cloudKey,
				  "api_secret", cloudSecret));
	}
}
