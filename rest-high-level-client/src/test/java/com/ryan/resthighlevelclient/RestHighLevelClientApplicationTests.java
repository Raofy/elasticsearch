package com.ryan.resthighlevelclient;

import com.alibaba.fastjson.JSON;
import com.ryan.resthighlevelclient.entity.User;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class RestHighLevelClientApplicationTests {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    void contextLoads() throws IOException {
        boolean ping = restHighLevelClient.ping(RequestOptions.DEFAULT);
        System.out.println(ping);
    }

    @Test
    void createIndex() throws IOException {
        CreateIndexRequest index = new CreateIndexRequest("fuyi_index");
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(index, RequestOptions.DEFAULT);
        System.out.println(createIndexResponse);
    }

    @Test
    void exitIndex() throws IOException {
        GetIndexRequest index = new GetIndexRequest("fuyi_index");
        boolean exists = restHighLevelClient.indices().exists(index, RequestOptions.DEFAULT);
        System.out.println("是否存在？？" + exists);
    }

    @Test
    void deleteIndex() throws IOException {
        DeleteIndexRequest index = new DeleteIndexRequest("fuyi_index");
        AcknowledgedResponse delete = restHighLevelClient.indices().delete(index, RequestOptions.DEFAULT);
        System.out.println(delete.isAcknowledged());
    }

    @Test
    void addDocument() throws IOException {
        User document = new User("fuyi", 18);
        IndexRequest request = new IndexRequest("fuyi_index");
        request.source(JSON.toJSONString(document), XContentType.JSON);
        request.timeout(TimeValue.timeValueSeconds(1));
        request.id("1");
        IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        System.out.println(response.toString());
        System.out.println(response.status());

    }

    @Test
    void getDocument() throws IOException {
        GetRequest getRequest = new GetRequest("fuyi_index");
        getRequest.id("1");
        GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(getResponse.toString());
        System.out.println(getResponse.getSourceAsString());
    }

    @Test
    void updateDocument() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("fuyi_index", "1");
        User user = new User("陈大周", 99);
        updateRequest.doc(JSON.toJSONString(user), XContentType.JSON);
        updateRequest.timeout("1s");

        UpdateResponse update = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(update);
        System.out.println(update.status());
    }

    @Test
    void delDocument() throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest("fuyi_index","1");
        deleteRequest.timeout("1s");
        DeleteResponse delete = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println(delete);
        System.out.println(delete.status()); //删除成功返回OK，元素不存在返回NOT_FOUND
    }

    




}
