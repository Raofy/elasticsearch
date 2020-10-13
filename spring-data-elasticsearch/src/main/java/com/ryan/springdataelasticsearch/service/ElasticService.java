package com.ryan.springdataelasticsearch.service;


import com.ryan.springdataelasticsearch.entity.Elastic;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.util.StringUtils;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;


public interface ElasticService extends ElasticsearchRepository<Elastic, Integer> {

    Elastic findByName(String name);

    Page<Elastic> findByNameLike(String name, Pageable pageable);

    default Page<Elastic> search(Integer id, String keyword, Pageable pageable) {

        //创建查询构造器对象
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();

        //筛选ID
        if (id != null) {
            nativeSearchQueryBuilder.withFilter(QueryBuilders.termQuery("cid", id));
        }

        // <2.2> 筛选
        if (StringUtils.hasText(keyword)) {
            FunctionScoreQueryBuilder.FilterFunctionBuilder[] functions = { // TODO 芋艿，分值随便打的
                    new FunctionScoreQueryBuilder.FilterFunctionBuilder(matchQuery("name", keyword),
                            ScoreFunctionBuilders.weightFactorFunction(10)),
                    new FunctionScoreQueryBuilder.FilterFunctionBuilder(matchQuery("desc", keyword),
                            ScoreFunctionBuilders.weightFactorFunction(2)),
                    new FunctionScoreQueryBuilder.FilterFunctionBuilder(matchQuery("email", keyword),
                            ScoreFunctionBuilders.weightFactorFunction(3)),
            };
            FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(functions)
                    .scoreMode(FunctionScoreQuery.ScoreMode.SUM) // 求和
                    .setMinScore(2F); // TODO 需要考虑下 score
            nativeSearchQueryBuilder.withQuery(functionScoreQueryBuilder);
        }
        // 排序
        if (StringUtils.hasText(keyword)) { // <3.1> 关键字，使用打分
            nativeSearchQueryBuilder.withSort(SortBuilders.scoreSort().order(SortOrder.DESC));
        } else if (pageable.getSort().isSorted()) { // <3.2> 有排序，则进行拼接
            pageable.getSort().get().forEach(sortField -> nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort(sortField.getProperty())
                    .order(sortField.getDirection().isAscending() ? SortOrder.ASC : SortOrder.DESC)));
        } else { // <3.3> 无排序，则按照 ID 倒序
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("id").order(SortOrder.DESC));
        }
        // <4> 分页
        nativeSearchQueryBuilder.withPageable(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize())); // 避免
        // <5> 执行查询
        return search(nativeSearchQueryBuilder.build());

    }
}
