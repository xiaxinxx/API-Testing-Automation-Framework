package com.allen.testcase;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.allen.http.HttpTestBase;
import com.allen.utils.AssertUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class WeatherGetMethodTest extends HttpTestBase {

    @DataProvider(name = "data")
    public Object[][] dataProvider() {
        return new Object[][]{
                {"101190408", "101190408"},
                {"101010100", "101010100"}
        };
    }

    @Test(dataProvider = "data")
    public void test(String id, String reuslt) {
        JSONObject jsonObject = httpClient.get("/data/sk/" + id + ".html");
        AssertUtil.assertEquals(JSONPath.read(jsonObject.toJSONString(), "weatherinfo.cityid").toString(), reuslt);
    }
}
