package api_tests.rest;

import api.AuthenticationController;
import dto.ErrorMessageDtoString;
import dto.RegistrationBodyDto;
import interfaces.Base_Api;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTestsRest extends AuthenticationController {

    @Test
    public void registrationPositiveTest() {
        int i = new Random().nextInt(1000) + 1000;
        RegistrationBodyDto registrationBodyDto = RegistrationBodyDto.builder()
                .firstName("John")
                .lastName("Smith")
                .username("john_smith" + i + "@gmail.com")
                .password("John1234!")
                .build();
        Assert.assertEquals(registrationLogin(registrationBodyDto, REGISTRATION_URL)
                .getStatusCode(), 200);
    }

    @Test
    public void registrationNegativeTest_emptyPassword() {
        int i = new Random().nextInt(1000) + 1000;
        RegistrationBodyDto registrationBodyDto = RegistrationBodyDto.builder()
                .firstName("John")
                .lastName("Smith")
                .username("john_smith" + i + "@gmail.com")
                .password("")
                .build();
        Assert.assertEquals(registrationLogin(registrationBodyDto, REGISTRATION_URL)
                .getBody()
                .as(ErrorMessageDtoString.class)
                .getError(), "Bad Request");
    }
}
