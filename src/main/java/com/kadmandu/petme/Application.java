package com.kadmandu.petme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Base class to run this app as a spring boot app.
 * 
 * @author German Potes
 */
@EnableAutoConfiguration
@Configuration
@ComponentScan
@SuppressWarnings("PMD.UseUtilityClass")
public class Application
{

    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }
}