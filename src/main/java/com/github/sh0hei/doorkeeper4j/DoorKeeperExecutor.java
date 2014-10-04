package com.github.sh0hei.doorkeeper4j;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;

public class DoorKeeperExecutor {

    private static final String ENDPOINT_URL = "http://api.doorkeeper.jp";

    private HttpResponse execute(HttpRequestBase request) throws ClientProtocolException, IOException {
        HttpClient httpClient = new DefaultHttpClient();
        return httpClient.execute(request);
    }
}
