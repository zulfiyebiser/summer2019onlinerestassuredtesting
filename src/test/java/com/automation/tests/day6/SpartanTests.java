package com.automation.tests.day6;

import com.automation.pojos.Spartan;
import org.junit.jupiter.api.BeforeAll;

import com.automation.pojos.Job;
import com.automation.pojos.Location;
import com.automation.utilities.ConfigurationReader;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class SpartanTests {

    @BeforeAll
    public static void setup(){
        baseURI = ConfigurationReader.getProperty("spartan.uri");
    }

    /**
     * given accept content type as JSON
     * when user sends GET request to /spartans
     * then user verifies that status code is 200
     * and user verifies that content type is JSON
     */


    @Test
    @DisplayName("Verfy that / spartans end point returns 200 and content type as JSON")
    public void test1(){
        given().
                accept(ContentType.JSON).
                when().
                get("/spartans").prettyPeek().
                then().assertThat().
                statusCode(200).
                contentType(ContentType.JSON);
    }

    /**
     * given accept content type as XML
     * when user sends GET request to /spartans
     * then user verifies that status code is 200
     * and user verifies that content type is XML
     */

    @Test
    @DisplayName("Verfy that / spartans end point returns 200 and content type as XML")
    public void test2(){
        given().
                accept(ContentType.XML).
                when().
                get("/spartans").prettyPeek().
                then().assertThat().
                statusCode(200).
                contentType(ContentType.XML);
    }


    /** TASK
     * given accept content type as JSON
     * when user sends GET request to /spartans
     * then user saves payload in collection
     */

    @Test
    @DisplayName("Save payload into java collection")
    public void test3(){
        Response response =given().
                accept(ContentType.JSON).
        when().
                get("/spartans").prettyPeek();
        List<Map<String ,?>> collection = response.jsonPath().get();
        for(Map<String,?> map : collection){
            System.out.println(map);
            System.out.println(map.get("phone")); //==> to print specific value
        }

    }
    /** TASK
     * given accept content type as JSON
     * when user sends GET request to /spartans
     * then user saves payload into collection of Spartan
     */

    @Test
    @DisplayName("Save payload into java collection of Spartan")
    public void test4(){
        Response response = given().
                contentType(ContentType.JSON).
                when().
                get("/spartans").prettyPeek();
        //whenever you see: Class object = response.jsonPath.getObject() | deserialization
        List<Spartan> collection = response.jsonPath().getList("", Spartan.class);

        for(Spartan spartan: collection){
            System.out.println(spartan);
        }
    }

    /** TASK
     * given accept content type as JSON
     * when user sends POST request to /spartans
     * then user should be able to create new spartan
     *      |gender|name           |phone     |
     *      | male |Mister Twister |5712134235|
     *      Then user verifies that status code 201
     *
     *      201 - means created.Whenever you POST something, you should get back 201 status code
     *      in case of created record
     *
     */

    @Test
    @DisplayName("Create new spartan and verify that status code is 201")
    public void test5(){
        //builder pattern, one of the design patterns in OOP
        //instead of having too many different constructors
        //we can use builder pattern and chain with{preopertyName} methods to specify properties of an object
//        Spartan spartan1 = new Spartan().
//                withGender("Male").
//                withName("Some User").
//                withPhone(5412134235L);

        Spartan spartan = new Spartan();
        spartan.setGender("Male");
        spartan.setName("Mister Twister");
        spartan.setPhone(540612134235L);

        Response response = given().
                contentType(ContentType.JSON).
                body(spartan).
                when().
                post("/spartans");
        assertEquals(201, response.getStatusCode(), "Status code is wrong!");
        assertEquals("application/json", response.getContentType(), "Content type is invalid!");
        assertEquals(response.jsonPath().getString("success"), "A Spartan is Born!");

        response.prettyPrint();

        Spartan spartan_from_response = response.jsonPath().getObject("data", Spartan.class);

        System.out.println("Spartan id: "+spartan_from_response.getSpartanId());

//        //delete spartan that you've just created
//        when().delete("/spartans/{id}", spartan_from_response.getSpartanId()).
//                prettyPeek().
//                then().assertThat().statusCode(204);

    }

}