package mobile_tests;

import config.AppiumConfig;
import dto.UserDto;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.SearchScreen;
import screens.SplashScreen;

import java.util.Random;

public class RegistrationTests extends AppiumConfig {

    int i = new Random().nextInt(1000)+ 1000;
    UserDto user =  UserDto.builder()
        .username("john" + i + "@gmail.com")
        .password("John123456!")
        .firstName("John")
        .lastName("Smith")
        .build();


    @Test
    public void registrationPositiveTest() {
        Assert.assertTrue(new SplashScreen(driver)
                .goToSearchScreen()
                .clickBtnDots()
                .clickBtnRegistration()
                .fillRegistrationForm(user)
                .clickCheckBox()
                .clickBtnYallaPositive()
                .isElementPresent_PopUpMessageSuccess("Registration success!"));
    }
}
