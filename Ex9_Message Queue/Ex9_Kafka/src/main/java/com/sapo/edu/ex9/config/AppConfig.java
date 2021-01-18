package com.sapo.edu.ex9.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    /*@Bean
    public KafkaTemplate kafkaTemplate(){
        return new KafkaTemplate(() -> {
            return null;
        });
    }*/
}
