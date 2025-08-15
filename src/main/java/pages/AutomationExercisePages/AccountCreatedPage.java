package pages.AutomationExercisePages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class AccountCreatedPage {
    private WebDriver driver;
    private ExtentTest test;

    @FindBy(css = "h2 > b")
    private WebElement confirmAccountCreatedText;

    public AccountCreatedPage(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;

        PageFactory.initElements(driver, this);
    }

    public void confirmAccountCreation () {
        test.log(Status.INFO, "Check that confirmation message is visible after creating account");
        Assert.assertEquals(confirmAccountCreatedText.getText(),
                "ACCOUNT CREATED!", "Confirmation message is not as expected");
        test.log(Status.PASS, "Account Created message is visible");
    }
}
