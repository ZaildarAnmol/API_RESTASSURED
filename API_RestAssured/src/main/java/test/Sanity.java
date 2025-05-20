package test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import restUtils.RequestUtils;

public class Sanity {
    RequestUtils ru = null;

    @BeforeClass
    public void setup() {
        ru = new RequestUtils();
    }

    @Test
    public void getUsers_TC02() {
        Response getUsers  = ru.getRequest("/getdata");
        Assert.assertEquals(getUsers.statusCode(), 200);
        getUsers.prettyPrint();
    }
}
