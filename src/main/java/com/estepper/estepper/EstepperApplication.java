package com.estepper.estepper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.estepper.estepper.service.PythonService;
import com.estepper.estepper.service.PythonServiceFactory;

@SpringBootApplication
public class EstepperApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstepperApplication.class, args);
	}

	@Bean(name = "PythonServiceFactory")
	public PythonServiceFactory PythonFactory() {
		return new PythonServiceFactory();
	}

	@Bean(name = "PythonServiceImpl")
	public PythonService PythonServiceImpl() throws Exception {
		return PythonFactory().getObject();
	}

}
