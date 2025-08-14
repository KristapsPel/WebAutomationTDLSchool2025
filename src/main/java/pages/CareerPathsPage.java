package pages;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CareerPathsPage {
    @FindBy(tagName = "h1")
    private WebElement pageTitle;

    private WebDriver driver;
    private ExtentTest test;

    public CareerPathsPage (WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;

        PageFactory.initElements(driver, this);
    }


}
