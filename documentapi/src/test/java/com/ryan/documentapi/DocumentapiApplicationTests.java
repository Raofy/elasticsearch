package com.ryan.documentapi;

import com.alibaba.fastjson.JSONObject;
import com.ryan.documentapi.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.TermVectorsRequest;
import org.elasticsearch.client.core.TermVectorsResponse;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.document.DocumentField;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.VersionType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.index.reindex.DeleteByQueryRequestBuilder;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

import static java.util.Collections.singletonMap;

@Slf4j
@SpringBootTest
class DocumentapiApplicationTests {

    @Resource
    private RestHighLevelClient client;

    @Test
    void contextLoads() {


//        GetRequest request = new GetRequest("test", "KVigZ3QBMoi3obtijnUj");

        /**
         * Get API
         *
         */

        //1. 配置请求是否可以源搜索，默认是可以源搜索的，开启了就不返回_source结果
//            request.fetchSourceContext(FetchSourceContext.DO_NOT_FETCH_SOURCE);


        //2. 配置返回指定字段，返回学校和学生名字两个字段值
//        String[] includes = new String[]{"school", "name"};
//        String[] excludes = Strings.EMPTY_ARRAY;
//        FetchSourceContext fetchSourceContext =
//                new FetchSourceContext(true, includes, excludes);
//        request.fetchSourceContext(fetchSourceContext);
//        try {
//            GetResponse documentFields = client.get(request, RequestOptions.DEFAULT);
//            Map<String, Object> sourceMap = documentFields.getSourceAsMap();
//            for (String key : sourceMap.keySet()) {
//                System.out.println(key + ":" +sourceMap.get(key));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        //3. 返回除了school字段的其他字段内容
//        String[] includes = Strings.EMPTY_ARRAY;
//        String[] excludes = new String[]{"school"};
//        FetchSourceContext fetchSourceContext =
//                new FetchSourceContext(true, includes, excludes);
//        request.fetchSourceContext(fetchSourceContext);
//        try {
//            GetResponse documentFields = client.get(request, RequestOptions.DEFAULT);
//            Map<String, Object> sourceMap = documentFields.getSourceAsMap();
//            for (String key : sourceMap.keySet()) {
//                System.out.println(key + ":" + sourceMap.get(key));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        //4.设置路由值
//        request.routing("routing");
//
//        //5. 设置偏好值
//        request.preference("preference");
//
//        //6. 设置是否实时，默认为true
//        request.realtime(false);
//
//        //7. 设置检索文档前刷新，默认为false
//        request.refresh(true);
//
//        //8. 设置版本号
//        request.version(2);
//
//        //9. 设置版本类型
//        request.versionType(VersionType.EXTERNAL);

        //10. 上面是同步请求，也可以进行也可以以异步方式执行GetRequest，以便客户端可以直接返回。 用户需要通过将请求和侦听器传递给异步get方法来指定如何处理响应或潜在的失败：
//        ActionListener<GetResponse> listener = new ActionListener<GetResponse>() {
//            @Override
//            public void onResponse(GetResponse getResponse) {
        //获取响应方式一
//                Map<String, Object> sourceMap = getResponse.getSourceAsMap();
//                for (String key : sourceMap.keySet()) {
//                    System.out.println(key + ":" + sourceMap.get(key));
//                }

        //获取响应方式二
//                Map<String, Object> source = getResponse.getSource();
//                for (String key : source.keySet()) {
//                    System.out.println(key + ":" + source.get(key));
//                }

        //获取响应方式三
//                byte[] sourceAsBytes = getResponse.getSourceAsBytes();
//
//                //获取响应方式四
//                System.out.println(getResponse.getSourceAsString());
//            }
//            @Override
//            public void onFailure(Exception e) {
//
//            }
//        };
//        client.getAsync(request, RequestOptions.DEFAULT, listener);
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        /**
         * Exit API(判断文档是否存在，存在就为true，反之false)
         */
//        request.fetchSourceContext(FetchSourceContext.DO_NOT_FETCH_SOURCE);
//
//        //明确指定将要返回的存储字段。 默认情况下，将返回{@code _source}字段。这个方法到底有什么用呢
//        request.storedFields("school");
//
//        ActionListener<Boolean> listener = new ActionListener<Boolean>() {
//            @Override
//            public void onResponse(Boolean aBoolean) {
//                log.info("是否存在：" + aBoolean);
//            }
//
//            @Override
//            public void onFailure(Exception e) {
//
//            }
//        };
//        client.existsAsync(request, RequestOptions.DEFAULT, listener);
//        try {
//            boolean exists = client.exists(request, RequestOptions.DEFAULT);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        /**
         * Delete API
         */

//        DeleteRequest deleteRequest = new DeleteRequest("test", "1");

        //1.配置路由
//        deleteRequest.routing("routing");
//
//        //2.配置主分区变成可以操作使用的超时时间
//        deleteRequest.timeout(TimeValue.timeValueMinutes(2));
//        deleteRequest.timeout("2m");
//
//        //3.配置更新策略
//        deleteRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
//        deleteRequest.setRefreshPolicy("wait_for");
//
//        //4.配置版本号
//        deleteRequest.version(2);
//
//        //5.配置版本类型
//        deleteRequest.versionType(VersionType.EXTERNAL);

        //6.同步请求方式
//        try {
//            client.delete(deleteRequest, RequestOptions.DEFAULT);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        //7.异步请求方式
//        ActionListener<DeleteResponse> listener = new ActionListener<DeleteResponse>() {
//            @Override
//            public void onResponse(DeleteResponse deleteResponse) {
//                ReplicationResponse.ShardInfo shardInfo = deleteResponse.getShardInfo();
//                if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
//
//                }
//                if (shardInfo.getFailed() > 0) {
//                    for (ReplicationResponse.ShardInfo.Failure failure :
//                            shardInfo.getFailures()) {
//                        String reason = failure.reason();
//                    }
//                }
////                RestStatus status = deleteResponse.status();
////                DocWriteResponse.Result result = deleteResponse.getResult();
////                log.info("删除结果：" + result);
//                //怎样判断删除成功，deleteResponse返回什么样的结果才算是删除成功
//            }
//
//            @Override
//            public void onFailure(Exception e) {
//
//            }
//        };
//        client.deleteAsync(deleteRequest, RequestOptions.DEFAULT, listener);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//

        /**
         * Update API (单个记录刷新)
         */

        //1. 使用脚本文件进行执行
//        UpdateRequest updateRequest = new UpdateRequest("test", "LVigZ3QBMoi3obtijnUj");
//        Map<String, Object> parameters = singletonMap("count", 4);
//
//        Script inline = new Script(ScriptType.INLINE, "painless",
//                "ctx._source.field += params.count", parameters);
//        updateRequest.script(inline);

        //2. Json文件
//        UpdateRequest request = new UpdateRequest("test", "LVigZ3QBMoi3obtijnUj");
//        String jsonString = "{" +
//                "\"name\":\"姓名77\"," +
//                "\"school\":\"执信中学\"" +
//                "}";
//        request.doc(jsonString, XContentType.JSON);
//        try {
//            UpdateResponse updateResponse = client.update(request, RequestOptions.DEFAULT);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        XContentBuilder builder = null;
//        try {
//            builder = XContentFactory.jsonBuilder();
//            builder.startObject();
//            {
//                builder.field("school", "南丰中学");
//                builder.field("name", "姓名77");
//            }
//            builder.endObject();
//            UpdateRequest updateRequest = new UpdateRequest("test", "LVigZ3QBMoi3obtijnUj")
//                    .doc(builder);
//            client.update(updateRequest, RequestOptions.DEFAULT);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        /**
         * Upsert API
         */

//        XContentBuilder builder = null;
//        try {
//            builder = XContentFactory.jsonBuilder();
//            builder.startObject();
//            {
//                builder.field("school", "南丰中学");
//                builder.field("name", "姓名77");
//            }
//            builder.endObject();
//            UpdateRequest updateRequest = new UpdateRequest("test", "LVigZ3QBMoi3obtijnUj")
//                    .doc(builder);
//
//            String jsonString = "{\"name\":\"Ryan\"," +
//                    "\"school\":\"中心小学\"," +
//                    "\"sid\":\"201624134109\"}";
//            updateRequest.upsert(jsonString, XContentType.JSON);
//            client.update(updateRequest, RequestOptions.DEFAULT);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        /**
         * Term Vectors API 返回有关特定文档字段中术语的信息和统计信息
         *
         */
//        TermVectorsRequest termVectorsRequest = new TermVectorsRequest("test", "_doc","LVigZ3QBMoi3obtijnUj");
//
//        //常见选项参数
//        termVectorsRequest.setFieldStatistics(false); //将fieldStatistics设置为false（默认为true）以省略文档计数，文档频率之和，总术语频率之和。
//        termVectorsRequest.setTermStatistics(true);   //将termStatistics设置为true（默认为false）以显示总术语频率和文档频率。
//        termVectorsRequest.setPositions(false);       //将positions设置为false（默认为true）以忽略头寸的输出。
//        termVectorsRequest.setOffsets(false);         //将offsets设置为false（默认为true）以忽略offsets的输出。
//        termVectorsRequest.setPayloads(false);        //将有效负载设置为false（默认为true）以忽略有效负载的输出。
//        Map<String, Integer> filterSettings = new HashMap<>();
//        filterSettings.put("max_num_terms", 3);
//        filterSettings.put("min_term_freq", 1);
//        filterSettings.put("max_term_freq", 10);
//        filterSettings.put("min_doc_freq", 1);
//        filterSettings.put("max_doc_freq", 100);
//        filterSettings.put("min_word_length", 1);
//        filterSettings.put("max_word_length", 10);
//
//        termVectorsRequest.setFilterSettings(filterSettings);
//        Map<String, String> perFieldAnalyzer = new HashMap<>();
//        perFieldAnalyzer.put("user", "keyword");
//        termVectorsRequest.setPerFieldAnalyzer(perFieldAnalyzer);//设置perFieldAnalyzer以指定与该字段不同的分析器。
//
//        termVectorsRequest.setRealtime(false); //将realtime设置为false（默认为true）以检索接近实时的术语向量
//        termVectorsRequest.setRouting("routing");
//
//        TermVectorsResponse termvectors = null;
//        //同步请求
//        try {
//            termvectors = client.termvectors(termVectorsRequest, RequestOptions.DEFAULT);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//
//        //异步请求
//        client.termvectorsAsync(termVectorsRequest, RequestOptions.DEFAULT, new ActionListener<TermVectorsResponse>() {
//            @Override
//            public void onResponse(TermVectorsResponse termVectorsResponse) {
//
//            }
//            @Override
//            public void onFailure(Exception e) {
//
//            }
//        });
//
//        //校验返回结果
//        for (TermVectorsResponse.TermVector tv : termvectors.getTermVectorsList()) {
//            String fieldname = tv.getFieldName();                                //当前字段名称
//            int docCount = tv.getFieldStatistics().getDocCount();                //当前字段的字段统计信息-文档数量
//            long sumTotalTermFreq =
//                    tv.getFieldStatistics().getSumTotalTermFreq();               //当前字段的字段统计信息-总术语频率之和
//            long sumDocFreq = tv.getFieldStatistics().getSumDocFreq();           //当前字段的字段统计信息-文档频率之和
//            if (tv.getTerms() != null) {
//                List<TermVectorsResponse.TermVector.Term> terms =
//                        tv.getTerms();                                           //当前字段的Term
//                for (TermVectorsResponse.TermVector.Term term : terms) {
//                    String termStr = term.getTerm();                             //Term的名字
//                    int termFreq = term.getTermFreq();
//                    int docFreq = term.getDocFreq();
//                    long totalTermFreq = term.getTotalTermFreq();
//                    float score = term.getScore();
//                    if (term.getTokens() != null) {
//                        List<TermVectorsResponse.TermVector.Token> tokens =
//                                term.getTokens();
//                        for (TermVectorsResponse.TermVector.Token token : tokens) {
//                            int position = token.getPosition();
//                            int startOffset = token.getStartOffset();
//                            int endOffset = token.getEndOffset();
//                            String payload = token.getPayload();
//                        }
//                    }
//                }
//            }
//        }


        /**
         * Bulk API 批量插入
         */
        BulkResponse response = null;
        try {
            ArrayList<Student> list = new ArrayList<>();
            BulkRequest request = new BulkRequest();
            list.forEach(item -> request.add(new IndexRequest("test")
                    .source(JSONObject.toJSONString(item), XContentType.JSON)));
            //同步执行
            response = client.bulk(request, RequestOptions.DEFAULT);

            //异步执行
            ActionListener<BulkResponse> listener = new ActionListener<BulkResponse>() {
                @Override
                public void onResponse(BulkResponse bulkResponse) {
                }

                @Override
                public void onFailure(Exception e) {
                }
            };
            client.bulkAsync(request, RequestOptions.DEFAULT, listener);


        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response.hasFailures()) {
            throw new RuntimeException(response.buildFailureMessage());
        }


    }


}
