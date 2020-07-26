package com.allen.http;

import org.testng.annotations.BeforeClass;

public class HttpTestBase {

    public HttpClient httpClient;

    @BeforeClass
    public void setup(){
        httpClient = new HttpClient();
    }
}
