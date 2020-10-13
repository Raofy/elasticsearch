package com.ryan.springdatajedis;

import com.ryan.springdatajedis.entity.Elastic;
import com.ryan.springdatajedis.service.ElasticService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;

import java.util.Arrays;
import java.util.Optional;

@SpringBootApplication(exclude = {ElasticsearchDataAutoConfiguration.class})
public class Application {

}
