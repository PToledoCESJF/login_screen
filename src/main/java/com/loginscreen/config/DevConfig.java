package com.loginscreen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class DevConfig {

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        return true;
    }
}
