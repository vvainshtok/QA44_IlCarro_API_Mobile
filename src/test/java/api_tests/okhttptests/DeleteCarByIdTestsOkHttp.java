package api_tests.okhttptests;

import dto.*;
import interfaces.Base_Api;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeleteCarByIdTestsOkHttp implements Base_Api {

    TokenDto token;
    CarDto carDto;
    ErrorMessageDtoString errorMessageDtoString;

    @BeforeClass(alwaysRun = true)
    public void login() {
        UserDto bodyDto = UserDto
                .builder()
                .username("vv17@gmail.com")
                .password("QWErty123!!!")
                .build();
        RequestBody requestBody = RequestBody.create(GSON.toJson(bodyDto),JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
            System.out.println(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            token = GSON.fromJson(response.body().string(), TokenDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(token);
    }

    @BeforeMethod(alwaysRun = true)
    public void getSerialNumber() {
        Request request = new Request.Builder()
                .url(BASE_URL + GET_USER_CARS_URL)
                .addHeader("Authorization", token.getAccessToken())
                .get()
                .build();
        Response response;
        CarsDto carsDto;

        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (response.isSuccessful()) {
            try {
                carsDto = GSON.fromJson(response.body().string(), CarsDto.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            carDto = carsDto.getCars()[0];
        }
        else
            System.out.println("Get all users cars is not successful");

    }


    @Test
    public void deleteCarByIdPositiveTest() {
        String idCar = carDto.getSerialNumber();
        Request request = new Request.Builder()
                .url(BASE_URL + DELETE_CAR_URL + "/" + idCar)
                .addHeader("Authorization", token.getAccessToken())
                .delete()
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(response.isSuccessful());


    }

    @Test(groups = {"smoke"})
    public void deleteCarByIdNegativeTest() {
        String idCar = carDto.getSerialNumber();
        Request request = new Request.Builder()
                .url(BASE_URL + DELETE_CAR_URL +  "/" + 456456364)
                .addHeader("Authorization", token.getAccessToken())
                .delete()
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            errorMessageDtoString
                    = GSON.fromJson(response.body().string(), ErrorMessageDtoString.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assert.assertEquals(errorMessageDtoString.getStatus(), 400);

    }

    @Test
    public void deleteCarByIdNegativeTest_wrongToken() {
        String idCar = carDto.getSerialNumber();
        Request request = new Request.Builder()
                .url(BASE_URL + DELETE_CAR_URL + idCar)
                .addHeader("Authorization", "wrongToken")
                .delete()
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            errorMessageDtoString
                    = GSON.fromJson(response.body().string(), ErrorMessageDtoString.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(errorMessageDtoString.getMessage());
        System.out.println(errorMessageDtoString.getError());
        Assert.assertEquals(errorMessageDtoString.getStatus(), 401);
    }
}
