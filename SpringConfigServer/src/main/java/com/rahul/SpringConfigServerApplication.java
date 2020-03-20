package com.rahul;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableConfigServer
@RestController
public class SpringConfigServerApplication {

	@Autowired
	Environment env;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringConfigServerApplication.class, args);
	}

	@RequestMapping("/")
    public String home() {
        return "Host is  => " +env ;
    }
}
