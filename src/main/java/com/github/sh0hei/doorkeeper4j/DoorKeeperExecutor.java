package com.github.sh0hei.doorkeeper4j;

import static org.apache.http.Consts.*;
import static org.apache.http.HttpStatus.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.arnx.jsonic.JSON;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;

import com.github.sh0hei.doorkeeper4j.model.response.DoorKeeperResponse;

public class DoorKeeperExecutor {

    private static final String ENDPOINT_URL = "http://api.doorkeeper.jp";

    <T extends DoorKeeperResponse> T getContent(String apiPath, Class<T> responseType) throws IOException, DoorKeeperException {
        return getContent(apiPath, Collections.<String, String> emptyMap(), responseType);
    }

    <T extends DoorKeeperResponse> T getContent(String apiPath, Map<String, String> params, Class<T> responseType) throws IOException, DoorKeeperException {
        HttpGet request = new HttpGet(createQuery(createUrl(apiPath), params));
        HttpResponse response = execute(request);
        verifyStatusCode(response, Integer.valueOf(SC_OK));
        try (InputStream in = response.getEntity().getContent()) {
            T res = JSON.decode(in, responseType);
            return res;
        }
    }

    private HttpResponse execute(HttpRequestBase request) throws ClientProtocolException, IOException {
        HttpClient httpClient = new DefaultHttpClient();
        return httpClient.execute(request);
    }

    private static void verifyStatusCode(HttpResponse response, Integer expectedStatusCode) throws IOException, DoorKeeperException {
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == SC_BAD_REQUEST || statusCode == SC_FORBIDDEN || statusCode == SC_NOT_FOUND) {
            try (InputStream in = response.getEntity().getContent()) {
                Error res = JSON.decode(in, Error.class);
                throw new DoorKeeperException(res.getCause());
            }
        }
        if (statusCode != expectedStatusCode) {
            throw new IllegalArgumentException("unexpected status code:" + statusCode);
        }
    }

    private String createQuery(String url, Map<String, String> params) throws DoorKeeperException {
        StringBuilder sb = new StringBuilder(url);
        if (url.contains("?")) {
            sb.append('&');
        } else {
            sb.append('?');
        }
        try {
            for (Entry<String, String> entry : params.entrySet()) {
                String key = URLEncoder.encode(entry.getKey(), UTF_8.name());
                String value = URLEncoder.encode(entry.getValue(), UTF_8.name());
                sb.append(key).append('=').append(value).append('&');
            }
        } catch (UnsupportedEncodingException e) {
            throw new DoorKeeperException(e);
        }
        return sb.delete(sb.length() - 1, sb.length()).toString();
    }

    private static String createUrl(String apiPath) {
        return ENDPOINT_URL + '/' + apiPath;
    }

    private static String[] getHeaderValues(Header[] headers) {
        List<String> values = new ArrayList<>();
        for (Header header : headers) {
            values.add(header.getValue());
        }
        return values.toArray(new String[headers.length]);
    }
}
