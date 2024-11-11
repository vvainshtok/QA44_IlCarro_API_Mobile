package api_tests.rest;

import api.CarController;
import dto.CarDto;
import dto.Fuel;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewCarTestsRest extends CarController {

    @Test
    public void addNewCarPositiveTest() {
       int i = new Random().nextInt(1000) + 1000;
        CarDto car = CarDto.builder()
                .serialNumber("333-"+i)
                .manufacture("Opel")
                .model("Astra")
                .year("2020")
                .fuel(Fuel.DIESEL.getFuel())
                .seats(4)
                .carClass("A")
                .pricePerDay(100.23)
                .city("Haifa")
                .build();
        Assert.assertEquals(addNewCarResponse(car, tokenDto.getAccessToken()).getStatusCode(), 200);
    }

    @Test
    public void addNewCarNegativeTest_wrongToken() {
        int i = new Random().nextInt(1000) + 1000;
        CarDto car = CarDto.builder()
                .serialNumber("333-"+i)
                .manufacture("Opel")
                .model("Astra")
                .year("2020")
                .fuel(Fuel.DIESEL.getFuel())
                .seats(4)
                .carClass("A")
                .pricePerDay(100.23)
                .city("Haifa")
                .build();
        Assert.assertEquals(addNewCarResponse(car, "wrong_token")
                .getStatusCode(), 401);
    }

    @Test
    public void addNewCarNegativeTest_noSerialNumber() {
        int i = new Random().nextInt(1000) + 1000;
        CarDto car = CarDto.builder()
        //        .serialNumber("333-"+i)
                .manufacture("Opel")
                .model("Astra")
                .year("2020")
                .fuel(Fuel.DIESEL.getFuel())
                .seats(4)
                .carClass("A")
                .pricePerDay(100.23)
                .city("Haifa")
                .build();
        Assert.assertTrue(addNewCarResponse(car, tokenDto.getAccessToken())
                .getBody().print().contains("must not be blank"));
    }
}
