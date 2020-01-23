package com.automation.tests.day2;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSTests {
    //address of ORDS web service, that is running no AWS ec2
    //dara is coming frim SQL Oracle data base to this web service

    private String baseURI = "http://ec2-52-90-22-4.compute-1.amazonaws.com:1000/ords/hr";
    //verify that status code is 200
    @Test()
    public void test1(){
        Response response = given().
                get("http://ec2-52-90-22-4.compute-1.amazonaws.com:1000/ords/hr/employees");
        // we can put ==> get(baseURL+"/employees")
        System.out.println(response.getBody().asString());
        assertEquals(200,response.getStatusCode());
        System.out.println(response.prettyPrint());

    }
}
