package in.reqres.test.singleuser;

import in.reqres.model.response.singleuser.UserResponse;
import in.reqres.test.ApiTestBase;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class SingleUserTests extends ApiTestBase {

    @Test
    public void testGetUserDetails() {
        UserResponse response = given()
                .when()
                .pathParam("userId", 2)
                .when()
                .get("/users/{userId}")
                .then()
                .statusCode(200)
                .extract().as(UserResponse.class);

        assertEquals(2, response.getData().getId());
        assertEquals("janet.weaver@reqres.in", response.getData().getEmail());
        assertEquals("Janet", response.getData().getFirstName());
        assertEquals("Weaver", response.getData().getLastName());
        assertEquals("https://reqres.in/img/faces/2-image.jpg", response.getData().getAvatar());

        assertEquals("https://reqres.in/#support-heading", response.getSupport().getUrl());
        assertTrue(response.getSupport().getText().contains("To keep ReqRes free,"));
        // assertEquals("To keep ReqRes free, contributions towards server costs are appreciated!", response.getSupport().getText());
    }


    @Test
    public void testGetUserDetailsV2() {
        given()
                .when()
                .pathParam("userId", 2)
                .when()
                .get("/users/{userId}")
                .then()
                .statusCode(200)
                .body("data.id", equalTo(2))
                .body("data.email", equalTo("janet.weaver@reqres.in"))
                .body("support.text", containsString("To keep ReqRes free,"));
    }


}
