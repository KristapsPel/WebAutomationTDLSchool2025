package pages.AutomationExercisePages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoginSignupPage {
    private WebDriver driver;
    private ExtentTest test;

    @FindBy(name = "name")
    private WebElement nameInput;

    @FindBy(xpath = "//input[@data-qa='signup-email']")
    private WebElement emailInput;

    @FindBy(xpath = "//button[@data-qa='signup-button']")
    private WebElement signUpButton;

    public LoginSignupPage (WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;

        PageFactory.initElements(driver, this);
    }

    public void enterName (String name) {
        test.log(Status.INFO, "Enter "+name+" inside name field");
        nameInput.sendKeys(name);
        test.log(Status.PASS, "Name successfully entered");
    }

    public void enterEmail (String email) {
        // Get the current timestamp
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String currentTimeStamp = now.format(formatter);
        email=currentTimeStamp+email;
        test.log(Status.INFO, "Enter "+email+" inside name field");
        emailInput.sendKeys(email);
        test.log(Status.PASS, "Email successfully entered");
    }

    public void fillOutSignUpForm (String email, String name) {
        enterName(name);
        enterEmail(email);
        signUpButton.click();
    }
}
