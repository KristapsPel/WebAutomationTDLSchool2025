package pages.AutomationExercisePages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomePage {
    private WebDriver driver;
    private ExtentTest test;

    @FindBy(partialLinkText = "Signup")
    private WebElement signUpLoginButton;

    @FindBy(xpath = "//p[text()='Consent']")
    private WebElement consentButton;

    public HomePage (WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;

        PageFactory.initElements(driver, this);
    }
    public void clickConsentButton () {
        this.test.log(Status.INFO, "Click on Consent button");
        consentButton.click();
    }

    public void clickOnSignUpLoginButton (){
        this.test.log(Status.INFO, "Click on Sign up / Login button");
        Assert.assertTrue(signUpLoginButton.isDisplayed(), "Can not find Signup Login button");
        signUpLoginButton.click();
        this.test.log(Status.PASS, "Click sucefuly on Login / Sign up button");
    }

}
