package com.automation.tests.day3;

import com.automation.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class ORDSTestsDay3 {

    @BeforeAll
    public static void setup() {
        baseURI = ConfigurationReader.getProperty("ords.uri");
    }
    //accept("application/json") shortcut for header("Accept", "application/json")
    //we are asking for json as a response
    @Test
    public void test1() {
        given().
                accept("application/json").
                get("/employees").
                then().
                assertThat().statusCode(200).
                and().assertThat().contentType("application/json").
                log().all(true);
    }
    // path parameter - to point on specific resource / employee/: -id it's a path parameter
    //querry parameter - to filterresults, or describe new resource : POST / users?name=James&age=60&joib-title=SDET
    //or to filter GET / employee?name=Jamal get all employees with name Jamal
    @Test
    public void test2(){
        given().
                accept("application/json").
                pathParam("id",100).
                when().get("employees/{id}").
                then().assertThat().statusCode(200).
                and().assertThat().body("employee_id", is(100)).
                log().all(true);// provides printing like prettyPrint
        // body ("phone_number")-->515.123.4567
        // is coming from --> import static org.hamcrest.Matchers.*;
    }
}
