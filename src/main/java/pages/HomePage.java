package pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class HomePage {
    @FindBy(className = "navigation__logo")
    private WebElement logo;

    @FindBy(linkText = "Career Paths")
    private WebElement careerPaths;

    @FindBy(css = "a.course-suggestions__course-card--active")
    private List<WebElement> listOfVisibleCourses;

    private WebDriver driver;
    private ExtentTest test;

    public HomePage (WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;

        PageFactory.initElements(driver, this);
    }

    public void verifyTDLSchoolLogoIsDisplayed() {
        this.test.log(Status.INFO, "Verify that TDL School logo is displayed");
        Assert.assertTrue(this.logo.isDisplayed(), "TDL School logo is not displayed on page");
        this.test.log(Status.PASS, "TDL School logo is Displayed in page");
    }

    public void clickOnCareerPathButton () {
        this.test.log(Status.INFO, "Click on Career Path");
        Assert.assertTrue(careerPaths.isDisplayed(), "Can not find Career Path button");
        careerPaths.click();
        test.log(Status.PASS, " Click action on Career path did work");
    }

    public void checkUpcomingLectureCount(int expectedCount) {
        this.test.log(Status.INFO,
                "Check that "+expectedCount+" upcoming courses is Displayed.");
        Assert.assertEquals(listOfVisibleCourses.size(), expectedCount,
                "Expected course count is not as expected");
        this.test.log(Status.PASS, expectedCount+" courses is visible in Upcoming section.");
    }

}
