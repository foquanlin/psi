package com.tongyi.es.service;

import org.elasticsearch.xcontent.XContentType;

import java.util.List;
import java.util.Map;

public interface ElasticSearchService {
    boolean createIndex(String index);

    boolean deleteIndex(String index);

    int insertDocument(String index, Object data, XContentType dataType, String id);

    <T> T getDocument(String index, String id, Class<T> mappingClass);

    int updateDocument(String index, Object data, XContentType dataType, String id);

    int deleteDocument(String index, String id);

    boolean batchInsertDocument(String index, List<?> list, XContentType dataType);

    List<Map<String, Object>> searchDocument(String index);
}
