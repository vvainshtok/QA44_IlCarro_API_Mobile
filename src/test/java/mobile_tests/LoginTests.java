package mobile_tests;

import config.AppiumConfig;
import dto.UserDto;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.SplashScreen;

public class LoginTests extends AppiumConfig {

    @Test
    public void loginPositiveTest() {
        UserDto user =  UserDto.builder()
                .username("vv17@gmail.com")
                .password("QWErty123!!!")
                .build();
        Assert.assertTrue(new SplashScreen(driver)
                .goToSearchScreen()
                .clickBtnDots()
                .clickBtnLogin()
                .typeLoginFormPositive(user)
                .isElementPresent_PopUpMessageSuccess("Login success!"));
    }

    @Test
    public void loginNegativeTest_wrongPassword() {
        UserDto user =  UserDto.builder()
                .username("vv17@gmail.com")
                .password("wrong")
                .build();
        Assert.assertTrue(new SplashScreen(driver)
                .goToSearchScreen()
                .clickBtnDots()
                .clickBtnLogin()
                .typeLoginFormNegative(user)
                .validateErrorMessage("Login or Password incorrect"));
    }
}
