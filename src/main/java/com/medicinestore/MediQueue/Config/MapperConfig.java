package com.medicinestore.MediQueue.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }

    @Bean
    public RestClient client(){
        return RestClient.create();
    }
}
