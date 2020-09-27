package com.ryan.springdatajedis.service;


import com.ryan.springdatajedis.entity.Elastic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticService extends ElasticsearchRepository<Elastic, Integer> {
}
