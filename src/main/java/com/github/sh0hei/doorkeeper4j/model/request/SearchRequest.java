package com.github.sh0hei.doorkeeper4j.model.request;

import java.util.HashMap;
import java.util.Map;

public class SearchRequest extends ParamsRequest {

    private String query;

    private final String[] queries;

    public SearchRequest(String query, String... queries) {
        this.query = query;
        this.queries = queries;
    }

    @Override
    public Map<String, String> createParams() {
        Map<String, String> params = new HashMap<>();
        if (queries == null || queries.length < 1) {
            params.put("q", query);
        } else {
            StringBuilder sb = new StringBuilder(query);
            for (String query : queries) {
                sb.append(' ').append(query);
            }
            params.put("q", sb.toString());
        }
        return params;
    }

}
