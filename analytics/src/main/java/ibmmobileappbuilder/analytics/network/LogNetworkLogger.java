package ibmmobileappbuilder.analytics.network;

import android.util.Log;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import ibmmobileappbuilder.analytics.NetworkResponse;
import ibmmobileappbuilder.analytics.network.NetworkLogger;

public class LogNetworkLogger implements NetworkLogger {

    private static final String NETWORK_RESPONSE = "network_response";
    private static final String NETWORK_REQUEST = "network_request";

    @Override
    public void logRequest(String url, String httpMethod) {
        Map<String, String> paramsMap = new HashMap<>(2);
        paramsMap.put(NETWORK_REQUEST, url);
        paramsMap.put("http_method", httpMethod);
        Log.i(NETWORK_REQUEST, String.format("Request with params: %s", paramsMap));
    }

    @Override
    public void logResponse(NetworkResponse networkResponse) {
        Map<String, String> paramsMap = new HashMap<>(3);
        paramsMap.put(NETWORK_RESPONSE, networkResponse.getUrl());
        paramsMap.put("response_code", networkResponse.getStatusCode());
        paramsMap.put("response_body", networkResponse.getBody());
        Log.i(NETWORK_RESPONSE, String.format("Response with params: %s", paramsMap));
    }
}
