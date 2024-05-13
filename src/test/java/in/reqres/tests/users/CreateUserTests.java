package in.reqres.tests.users;

import in.reqres.model.request.create.CreateUserRequest;
import in.reqres.model.response.create.UserCreateResponse;
import in.reqres.tests.ApiTestBase;
import in.reqres.utils.ApiUtils;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class CreateUserTests extends ApiTestBase {

    @Test
    public void createUser (){
        CreateUserRequest request = new CreateUserRequest("morpheus","leader");
        UserCreateResponse userCreateResponse = given()
                .spec(ApiUtils.jsonRequestSpec())
                .body(request)
                .when()
                .post("users")
                .then()
                    .statusCode(201)
                    .extract().as(UserCreateResponse.class);

        assertEquals("morpheus", userCreateResponse.getName());
        assertEquals("leader", userCreateResponse.getJob());
        assertNotNull(userCreateResponse.getId());
        assertNotNull(userCreateResponse.getCreatedAt());

    }
}
