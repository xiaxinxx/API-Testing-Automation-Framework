package com.allen.testcase;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.allen.http.HttpTestBase;
import com.allen.utils.AssertUtil;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class WeatherPostMethodTest extends HttpTestBase {

    @Test
    public void postTest1(){
        String path ="";
        //设置cookie
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("cookie","cookie");
        //设置请求体
        Map<String, Object> params = new HashMap<>();
        params.put("id",123);
        JSONObject jsonObject = httpClient.post(path, headerMap, params);
        AssertUtil.assertEquals(JSONPath.read(jsonObject.toJSONString(),"weatherinfo.cityid").toString(),"101190408");
    }

    @Test
    public void postTest2(){
        String path ="";
        //设置cookie
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("cookie","cookie");
        //设置请求体,上传文件
        File file = new File("path");
        RequestBody fileMediaType = RequestBody.create(MediaType.parse("multipart/form-date"), file);
        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("fileId","123")
                .addFormDataPart("file", file.getName(),fileMediaType)
                .build();
        JSONObject jsonObject = httpClient.post(path, headerMap, body);
        AssertUtil.assertEquals(JSONPath.read(jsonObject.toJSONString(),"weatherinfo.cityid").toString(),"101190408");
    }
}
