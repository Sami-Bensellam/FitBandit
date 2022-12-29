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
public class TestDietPlan {

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
    public void getUsersTest() {


        String userJson = "{\"userName\":\"FirstUser\",\"password\":\"testing\",\"emailId\":\"john@somemail.com\",\"gain\":1,\"muscle\":0,\"age\":80}";

        // Send request and receive response (create user)
        Response response = RestAssured.given().
                header("Content-Type", "application/json").
                body(userJson).
                when().
                post("/users");


        // Send request and receive response (create auction)
        Response response6 = RestAssured.given().
                header("Content-Type", "application/json").
                when().
                get("/users");

        // Check status code
        int statusCode = response6.getStatusCode();
        assertEquals(200, statusCode);

        // Check response body for correct response
        String returnString = response6.getBody().asString();
        String expected = "[{\"id\":1,\"userName\":\"Admin\",\"password\":\"User\",\"emailId\":\"john@somemail.com\",\"gain\":0,\"muscle\":0,\"age\":0,\"dietPlan\":null,\"workouts\":[],\"weeks\":[],\"days\":[],\"threads\":[{\"id\":1,\"title\":\"Dieting chat\",\"text\":\"Rules:\\n This is strictly about diet not misinformation allowed.\",\"replies\":[]},{\"id\":2,\"title\":\"Weightloss chat\",\"text\":\"Rules:\\n This is strictly about diet not misinformation allowed.\",\"replies\":[]},{\"id\":3,\"title\":\"Weightdain chat\",\"text\":\"Rules:\\n This is strictly about diet not misinformation allowed.\",\"replies\":[]},{\"id\":4,\"title\":\"Offtopic\",\"text\":\"Rules: \\n its the wild west here go crazy\",\"replies\":[]}],\"replies\":[],\"weight\":0,\"height\":0,\"gender\":false,\"admin\":true},{\"id\":2,\"userName\":\"FirstUser\",\"password\":\"testing\",\"emailId\":\"john@somemail.com\",\"gain\":1,\"muscle\":0,\"age\":80,\"dietPlan\":null,\"workouts\":[],\"weeks\":[],\"days\":[],\"threads\":[],\"replies\":[],\"weight\":0,\"height\":0,\"gender\":false,\"admin\":false}]";
        assertEquals(expected, returnString);
    }


    /**
     * Test GET method for specific user
     * Tests the modified post method
     */
    @Test
    public void getUserTest() {


        // Send request and receive response (create user)
        Response response = RestAssured.given().
                header("Content-Type", "application/json").
                when().
                post("/users/register/Testuser2/testpassword2/tester@testing.com/130/66/16/1/False");

        Response response2 = RestAssured.given().
                header("Content-Type", "application/json").
                when().
                post("/users/3/dietcreate");


        String userJson = "{\"userName\":\"FirstUser\",\"password\":\"testing\",\"emailId\":\"john@somemail.com\",\"gain\":1,\"muscle\":0,\"age\":80,\"weight\":63,\"height\":0,\"gender\":false,\"admin\":false}";

        // Send request and receive response (create user)
        Response response3 = RestAssured.given().
                header("Content-Type", "application/json").
                body(userJson).
                when().
                put("/users/2");

        // Send request and receive response (create auction)
        Response response6 = RestAssured.given().
                header("Content-Type", "application/json").
                when().
                get("/users/3");
        // Check status code
        int statusCode = response6.getStatusCode();
        assertEquals(200, statusCode);


        // Check response body for correct response
        String returnString = response6.getBody().asString();
        String expected = "{\"id\":3,\"userName\":\"Testuser2\",\"password\":\"testpassword2\",\"emailId\":\"tester@testing.com\",\"gain\":1,\"muscle\":0,\"age\":16,\"dietPlan\":{\"id\":1,\"calories\":1855,\"protein\":115,\"carbs\":185,\"fat\":81,\"waterIntake\":1,\"userID\":3},\"workouts\":[],\"weeks\":[],\"days\":[],\"threads\":[],\"replies\":[],\"weight\":130,\"height\":66,\"gender\":false,\"admin\":true}";
        assertEquals(expected, returnString);
    }

    @Test
    public void getForumandReplyTest() {


        String userJson = "{\"title\":\"Newtest2\",\"text\":\"TESTING IF THE BODY WORKS\"}";

        // Send request and receive response (create user)
        Response response = RestAssured.given().
                header("Content-Type", "application/json").
                when().
                post("/threads/Newtest/ThreadBody/3");

        Response response2 = RestAssured.given().
                header("Content-Type", "application/json").
                body(userJson).
                when().
                post("/threads");

        Response response3 = RestAssured.given().
                header("Content-Type", "application/json").
                body(userJson).
                when().
                get("/threads/1");

        // Send request and receive response (create auction)
        Response response6 = RestAssured.given().
                header("Content-Type", "application/json").
                when().
                get("/threads");

        // Check status code
        int statusCode = response6.getStatusCode();
        assertEquals(200, statusCode);

        // Check response body for correct response
        String returnString = response6.getBody().asString();
        String expected = "[{\"id\":1,\"title\":\"Dieting chat\",\"text\":\"Rules:\\n This is strictly about diet not misinformation allowed.\",\"replies\":[]},{\"id\":2,\"title\":\"Weightloss chat\",\"text\":\"Rules:\\n This is strictly about diet not misinformation allowed.\",\"replies\":[]},{\"id\":3,\"title\":\"Weightdain chat\",\"text\":\"Rules:\\n This is strictly about diet not misinformation allowed.\",\"replies\":[]},{\"id\":4,\"title\":\"Offtopic\",\"text\":\"Rules: \\n its the wild west here go crazy\",\"replies\":[]},{\"id\":5,\"title\":\"Newtest2\",\"text\":\"TESTING IF THE BODY WORKS\",\"replies\":[]}]";
        assertEquals(expected, returnString);
    }
    @Test
    public void getTest() {



        // Send request and receive response (create user)

        Response response3 = RestAssured.given().
                header("Content-Type", "application/json").
                when().
                get("/threads/2");


        // Check status code
        int statusCode = response3.getStatusCode();
        assertEquals(200, statusCode);

        // Check response body for correct response
        String returnString = response3.getBody().asString();
        String expected = "{\"id\":2,\"title\":\"Weightloss chat\",\"text\":\"Rules:\\n This is strictly about diet not misinformation allowed.\",\"replies\":[]}";
        assertEquals(expected, returnString);
    }


}
