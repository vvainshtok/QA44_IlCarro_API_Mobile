package api;

import dto.UserDto;
import interfaces.Base_Api;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthenticationController implements Base_Api {

    protected Response registrationLogin(UserDto registrationBodyDto, String url) {
        return given()
                .body(registrationBodyDto)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL + url)
                .thenReturn();
    }
}
