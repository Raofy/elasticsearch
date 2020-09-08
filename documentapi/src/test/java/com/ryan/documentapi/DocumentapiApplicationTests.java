package com.ryan.documentapi;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.document.DocumentField;
import org.elasticsearch.index.VersionType;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootTest
class DocumentapiApplicationTests {

    @Resource
    private RestHighLevelClient client;

    @Test
    void contextLoads() {
        GetRequest request = new GetRequest("test", "KVigZ3QBMoi3obtijnUj");

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
//        //明确指定将要返回的存储字段。 默认情况下，将返回{@code _source}字段。
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

    }


}
