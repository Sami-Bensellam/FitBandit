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
public class TestReplies {

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
    public void DaysTest() {


        String userJson = "{\"caloriesConsumed\":230}";
        String userJson2 = "{\"id\":1,\"caloriesConsumed\":600}";

        // Send request and receive response (create user)
        Response response = RestAssured.given().
                header("Content-Type", "application/json").
                body(userJson).
                when().
                post("/days");

        Response response2 = RestAssured.given().
                header("Content-Type", "application/json").
                body(userJson2).
                when().
                put("/days/1");


        // Send request and receive response (create auction)
        Response response4 = RestAssured.given().
                header("Content-Type", "application/json").
                when().
                get("/days/1");

        Response response6 = RestAssured.given().
                header("Content-Type", "application/json").
                when().
                get("/days");

        // Check status code
        int statusCode = response6.getStatusCode();
        assertEquals(200, statusCode);

        // Check response body for correct response
        String returnString = response6.getBody().asString();
        String expected = "[{\"id\":1,\"caloriesConsumed\":600,\"workouts\":[]}]";
        assertEquals(expected, returnString);
    }


    @Test
    public void WeekTest() {

        String userJson = "{\"weight\":150}";
        String userJson2 = "{\"weight\":250}";
        String userJson3 = "{\"id\":2,\"weight\":120}";


        // Send request and receive response (create user)
        Response response = RestAssured.given().
                header("Content-Type", "application/json").
                body(userJson).
                when().
                post("/weeks");

        Response response2 = RestAssured.given().
                header("Content-Type", "application/json").
                body(userJson2).
                when().
                post("/weeks");


        Response response3 = RestAssured.given().
                header("Content-Type", "application/json").
                body(userJson3).
                when().
                put("/weeks/2");


        // Send request and receive response (create auction)
        Response response4 = RestAssured.given().
                header("Content-Type", "application/json").
                when().
                get("/weeks/1");

        Response response6 = RestAssured.given().
                header("Content-Type", "application/json").
                when().
                get("/weeks");

        // Check status code
        int statusCode = response6.getStatusCode();
        assertEquals(200, statusCode);

        // Check response body for correct response
        String returnString = response6.getBody().asString();
        String expected = "[{\"id\":1,\"weight\":150},{\"id\":2,\"weight\":120}]";
        assertEquals(expected, returnString);
    }


    @Test
    public void RepliesTest() {


        String userJson = "{\"userName\":\"FirstUser\",\"password\":\"testing\",\"emailId\":\"jo@somemail.com\",\"gain\":1,\"muscle\":0,\"age\":81}";

        // Send request and receive response (create user)
        Response response = RestAssured.given().
                header("Content-Type", "application/json").
                body(userJson).
                when().
                post("/users");

        Response response2 = RestAssured.given().
                header("Content-Type", "application/json").
                body(userJson).
                when().
                post("/replies/Newreplytest/2/2");


        // Send request and receive response (create auction)
        Response response4 = RestAssured.given().
                header("Content-Type", "application/json").
                when().
                get("/replies/1");

        Response response6 = RestAssured.given().
                header("Content-Type", "application/json").
                when().
                get("/replies");

        // Check status code
        int statusCode = response6.getStatusCode();
        assertEquals(200, statusCode);

        // Check response body for correct response
        String returnString = response6.getBody().asString();
        String expected = "[{\"id\":1,\"txt\":\"Newreplytest\"}]";
        assertEquals(expected, returnString);
    }

}

