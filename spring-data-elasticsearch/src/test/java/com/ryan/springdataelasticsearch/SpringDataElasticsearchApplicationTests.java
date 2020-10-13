package com.ryan.springdataelasticsearch;

import com.ryan.springdataelasticsearch.entity.Elastic;
import com.ryan.springdataelasticsearch.service.ElasticService;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.yaml.snakeyaml.util.ArrayUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class SpringDataElasticsearchApplicationTests {

    @Autowired
    private ElasticService elasticService;

    @Test
    public void contextLoads() {
    }

    @Test
    void blukInsert() {
        for (int i = 40; i < 50; i++) {
            Elastic info = new Elastic();
            info.setId(i);
            info.setCid(2);
            info.setName("fuyi" + i);
            info.setDesc("我是fuyi");
            info.setEmail("938797933@qq.com");
            elasticService.save(info);
        }
    }
    @Test
    public void elasticTestInsert() {
        Elastic info = new Elastic();
        info.setId(4);
        info.setName("Jane");
        info.setDesc("大家好!我是Jane");
        info.setEmail("1463583723@qq.com");
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

    @Test
    void testFindByName() {
        Elastic user = elasticService.findByName("fuyi");
        System.out.println(user);
    }

    @Test
    void testFindByNameLike() {

        Sort list = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(0, 2, list);
        Page<Elastic> users = elasticService.findByNameLike("Jane", pageRequest);
        users.forEach(System.out::println);
    }

    /**
     * 通过QueryBuilder 和 SearchQuery 构建相对复杂的搜索和排序条件
     *
     */
    @Test
    void testComplexSearch() {
        Page<Elastic> search = elasticService.search(0, "Jane",
                PageRequest.of(0, 5, Sort.Direction.ASC, "id"));
        System.out.println(search.getTotalElements());
        search.forEach(System.out::println);
    }


    /**
     * Elasticsearch 操作模板   ElasticsearchTemplate（待测试）
     *
     */
    @Resource
    private ElasticsearchRestTemplate elasticsearchTemplate;

    @Test
    public void test() {
        // <1> 创建 ES 搜索条件
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        // <2> 筛选
        nativeSearchQueryBuilder.withQuery(QueryBuilders.multiMatchQuery("fuyi",
                "name"));
        // <3> 聚合
        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("cids").field("cid")); // 分类

        // <4> 执行查询
        SearchHits<Elastic> search = elasticsearchTemplate.search(nativeSearchQueryBuilder.build(), Elastic.class);
        ParsedLongTerms cids = search.getAggregations().get("cids");

        if (cids != null) {
            List result = new ArrayList<>();
            cids.getBuckets().forEach(item -> result.add(item));
            // <5> 后续遍历 condition.categories 数组，查询商品分类，设置商品分类名。
            result.forEach(System.out::println);
        }



    }

}
