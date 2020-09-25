package com.ryan.springdataelasticsearch;

import com.ryan.springdataelasticsearch.entity.Elastic;
import com.ryan.springdataelasticsearch.service.ElasticService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringDataElasticsearchApplicationTests {

    @Autowired
    private ElasticService elasticService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void elasticTestInsert() {
        Elastic info = new Elastic();
        info.setId(1);
        info.setName("fuyi");
        info.setDesc("I am fuyi!");
        info.setEmail("12312312123@qq.com");
        elasticService.save(info);
    }
}
