package mobile_tests;

import config.AppiumConfig;
import dto.CarDto;
import dto.Fuel;
import dto.UserDto;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.SearchScreen;
import screens.SplashScreen;

import java.util.Random;

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
        int i = new Random().nextInt(1000) + 1000;
        CarDto car = CarDto.builder()
                .serialNumber("777"+i)
                .manufacture("Tesla")
                .model("Model X")
                .year("2004")
                .fuel(Fuel.ELECTRIC.getFuel())
                .seats(4)
                .carClass("B")
                .pricePerDay(1.23)
                .city("Haifa")
                .about("Ma car")
                .build();

        searchScreen.clickBtnDots()
                .clickBtnMyCars()
                .clickBtnAddNewCar()
                .typeAddNewCarForm(car)
                .clickBtnAddCarPositive()
                .isElementPresent_PopUpMessageSuccess("Car added");


    }

}
