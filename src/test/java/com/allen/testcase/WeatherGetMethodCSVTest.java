package com.allen.testcase;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.allen.dataprovider.CsvDataProvider;
import com.allen.http.HttpTestBase;
import com.allen.utils.AssertUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
@Slf4j
public class WeatherGetMethodCSVTest extends HttpTestBase {



    @Test(dataProviderClass = CsvDataProvider.class, dataProvider = CsvDataProvider.DEFAULT_PROVIDER)
    public void test(String id, String result) {
        log.error("=== s-=====");
        JSONObject jsonObject = httpClient.get("/data/sk/" + id + ".html");
        AssertUtil.assertEquals(JSONPath.read(jsonObject.toJSONString(), "weatherinfo.cityid").toString(), result);
    }
}
