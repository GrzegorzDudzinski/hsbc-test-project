package com.hsbc.testproject.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.hsbc.testproject"})
public class WebAppInitializer{

    public static void main(String[] args) throws Exception{
        SpringApplication.run(WebAppInitializer.class, args);	    
    }

}

