package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

public class SearchScreen  extends BaseScreen{
    public SearchScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@content-desc='More options']")
    AndroidElement btnDots;

    @FindBy(xpath = "//*[@text='Registration' and @resource-id ='com.telran.ilcarro:id/title']")
    AndroidElement btnRegistration;

    @FindBy(xpath = "//*[@text='Login' and @resource-id ='com.telran.ilcarro:id/title']")
    AndroidElement btnLogin;

    @FindBy(xpath = "//hierarchy/android.widget.Toast")
    AndroidElement popUpMessageSuccess;

    public SearchScreen clickBtnDots() {
        pause(3000);
        btnDots.click();
        return this;
    }

    public RegistrationScreen clickBtnRegistration() {
        //pause(3000);
        btnRegistration.click();
        return new RegistrationScreen(driver);
    }

    public boolean isElementPresent_PopUpMessageSuccess(String text) {

        return isTextInElementPresent(popUpMessageSuccess, text, 5000);
    }



}
