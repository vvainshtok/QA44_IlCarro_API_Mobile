package screens;

import dto.CarDto;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

public class AddNewCarScreen extends BaseScreen {
    public AddNewCarScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(id = "com.telran.ilcarro:id/editSerial")
    AndroidElement fieldSerialNumber;
    @FindBy(id = "com.telran.ilcarro:id/editMan")
    AndroidElement fieldManufacture;
    @FindBy(id = "com.telran.ilcarro:id/editModel")
    AndroidElement fieldModel;
    @FindBy(id = "com.telran.ilcarro:id/editCity")
    AndroidElement fieldCity;
    @FindBy(id = "com.telran.ilcarro:id/editPrice")
    AndroidElement fieldPrice;
    @FindBy(id = "com.telran.ilcarro:id/editCarClass")
    AndroidElement fieldCarClass;
    @FindBy(id = "com.telran.ilcarro:id/text1")
    AndroidElement fieldFuel;
    @FindBy(id = "com.telran.ilcarro:id/editYear")
    AndroidElement fieldYear;
    @FindBy(id = "com.telran.ilcarro:id/editSeats")
    AndroidElement fieldSeats;
    @FindBy(id = "com.telran.ilcarro:id/editAbout")
    AndroidElement fieldAbout;
    @FindBy(id = "com.telran.ilcarro:id/addCarBtn")
    AndroidElement btnAddCar;

    @FindBy(xpath = "//*[@text=")
    AndroidElement electric;

    @FindBy(xpath = "//hierarchy/android.widget.Toast")
    AndroidElement popUpMessageSuccess;



    public AddNewCarScreen typeAddNewCarForm(CarDto car) {
        fieldSerialNumber.sendKeys(car.getSerialNumber());
        fieldManufacture.sendKeys(car.getManufacture());
        fieldModel.sendKeys(car.getModel());
        fieldCity.sendKeys(car.getCity());
        fieldPrice.sendKeys(Double.toString(car.getPricePerDay()));
        fieldCarClass.sendKeys(car.getCarClass());

        int height = driver.manage().window().getSize().height;
        int width = driver.manage().window().getSize().width;
        System.out.println("Height: " + height + ", with: " + width);

        TouchAction<?> touchAction = new TouchAction<>(driver);
        touchAction.longPress(PointOption.point(width/2, height*3/4))
                .moveTo(PointOption.point(width/2, height/4))
                .release()
                .perform();

        typeFuel(car.getFuel());
        fieldYear.sendKeys(car.getYear());
        fieldSeats.sendKeys(""+car.getSeats());
        fieldAbout.sendKeys(car.getAbout());
        return this;
    }

    private void typeFuel(String fuel) {
        fieldFuel.click();
        pause(2000);
        AndroidElement element = driver.findElement
                (By.xpath("//*[@text='"+fuel+"']"));
        element.click();
    }

    public MyCarsScreen clickBtnAddCarPositive() {
        btnAddCar.click();
        return new MyCarsScreen(driver);
    }

    public boolean isElementPresent_PopUpMessageSuccess(String text) {
        return isTextInElementPresent(popUpMessageSuccess, text, 5000);
    }
}
