package org.techmeskills.aqa5.auf.apiTests;

import org.apache.http.HttpStatus;
import org.techmeskills.aqa5.auf.baseEntity.BaseApiTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class AlexApiTests extends BaseApiTest {

    @Test
    public void listUsers() {

        String endpoint = "/api/users?page=2";

        given()
                .when()
                .get(endpoint)
                .then().log().body()
                .statusCode(HttpStatus.SC_OK)
                .body("page", is(2))
                .body("per_page", is(6))
                .body("total", is(12));

    }

    @Test
    public void singleUsers() {

        String endpoint = "/api/users/2?/api/users/2";

        given()
                .when()
                .get(endpoint)
                .then().log().body()
                .statusCode(HttpStatus.SC_OK)
                .body("data.id", is(2))
                .body("data.email", equalTo("janet.weaver@reqres.in"))
                .body("data.first_name", equalTo("Janet"))
                .body("data.last_name", equalTo("Weaver"));
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
                .statusCode(HttpStatus.SC_OK)
                .body("page", is(1))
                .body("per_page", is(6))
                .body("total", is(12))
                .body("data.get(0).id", is(1))
                .body("data.get(1).id", is(2))
                .body("data.get(2).id", is(3))
                .body("data.get(3).id", is(4))
                .body("data.get(4).id", is(5))
                .body("data.get(5).id", is(6));
    }

    @Test
    public void singleResource() {

        String endpoint = "/api/unknown/2?/api/unknown/2";

        given()
                .when()
                .get(endpoint)
                .then().log().body()
                .statusCode(HttpStatus.SC_OK)
                .body("data.id", is(2))
                .body("data.name", equalTo("fuchsia rose"))
                .body("data.year", is(2001));

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
                .statusCode(HttpStatus.SC_OK)
                .body("page", is(1))
                .body("per_page", is(6))
                .body("total", is(12))
                .body("data.get(0).id", is(1))
                .body("data.get(1).id", is(2))
                .body("data.get(2).id", is(3))
                .body("data.get(3).id", is(4))
                .body("data.get(4).id", is(5))
                .body("data.get(5).id", is(6));
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
                .statusCode(HttpStatus.SC_OK)
                .body("id", is(4))
                .body("token", equalTo("QpwL5tke4Pnpja7X4"));

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
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("error", equalTo("Missing password"));
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
                .statusCode(HttpStatus.SC_OK)
                .body("token", equalTo("QpwL5tke4Pnpja7X4"));

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
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("error", equalTo("Missing password"));
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
                .statusCode(HttpStatus.SC_CREATED)
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("leader"));

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
                .statusCode(HttpStatus.SC_OK)
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("zion resident"));

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
                .statusCode(HttpStatus.SC_OK)
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("zion resident"));
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

