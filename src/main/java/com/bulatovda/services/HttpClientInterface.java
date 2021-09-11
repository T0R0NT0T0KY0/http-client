package com.bulatovda.services;

import java.util.Map;

public interface HttpClientInterface {

	//    https://postman-echo.com/get
	String get(String url, Map<String, String> headers, Map<String, String> params);

	//    https://postman-echo.com/post
	String post(String url, Map<String, String> headers, Map<String, String> params);

}