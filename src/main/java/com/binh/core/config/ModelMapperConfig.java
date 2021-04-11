package com.binh.core.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ModelMapperConfig {
	@Bean
    @Primary
    public ModelMapper accountMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }
}
