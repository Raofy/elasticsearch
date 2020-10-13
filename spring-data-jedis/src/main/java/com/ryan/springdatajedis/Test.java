package com.ryan.springdatajedis;

import com.ryan.springdatajedis.entity.Elastic;
import com.ryan.springdatajedis.service.ElasticService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class Test {

    @Autowired
    private ElasticService elasticService;

    @org.junit.Test
    public void contextLoads() {
    }

    @org.junit.Test
    public void elasticTestInsert() {
        Elastic info = new Elastic();
        info.setId(3);
        info.setName("Sam");
        info.setDesc("山姆高德!");
        info.setEmail("389312123@qq.com");
        elasticService.save(info);
    }

    // 这里要注意，如果使用 save 方法来更新的话，必须是全量字段（全部的字段都要写上去），否则其它字段会被覆盖。
    @org.junit.Test
    public void elasticTestUpdate() {
        Elastic info = new Elastic();
        info.setId(2);
        info.setName("John");
        info.setDesc("我是John!");
        info.setEmail("952536180@qq.com");
        elasticService.save(info);
    }

    @org.junit.Test
    public void elasticTestDelete() {
        elasticService.deleteById(3);
    }

    @org.junit.Test
    public void elasticTestSelectById() {
        Optional<Elastic> user = elasticService.findById(1);
        System.out.println(user.toString());
    }

    @org.junit.Test
    public void elasticTestSelectByIds() {
        Iterable<Elastic> user = elasticService.findAllById(Arrays.asList(1, 2));
        user.forEach(System.out::println);
    }
}


