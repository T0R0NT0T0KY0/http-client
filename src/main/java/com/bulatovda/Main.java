package com.bulatovda;


import com.bulatovda.services.HttpClient;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.json.JSONObject;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		HttpClient httpClient = new HttpClient() {
			@Override
			public String get(String url, Map<String, String> headers, Map<String, String> params) {
				return httpGet(url, headers, params);
			}

			@Override
			public String post(String url, Map<String, String> headers, Map<String, String> params) {
				return httpPost(url, headers, params);
			}
		};
		Map headers = new HashMap();
		headers.put("content-type", "application/json");
		Map params = new HashMap();
		params.put("name", "Dmitry");
		System.out.println(httpClient.post("https://postman-echo.com/post", headers, params));
		System.out.println(httpClient.get("https://postman-echo.com/get", headers, params));
	}

	//todo
	private static String httpPost(String url, Map<String, String> headers, Map<String, String> params) {
		try {
			final Request request = Request.Post(url);
			JSONObject json = new JSONObject();
			params.forEach(json::put);
			return request.bodyString(
					json.toString(),
					ContentType.APPLICATION_JSON).execute()
					.returnContent().asString();
		} catch (IOException e) {
			return e.getMessage();
		}
	}

	//todo params
	private static String httpGet(String url, Map<String, String> headers, Map<String, String> params) {
		try {
			final Request request = Request.Get(url);
			headers.forEach(request::setHeader);
			return request.execute()
					.returnContent().asString();
		} catch (IOException e) {
			return e.getMessage();
		}
	}
}
