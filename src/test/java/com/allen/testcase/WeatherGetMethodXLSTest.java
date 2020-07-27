package com.allen.testcase;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.allen.dataprovider.ExcelDataProvider;
import com.allen.http.HttpTestBase;
import com.allen.utils.AssertUtil;
import org.testng.annotations.Test;

public class WeatherGetMethodXLSTest extends HttpTestBase {

    @Test(dataProviderClass = ExcelDataProvider.class, dataProvider = ExcelDataProvider.DEFAULT_PROVIDER)
    public void test(String id, String result) {
        JSONObject jsonObject = httpClient.get("/data/sk/" + id + ".html");
        AssertUtil.assertEquals(JSONPath.read(jsonObject.toJSONString(), "weatherinfo.cityid").toString(), result);
    }
}
