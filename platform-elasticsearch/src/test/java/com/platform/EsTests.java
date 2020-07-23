package com.platform;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.io.IOException;
import java.util.Iterator;

public class EsTests {

    @Autowired
    ElasticsearchRestTemplate template;
    @Autowired
    RestHighLevelClient restHighLevelClient;


    @Test
    public void test2(){

        IndexOperations ops = template.indexOps(User.class);
        boolean exists = ops.exists();
        System.out.println(exists);

        //通过id查询，已经废弃
        User user = template.queryForObject(GetQuery.getById("1"), User.class);
        System.out.println(user);

        //和上一样
        User user1 = template.get("1", User.class);
        System.out.println(user1);


        //查询所有
        final SearchHits<User> search = template.search(Query.findAll(), User.class);
        final Iterator<SearchHit<User>> iterator = search.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Test
    public void test() throws IOException {

        final GetIndexRequest indexRequest = new GetIndexRequest("boot");
        final boolean exists = restHighLevelClient.indices().exists(indexRequest,RequestOptions.DEFAULT);
        System.out.println(exists);
    }
    @Test
    public void test01() throws Exception {
        final GetRequest request = new GetRequest("boot", "1");
        final GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT);
        final String string = response.toString();
        System.out.println(string);
        System.out.println("-------------------------");


    }

    //{"_index":"boot","_type":"_doc","_id":"1","_version":2,"_seq_no":4,"_primary_term":3,"found":true,"_source":{"name":"Jaskson","age":18,"gender":"male","desc":["码农","直男","女装大佬"]}}


    @Test
    public void test02() throws Exception {
        SearchResponse search = restHighLevelClient.search(new SearchRequest("boot"), RequestOptions.DEFAULT);
        System.out.println(search);
    }
}
