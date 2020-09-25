# ElasticSearch配置

#### 1. 搭建环境

- JDK 1.8（最低要求）
- ElasticSearch 7.6.1

**注意**： Client的版本号要和ElasticSearch的版本一致。

[Java开发文档](https://artifacts.elastic.co/javadoc/org/elasticsearch/client/elasticsearch-rest-high-level-client/7.6.2/index.html)

- 1.1 pom.xml文件添加依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.3.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.Ryan</groupId>
    <artifactId>configuration</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>configuration</name>
    <description>Demo project for Spring Boot</description>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>

         <!--elasticsearch依赖-->
        <dependency>
            <groupId>org.elasticsearch.client</groupId>
            <artifactId>elasticsearch-rest-high-level-client</artifactId>
            <version>7.6.2</version>
        </dependency>

    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>

```

- 1.2 相关参数数值配置

```yaml

elasticsearch:
  hostname: 192.168.9.173
  port: 9200
  schema: http
```

- 1.3 编写配置Java配置

```java
package com.ryan.configuration.config;

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

**注意**：因为自身RestHighLevelClient是基于Low-level Client构造器进行创建连接池进行池化管理的，可以通过
释放资源
```java
client.close()
```