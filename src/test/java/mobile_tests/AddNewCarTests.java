package mobile_tests;

import config.AppiumConfig;
import dto.UserDto;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.SearchScreen;
import screens.SplashScreen;

public class AddNewCarTests extends AppiumConfig {

    SearchScreen searchScreen;

    @BeforeMethod
    public void login() {
        UserDto user =  UserDto.builder()
                .username("vv17@gmail.com")
                .password("QWErty123!!!")
                .build();
        searchScreen = new SplashScreen(driver)
                .goToSearchScreen()
                .clickBtnDots()
                .clickBtnLogin()
                .typeLoginFormPositive(user);
    }


    @Test
    public void addNewCarPositiveTest() {
        searchScreen.clickBtnDots()
                .clickBtnMyCars();

    }

}
