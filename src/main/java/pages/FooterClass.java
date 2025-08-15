package pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FooterClass {
    @FindBy(name = "email")
    private WebElement emailInput;

    private WebDriver driver;
    private ExtentTest test;

    public FooterClass (WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;

        PageFactory.initElements(driver, this);
    }

    public void enterEmail(String email) {
        test.log(Status.INFO, "Enter text in email input field");
        emailInput.sendKeys(email);
        test.log(Status.PASS, email+" entered in in input field");
    }
}
