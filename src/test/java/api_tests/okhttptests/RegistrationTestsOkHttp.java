package api_tests.okhttptests;

import dto.ErrorMessageDtoString;
import dto.UserDto;
import interfaces.Base_Api;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.Random;

public class RegistrationTestsOkHttp implements Base_Api {

    SoftAssert softAssert = new SoftAssert();

    @Test(groups = {"smoke"})
    public void registrationPositiveTest() {
        int i = new Random().nextInt(1000) + 1000;
        UserDto bodyDto = UserDto
                .builder()
                .username("steve_dow" + i + "@gmail.com")
                .password("Steve12345$")
                .firstName("Steve")
                .lastName("Dow")
                .build();
        RequestBody requestBody = RequestBody.create(GSON.toJson(bodyDto),JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + REGISTRATION_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
            System.out.println(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(response.code(), 200);
    }

    @Test
    public void registrationNegativeTest() {
        int i = new Random().nextInt(1000) + 1000;
        UserDto bodyDto = UserDto
                .builder()
                .username("steve_dow" + i + "gmail.com")
                .password("Steve12345$")
                .firstName("Steve")
                .lastName("Dow")
                .build();
        RequestBody requestBody = RequestBody.create(GSON.toJson(bodyDto),JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + REGISTRATION_URL)
                .post(requestBody)
                .build();
        Response response;
        String responseBody;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
            System.out.println(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            responseBody = response.body().string();
            System.out.println(responseBody);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ErrorMessageDtoString errorMessageDtoString
                = GSON.fromJson(responseBody, ErrorMessageDtoString.class);
        softAssert.assertEquals(errorMessageDtoString.getStatus(), 400);
        softAssert.assertEquals(errorMessageDtoString.getError(), "Bad Request");
        softAssert.assertTrue(errorMessageDtoString.getMessage().toString()
                .contains("must be a well-formed email address"));
        softAssert.assertAll();
    }
}
