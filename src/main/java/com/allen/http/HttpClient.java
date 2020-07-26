package com.allen.http;

import com.alibaba.fastjson.JSONObject;
import com.allen.enums.DefaultSystemDomain;
import okhttp3.RequestBody;
import org.apache.commons.lang3.StringUtils;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

import java.io.IOException;
import java.util.Map;

public class HttpClient {

    private HttpInterface httpInterface;

    public HttpClient() {
        String domain = System.getProperty("domain");
        if (StringUtils.isEmpty(domain)) {
            domain = DefaultSystemDomain.DEFAULT.getUrl();
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(domain)
                .addConverterFactory(GsonConverterFactory.create()).build();

        httpInterface = retrofit.create(HttpInterface.class);
    }

    public JSONObject get(String path) {
        Call<JSONObject> call = httpInterface.get(path);
        Response<JSONObject> execute = null;
        try {
            execute = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return execute.body();
    }

    public JSONObject get(String path, Map<String, String> querys) {
        Call<JSONObject> call = httpInterface.get(path, querys);
        Response<JSONObject> execute = null;
        try {
            execute = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return execute.body();
    }

    public JSONObject post(String path, Map<String, Object> headers, RequestBody body) {
        Call<JSONObject> call = httpInterface.post(path, headers, body);
        Response<JSONObject> execute = null;
        try {
            execute = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return execute.body();
    }

    public JSONObject post(String path, Map<String, Object> headers, Map<String, Object> params) {
        Call<JSONObject> call = httpInterface.post(path, headers, params);
        Response<JSONObject> execute = null;
        try {
            execute = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return execute.body();
    }
}
