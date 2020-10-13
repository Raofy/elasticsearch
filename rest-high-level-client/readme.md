## 高级客户端

   1.准备工作
   
   - 环境配置
   
   ````xml
   <dependency>
       <groupId>org.elasticsearch.client</groupId>
       <artifactId>elasticsearch-rest-high-level-client</artifactId>
       <version>7.6.2</version>
   </dependency>
   ````

   ```yaml
   
    elasticsearch:
      hostname: 192.168.9.173
      port: 9200
      schema: http
   ```

   ```java
   package com.ryan.resthighlevelclient.config;
   
   import org.apache.http.HttpHost;
   import org.elasticsearch.client.RestClient;
   import org.elasticsearch.client.RestHighLevelClient;
   import org.springframework.beans.factory.annotation.Value;
   import org.springframework.context.annotation.Bean;
   import org.springframework.context.annotation.Configuration;
   
   @Configuration
   public class ESConfig {
   
       @Value("${elasticsearch.hostname}")
       private String hostname;
   
       @Value("${elasticsearch.port}")
       private int port;
   
       @Value("${elasticsearch.schema}")
       private String schema;
   
       @Bean
       public RestHighLevelClient restHighLevelClient() {
           // 如果有多个从节点可以持续在内部new多个HttpHost，参数1是IP，参数2是端口，参数3是通信协议
           return new RestHighLevelClient(RestClient.builder(new HttpHost(hostname, port, schema)));
       }
   }

   ```

   ```java
   @Test
       void contextLoads() throws IOException {
           boolean ping = restHighLevelClient.ping(RequestOptions.DEFAULT);
           System.out.println(ping);   //结果显示为true，表明连接成功
       }
   ```

   2.操作索引
   
   - 创建索引
   
   ```java

   ```
   
   - 判断索引
   
   ```java
   
   ```
   
   - 删除索引
   
   ```java
   
   ```
   
   - 更新索引
   
   ```java
   
   ```
   
   