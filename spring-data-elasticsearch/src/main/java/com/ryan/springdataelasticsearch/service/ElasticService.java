package com.ryan.springdataelasticsearch.service;


import com.ryan.springdataelasticsearch.entity.Elastic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface ElasticService extends ElasticsearchRepository<Elastic, Integer> {
}
