package com.mariuspaavel.driveodtcompiler;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class HttpReqSync {

    // one instance, reuse
    private static HttpClient httpClient;
    
    
    private static boolean ready = false;
    private static void init() {
    	httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
    	ready = true;
    }
    

    public static String get(String url, Map<String, String> params) throws Exception {
    	if(!ready)init();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(getUrl(url, params)))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .timeout(Duration.ofSeconds(5))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // print status code
        System.out.println(response.statusCode());

        // print response body
        System.out.println(response.body());

        return response.body();
    }

    public static String post(String url, Map<? extends Object, ? extends Object> data) throws Exception {
    	if(!ready)init();
        
        HttpRequest request = HttpRequest.newBuilder()
                .POST(buildFormDataFromMap(data))
                .uri(URI.create(url))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .header("Content-Type", "application/x-www-form-urlencoded")
                .timeout(Duration.ofSeconds(5))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // print status code
        System.out.println(response.statusCode());

        // print response body
        System.out.println(response.body());
        
        return response.body();

    }




	private static String getUrl(String source, Map<String, String> params) {
    	StringBuilder sb = new StringBuilder();
    	sb.append(source);
    	if(params == null)return sb.toString();
    	sb.append('?');
    	int i = 0;
    	for(Map.Entry<String, String> entry : params.entrySet()) {
    		sb.append(entry.getKey());
    		sb.append('=');
    		sb.append(entry.getValue());
    		if(i != params.size()-1)sb.append('&');
    		i++;
    	}
    	return sb.toString();
    }
    
    private static HttpRequest.BodyPublisher buildFormDataFromMap(Map<? extends Object, ? extends Object> data) {
    	
        var builder = new StringBuilder();
        
        if (data != null)
        for (Map.Entry<? extends Object, ? extends Object> entry : data.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
        }
        System.out.println(builder.toString());
        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }

}