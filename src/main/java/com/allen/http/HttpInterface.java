package com.allen.http;

import com.alibaba.fastjson.JSONObject;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

public interface HttpInterface {

    @GET("/{path}")
    Call<JSONObject> get(@Path("path") String path);

    @GET("/{path}")
    Call<JSONObject> get(@Path("path") String path, @QueryMap Map<String, String> querys);

    @FormUrlEncoded
    @POST("/{path}")
    Call<JSONObject> post(@Path("path") String path, @HeaderMap Map<String, Object> headers, @Body RequestBody body);

    @FormUrlEncoded
    @POST("/{path}")
    Call<JSONObject> post(@Path("path") String path, @HeaderMap Map<String, Object> headers, @QueryMap Map<String, Object> params);
}
