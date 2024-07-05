package com.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHits;

import java.io.IOException;

public class ElasticsearchQueryDemo {

    public static void main(String[] args) {
        // 初始化客户端
        RestClientBuilder builder = RestClient.builder(
                new HttpHost("localhost", 9200, "http")
        );
        RestHighLevelClient client = new RestHighLevelClient(builder);

        try {
            // 创建搜索请求，并指定索引
            SearchRequest searchRequest = new SearchRequest("your-index-name");

            // 构建搜索源并添加查询类型
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.query(QueryBuilders.matchQuery("fieldName", "searchValue"));

            searchRequest.source(sourceBuilder);

            // 执行搜索
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

            // 处理响应
            SearchHits hits = searchResponse.getHits();
            System.out.println("Search complete. Total Hits: " + hits.getTotalHits());
            hits.forEach(hit -> System.out.println(hit.getSourceAsString()));

        } catch (IOException e) {
            // 异常处理
            e.printStackTrace();
        } finally {
            // 关闭客户端
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
