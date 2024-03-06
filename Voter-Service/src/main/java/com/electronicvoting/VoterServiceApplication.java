package com.electronicvoting;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
@SpringBootApplication
@EnableDiscoveryClient
public class VoterServiceApplication {
	@Bean
	WebClient.Builder getWebClient()
	{
		return WebClient.builder();
	}

	public static void main(String[] args) {
		SpringApplication.run(VoterServiceApplication.class, args);
	}

}
