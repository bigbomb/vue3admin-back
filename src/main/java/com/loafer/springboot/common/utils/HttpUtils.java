package com.loafer.springboot.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Map;

public class HttpUtils {

    private static RestTemplate restTemplate;

    static {
        restTemplate = new RestTemplate();
    }

    /**
     * get请求
     * @param url
     * @return Map
     */
    public static Map doGet (String url) {
        ResponseEntity<Map> results = restTemplate.exchange(url, HttpMethod.GET, null, Map.class);
        return results.getBody();
    }

    /**
     * form请求
     * @param url
     * @param params
     * @return Map
     */
    public static Map doForm (String url, JSONObject params) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<JSONObject> request = new HttpEntity<>(params, headers);
        ResponseEntity<Map> results = restTemplate.exchange(url, HttpMethod.POST, request, Map.class);
        return results.getBody();
    }

    /**
     * json请求
     * @param url
     * @param params
     * @return Map
     */
    public static Map doJson (String url, Map params) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(JSON.toJSONString(params), headers);
        ResponseEntity<Map> results = restTemplate.exchange(url, HttpMethod.POST, request, Map.class);
        return results.getBody();
    }

    /**
     * 图片请求
     * @param url
     * @param params
     * @return byte[]
     */
    public static byte[] doImage (String url, Map params) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
        HttpEntity<String> request = new HttpEntity<>(JSON.toJSONString(params), headers);
        ResponseEntity<byte[]> results = restTemplate.exchange(url, HttpMethod.POST, request, byte[].class);
        return results.getBody();
    }
}
