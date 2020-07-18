package org.techmeskills.aqa5.auf.apiTests;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.apache.http.protocol.HTTP;
import org.techmeskills.aqa5.auf.baseEntity.BaseApiTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AlexApiTests extends BaseApiTest {

    @Test
    public void listUsers() {

        String endpoint = "/api/users?page=2";

        given()
                .when()
                .get(endpoint)
                .then().log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void singleUsers() {

        String endpoint = "/api/users/2?/api/users/2";

        given()
                .when()
                .get(endpoint)
                .then().log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void singleUsersNotFound() {

        String endpoint = "/api/users/23?/api/users/23";

        given()
                .when()
                .get(endpoint)
                .then().log().body()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void listResource() {

        String endpoint = "/api/unknown?/api/unknown";

        given()
                .when()
                .get(endpoint)
                .then().log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void singleResource() {

        String endpoint = "/api/unknown/2?/api/unknown/2";

        given()
                .when()
                .get(endpoint)
                .then().log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void singleResourceNotFound() {

        String endpoint = "/api/unknown/23";

        given()
                .when()
                .get(endpoint)
                .then().log().body()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void delayedResponse() {

        String endpoint = "/api/users?delay=3";

        given()
                .when()
                .get(endpoint)
                .then().log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void registerSuccessfull() {

        String endpoint = "/api/register?/api/register=";

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("email", "eve.holt@reqres.in");
        jsonAsMap.put("password", "pistol");

        given()
                .body(jsonAsMap)
                .when()
                .post(endpoint)
                .then()
                .log()
                .body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void registerUnSuccessfull() {

        String endpoint = "/api/register?/api/register=";

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("email", "sydney@fife");
        jsonAsMap.put("password", "");

        given()
                .body(jsonAsMap)
                .when()
                .post(endpoint)
                .then()
                .log()
                .body()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void loginSuccessfull() {

        String endpoint = "/api/login?/api/login";

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("email", "eve.holt@reqres.in");
        jsonAsMap.put("password", "cityslicka");

        given()
                .body(jsonAsMap)
                .when()
                .post(endpoint)
                .then()
                .log()
                .body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void loginUnSuccessfull() {

        String endpoint = "/api/login?/api/login";

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("email", "peter@klaven");
        jsonAsMap.put("password", "");

        given()
                .body(jsonAsMap)
                .when()
                .post(endpoint)
                .then()
                .log()
                .body()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void Create() {

        String endpoint = "/api/users?/api/users";

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "morpheus");
        jsonAsMap.put("job", "leader");

        given()
                .body(jsonAsMap)
                .when()
                .post(endpoint)
                .then()
                .log()
                .body()
                .statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    public void updatePUT() {

        String endpoint = "/api/users/2?/api/users/2";

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "morpheus");
        jsonAsMap.put("job", "zion resident");

        given()
                .body(jsonAsMap)
                .when()
                .put(endpoint)
                .then()
                .log()
                .body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void updatePATCH() {

        String endpoint = "/api/users/2?/api/users/2";

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "morpheus");
        jsonAsMap.put("job", "zion resident");

        given()
                .body(jsonAsMap)
                .when()
                .patch(endpoint)
                .then()
                .log()
                .body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void delete() {

        String endpoint = "/api/users/2?/api/users/2";

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "morpheus");
        jsonAsMap.put("job", "zion resident");

        given()
                .body(jsonAsMap)
                .when()
                .delete(endpoint)
                .then()
                .log()
                .body()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
}

