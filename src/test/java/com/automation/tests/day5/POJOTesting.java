package com.automation.tests.day5;

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

import java.util.*;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class POJOTesting {

    @BeforeAll
    public static void setup() {
        baseURI = ConfigurationReader.getProperty("ords.uri");
    }

    @Test
    @DisplayName("Get job info from JSON and convert it into POJO")
    public void test1() {
        Response response = given().
                accept(ContentType.JSON).
                when().
                get("/jobs");

        JsonPath jsonPath = response.jsonPath();
        //this is deserialization
        // from JSON to POJO (java object)
        Job job = jsonPath.getObject("items[0]", Job.class);//Job.class type of POJO that you gonna create from JSON

        System.out.println(job);

        System.out.println("Job id: " + job.getJobId());
    }

    @Test
    @DisplayName("Convert from POJO to JSON")
    public void test2() {
        Job sdet = new Job("SDET", " Software Development Engineer in Test", 5000, 20000);

        Gson gson = new Gson();
        String json = gson.toJson(sdet); // convert POJO to JSON: serialization

        System.out.println("JSON file" + json);
        System.out.println("From toString(): " + sdet);
    }

    @Test
    @DisplayName("Convert JSON into collection of POJO's")
    public void test3() {
        Response response = given().
                accept(ContentType.JSON).
                when().
                get("/jobs");

        JsonPath jsonPath = response.jsonPath();
        List<Job> jobs = jsonPath.getList("items", Job.class);

        for (Job job : jobs) {
            System.out.println(job.getJob_title());
        }

        for (Job job : jobs) {
            System.out.println(job);
        }
    }

    @Test
    @DisplayName("Convert from JSON to Location POJO")
    public void test4() {
        Response response = given().
                accept(ContentType.JSON).
                when().
                get("/locations/{location_id}", 2500);
        Location location = response.jsonPath().getObject("", Location.class);

        System.out.println(location);


        Response response2 = given().
                accept(ContentType.JSON).
                when().
                get("/locations");

        List<Location> locations = response2.jsonPath().getList("items", Location.class);

        for(Location l: locations){
            System.out.println(l);
        }
    }

}