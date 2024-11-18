package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class MyCarsScreen extends BaseScreen{
    public MyCarsScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }
    @FindBy(xpath = "//hierarchy/android.widget.Toast")
    AndroidElement popUpMessageSuccess;

    @FindBy(id = "com.telran.ilcarro:id/addCarBtn")
    AndroidElement btnAddNewCar;

    @FindBy(xpath = "//*[@resource-id='com.telran.ilcarro:id/LinearLayout']")
    List<AndroidElement> carsList;

    @FindBy(id = "android:id/button1")
    AndroidElement btnYes;

    int countBefore, countAfter;

    public AddNewCarScreen clickBtnAddNewCar() {
        btnAddNewCar.click();
        return new AddNewCarScreen(driver);
    }

    public boolean isElementPresent_PopUpMessageSuccess(String text) {
        return isTextInElementPresent(popUpMessageSuccess, text, 5000);
    }

    public MyCarsScreen deleteFirstCar() {
        countBefore = carsList.size();
        System.out.println("Before --> " + countBefore);
        AndroidElement first = carsList.get(0);
        Rectangle rectangle = first.getRect();
        int xFrom = rectangle.getX() + rectangle.width/8;
        int xTo = rectangle.getX() + rectangle.width*7/8;;
        int y = rectangle.getY() + rectangle.getHeight()/2;
        TouchAction<?> touchAction = new TouchAction<>(driver);
        touchAction.longPress(PointOption.point(xFrom, y))
                .moveTo(PointOption.point(xTo, y))
                .release()
                .perform();
        btnYes.click();
        pause(2000);
        countAfter = carsList.size();
        System.out.println("After --> " + countAfter);
        return this;
    }


    public MyCarsScreen isListOfCarsLessOnOne() {
        Assert.assertEquals(countBefore - countAfter, 1);

        return this;
    }
}
