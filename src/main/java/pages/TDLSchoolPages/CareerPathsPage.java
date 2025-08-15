package pages.TDLSchoolPages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

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

    public void checkPageTitle (String expectedValue) {
        test.log(Status.INFO, "Check that Title "+expectedValue+" is displayed");
        Assert.assertEquals(pageTitle.getText(), expectedValue,
                "Page title was not as expected");
        test.log(Status.PASS, "Page title was:"+expectedValue);
    }


}
