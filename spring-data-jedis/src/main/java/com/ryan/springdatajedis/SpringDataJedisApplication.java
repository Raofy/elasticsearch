package com.ryan.springdatajedis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;

@SpringBootApplication(exclude = {ElasticsearchDataAutoConfiguration.class})
public class SpringDataJedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJedisApplication.class, args);
    }

}
