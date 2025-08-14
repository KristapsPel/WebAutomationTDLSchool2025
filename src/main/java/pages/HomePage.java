package pages;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

}
