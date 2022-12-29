package coms309;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;


import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TestWorkout {

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost:8080";
    }

    /**
     * Test GET method for users
     * Tests Post method for users
     */
    @Test
    public void WorkoutsTest() {


        String userJson = "{\"wName\":\"skiing\",\"wType\":\"cardio\",\"weight\":0,\"caloriesB\":250}";
        String userJson2 = "{\"id\":1,\"wName\":\"testcase\",\"wType\":\"test\",\"weight\":700,\"caloriesB\":250}";

        // Send request and receive response (create user)
        Response response = RestAssured.given().
                header("Content-Type", "application/json").
                body(userJson).
                when().
                post("/workouts");

        Response response2 = RestAssured.given().
                header("Content-Type", "application/json").
                body(userJson2).
                when().
                put("/workouts/1");


        // Send request and receive response (create auction)
        Response response4 = RestAssured.given().
                header("Content-Type", "application/json").
                when().
                get("/workouts/1");

        Response response6 = RestAssured.given().
                header("Content-Type", "application/json").
                when().
                get("/workouts");

        // Check status code
        int statusCode = response6.getStatusCode();
        assertEquals(200, statusCode);

        // Check response body for correct response
        String returnString = response6.getBody().asString();
        String expected = "[{\"wName\":\"Aerobics\",\"wType\":\"cardio\",\"weight\":null,\"caloriesB\":211,\"user\":[],\"days\":[],\"users\":[]},{\"wName\":\"Stationary Bike\",\"wType\":\"cardio\",\"weight\":null,\"caloriesB\":176,\"user\":[],\"days\":[],\"users\":[]},{\"wName\":\"Stationary Bike medium effort\",\"wType\":\"cardio\",\"weight\":null,\"caloriesB\":247,\"user\":[],\"days\":[],\"users\":[]},{\"wName\":\"dusting\",\"wType\":\"cardio\",\"weight\":null,\"caloriesB\":70,\"user\":[],\"days\":[],\"users\":[]},{\"wName\":\"gardening\",\"wType\":\"cardio\",\"weight\":null,\"caloriesB\":176,\"user\":[],\"days\":[],\"users\":[]},{\"wName\":\"grocery shopping\",\"wType\":\"cardio\",\"weight\":null,\"caloriesB\":106,\"user\":[],\"days\":[],\"users\":[]},{\"wName\":\"hiking\",\"wType\":\"cardio\",\"weight\":null,\"caloriesB\":211,\"user\":[],\"days\":[],\"users\":[]},{\"wName\":\"house cleaning\",\"wType\":\"cardio\",\"weight\":null,\"caloriesB\":106,\"user\":[],\"days\":[],\"users\":[]},{\"wName\":\"jogging\",\"wType\":\"cardio\",\"weight\":null,\"caloriesB\":247,\"user\":[],\"days\":[],\"users\":[]},{\"wName\":\"running 12 minute miles\",\"wType\":\"cardio\",\"weight\":null,\"caloriesB\":282,\"user\":[],\"days\":[],\"users\":[]},{\"wName\":\"running 10 minute miles\",\"wType\":\"cardio\",\"weight\":null,\"caloriesB\":352,\"user\":[],\"days\":[],\"users\":[]},{\"wName\":\"running 7.5 minute miles\",\"wType\":\"cardio\",\"weight\":null,\"caloriesB\":428,\"user\":[],\"days\":[],\"users\":[]},{\"wName\":\"laundry, including folding clothes\",\"wType\":\"cardio\",\"weight\":null,\"caloriesB\":70,\"user\":[],\"days\":[],\"users\":[]},{\"wName\":\"mowing the lawn\",\"wType\":\"cardio\",\"weight\":null,\"caloriesB\":141,\"user\":[],\"days\":[],\"users\":[]},{\"wName\":\"brisk walking\",\"wType\":\"cardio\",\"weight\":null,\"caloriesB\":141,\"user\":[],\"days\":[],\"users\":[]},{\"wName\":\"weightlifting\",\"wType\":\"resistance Training\",\"weight\":null,\"caloriesB\":106,\"user\":[],\"days\":[],\"users\":[]},{\"wName\":\"yoga\",\"wType\":\"cardio\",\"weight\":null,\"caloriesB\":141,\"user\":[],\"days\":[],\"users\":[]},{\"wName\":\"skiing\",\"wType\":\"cardio\",\"weight\":\"0\",\"caloriesB\":250,\"user\":[],\"days\":[],\"users\":[]},{\"wName\":\"testcase\",\"wType\":\"test\",\"weight\":\"700\",\"caloriesB\":250,\"user\":[],\"days\":[],\"users\":[]}]";
        assertEquals(expected, returnString);
    }
    @Test
    public void DietPlansTest() {


        String userJson = "{\"calories\":0,\"protein\":0,\"carbs\":0,\"fat\":0,\"waterIntake\":0}";
        String userJson2 = "{\"id\":1,\"calories\":90,\"protein\":90,\"carbs\":90,\"fat\":90,\"waterIntake\":90}";

        // Send request and receive response (create user)
        Response response = RestAssured.given().
                header("Content-Type", "application/json").
                body(userJson).
                when().
                post("/diets");

        Response response2 = RestAssured.given().
                header("Content-Type", "application/json").
                body(userJson2).
                when().
                put("/diets/1");


        // Send request and receive response (create auction)
        Response response4 = RestAssured.given().
                header("Content-Type", "application/json").
                when().
                get("/diets/1");

        Response response6 = RestAssured.given().
                header("Content-Type", "application/json").
                when().
                get("/diets");

        // Check status code
        int statusCode = response6.getStatusCode();
        assertEquals(200, statusCode);

        // Check response body for correct response
        String returnString = response6.getBody().asString();
        String expected = "[{\"id\":1,\"calories\":90,\"protein\":90,\"carbs\":90,\"fat\":90,\"waterIntake\":90,\"userID\":0}]";
        assertEquals(expected, returnString);
    }
}

