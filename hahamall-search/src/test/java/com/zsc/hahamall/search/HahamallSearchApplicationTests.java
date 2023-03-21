package com.zsc.hahamall.search;

import com.alibaba.fastjson.JSON;
import com.zsc.hahamall.search.config.HahamallElasticSearchConfig;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
//  @SpringBootTest  需要和 @RunWith （指定要spring的驱动）  组合使用
@RunWith(SpringRunner.class)
@SpringBootTest
public class HahamallSearchApplicationTests {
	@Autowired
	private RestHighLevelClient client;
	@Test
	public void contextLoads() {
		System.out.println(client);
	}

	/**
	 * 存储数据到ES中
	 * @throws IOException IO异常
	 */
	@Test
	public void indexData() throws IOException {
		// 设置【索引】 ，构造存储请求
		IndexRequest indexRequest = new IndexRequest("user");
		//数据的id
		indexRequest.id("2");
		User user = new User();
		user.setUserName("小熊");
		user.setAge(44);
		user.setGender("男");
		String jsonString = JSON.toJSONString(user);
		//设置要【保存的内容】，指定数据和类型 ，
		indexRequest.source(jsonString, XContentType.JSON);

		//执行创建索引和保存数据
		IndexResponse index = client.index(indexRequest, HahamallElasticSearchConfig.COMMON_OPTIONS);

		System.out.println(index);

	}

	/**
	 * 构建 DSL 语句
	 * @throws IOException
	 */
	@Test
	public void find() throws IOException {
		// 1 创建检索请求
		SearchRequest searchRequest = new SearchRequest();
		//指定索引（database）  查询索引  get /_cat/indices
		searchRequest.indices("bank");
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		// 构造检索条件
//        sourceBuilder.query();
//        sourceBuilder.from();
//        sourceBuilder.size();
//        sourceBuilder.aggregation();

		//GET /bank/_search
		//{
		//  "query": {
		//    "match": {
		//      "address": "mill"
		//    }
		//  }

		//QueryBuilders --->  xxxxBuilders  就是xxx的工具类
		//match 查询
		sourceBuilder.query(QueryBuilders.matchQuery("address","mill"));
		System.out.println(sourceBuilder.toString());
//        sourceBuilder.from();
//        sourceBuilder.size();
//        sourceBuilder.aggregation();

		//ES: 使用 aggregation 聚合

		//GET /bank/_search
		//{
		//  "aggs": {
		//    "ageAgg": {
		//      "terms": {
		//        "field": "age",
		//        "size": 10
		//      }
		//    },
		//    "balanceAgg":{
		//      "terms": {
		//        "field": "balance",
		//        "size": 10
		//      }
		//    }
		//  }
		//}

		System.out.println("===========term================");
		//1.2）、按照年龄分布进行聚合
		SearchSourceBuilder ageAgg = sourceBuilder.aggregation(AggregationBuilders.terms("ageAgg").field("age").size(10));
		System.out.println(ageAgg);
		System.out.println("===========aggregation==========");
		//1.3）、计算平均薪资
		SearchSourceBuilder balanceAvg = sourceBuilder.aggregation(AggregationBuilders.avg("balanceAvg").field("balance"));
		System.out.println(balanceAvg);
		searchRequest.source(sourceBuilder);
		System.out.println("================================");
		// 2 执行检索
		SearchResponse response = client.search(searchRequest, HahamallElasticSearchConfig.COMMON_OPTIONS);
		SearchHits hits = response.getHits();
		SearchHit[] hitsArray = hits.getHits();
		for (SearchHit documentFields : hitsArray) {
			System.out.println(documentFields.toString());
		}
		TotalHits totalHits = hits.getTotalHits();
		// 获取总记录数量
		long numHits = totalHits.value;
		System.out.println("======================================");
		// 3 分析响应结果
		System.out.println(response.toString());
	}


	@Data
	class User{
		private String userName;
		private String gender;
		private Integer age;

	}

}
