package com.automation.tests.day2;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSTests {
    //address of ORDS web service, that is running no AWS ec2.
    //dara is coming from SQL Oracle data base to this web service
    //during back-end testing with SQL developer and JDBC API
    //we were accessing data base directly
    //now, we gonna access web service

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

    @Test
    public void test2(){
        Response response = given().
                header("accept","application/json").// doesn't it make differs?????
                get(baseURI+"/employees/100");
        int actualStatusCode = response.getStatusCode();
        System.out.println(response.prettyPrint());
        assertEquals(200,actualStatusCode);

        System.out.println("what kind o contentent server sends to you, in this reponse: "+response.getHeader("Content-Type"));
    }

    //  #Task: perform Get request to /regions, print body and all headers.

    @Test
    public void test3 (){
        Response response= given().get(baseURI+"/regions");
        assertEquals(200,response.getStatusCode());
        // to get specific header
        Header header = response.getHeaders().get("content-Type");
        for (Header h: response.getHeaders()){
            System.out.println(h);
        }
        System.out.println("#########################################");
        System.out.println(response.prettyPrint());
    }
}
