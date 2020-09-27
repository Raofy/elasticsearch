package com.ryan.springdataelasticsearch;

import com.ryan.springdataelasticsearch.entity.Elastic;
import com.ryan.springdataelasticsearch.service.ElasticService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.yaml.snakeyaml.util.ArrayUtils;

import java.util.Arrays;
import java.util.Optional;

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

    // 这里要注意，如果使用 save 方法来更新的话，必须是全量字段（全部的字段都要写上去），否则其它字段会被覆盖。
    @Test
    public void elasticTestUpdate() {
        Elastic info = new Elastic();
        info.setId(2);
        info.setName("Tom");
        info.setDesc("我是汤姆!");
        info.setEmail("942536180@qq.com");
        elasticService.save(info);
    }

    @Test
    public void elasticTestDelete() {
       elasticService.deleteById(3);
    }

    @Test
    public void elasticTestSelectById() {
        Optional<Elastic> user = elasticService.findById(1);
        System.out.println(user.toString());
    }

    @Test
    public void elasticTestSelectByIds() {
        Iterable<Elastic> user = elasticService.findAllById(Arrays.asList(1, 2));
        user.forEach(System.out::println);
    }
}
