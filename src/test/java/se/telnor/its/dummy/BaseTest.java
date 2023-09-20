package se.telnor.its.dummy;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.hamcrest.core.Is.is;

public class BaseTest {


    @Test
    public void testSmth() {
        RestAssured.get("http://10.82.8.68:8080/dummy/its/get").then().body(is("Dummy updated OK"));
    }
}
