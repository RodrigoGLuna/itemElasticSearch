package Services;

import Modelo.Item;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ItemService {
    public static Item insertItem(Item item) {
        item.setId("" + item.getSite_id() + "" + UUID.randomUUID().toString());
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("id", item.getId());
        dataMap.put("site_id", item.getSite_id());
        dataMap.put("title", item.getTitle());
        dataMap.put("subtitle", item.getSubtitle());
        dataMap.put("seller_id", item.getSeller_id());
        dataMap.put("category_id", item.getCategory_id());
        dataMap.put("price", item.getPrice());
        dataMap.put("currency_id", item.getCurrency_id());
        dataMap.put("available_quantity", item.getAvailable_quantity());
        dataMap.put("condition", item.getCondition());

        IndexRequest indexRequest = new IndexRequest(Connection.INDEX, Connection.TYPE, item.getId())
                .source(dataMap);
        try {
            IndexResponse response = Connection.restHighLevelClient.index(indexRequest);
        } catch (ElasticsearchException e) {
            e.getDetailedMessage();
        } catch (java.io.IOException ex) {
            ex.getLocalizedMessage();
        }
        return item;
    }

    public static Item getItemById(String id) {
        GetRequest getPersonRequest = new GetRequest(Connection.INDEX, Connection.TYPE, id);
        GetResponse getResponse = null;
        try {
            getResponse = Connection.restHighLevelClient.get(getPersonRequest);
        } catch (java.io.IOException e) {
            e.getLocalizedMessage();
        }
        return getResponse != null ?
                Connection.objectMapper.convertValue(getResponse.getSourceAsMap(), Item.class) : null;
    }

    public static Item updateItemById(String id, Item item) {
        UpdateRequest updateRequest = new UpdateRequest(Connection.INDEX, Connection.TYPE, id)
                .fetchSource(true);    // Fetch Object after its update
        try {
            String personJson = Connection.objectMapper.writeValueAsString(item);
            updateRequest.doc(personJson, XContentType.JSON);
            UpdateResponse updateResponse = Connection.restHighLevelClient.update(updateRequest);
            return Connection.objectMapper.convertValue(updateResponse.getGetResult().sourceAsMap(), Item.class);
        } catch (JsonProcessingException e) {
            e.getMessage();
        } catch (java.io.IOException e) {
            e.getLocalizedMessage();
        }
        System.out.println("Unable to update person");
        return null;
    }

    public static String deleteItemById(String id) {
        DeleteRequest deleteRequest = new DeleteRequest(Connection.INDEX, Connection.TYPE, id);
        try {
            DeleteResponse deleteResponse = Connection.restHighLevelClient.delete(deleteRequest);
            return deleteResponse.getResult().toString();
        } catch (java.io.IOException e) {
            e.getLocalizedMessage();
            return "";
        }
    }

    public static void getItems() {
        SearchRequest searchRequest = new SearchRequest(Connection.INDEX);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = null;
        try {
            searchResponse = Connection.restHighLevelClient.search(searchRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SearchHits hits = searchResponse.getHits();
        for (SearchHit hit : hits.getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
    }

}
