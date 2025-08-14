package testScripts.TDLSchool;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

public class NavigationTest {
    private WebDriver driver;

    @AfterMethod
    private void tearDown() {
        this.driver.close();
        this.driver.quit();
    }

    @BeforeMethod
    private void setUpBrowser() {
        this.driver = setUpWebDriverManage("edge");
        //this.driver.manage().window().maximize();
        this.driver.get("https://tdlschool.com/");
    }

    @Test
    public void openTDLSchoolPage() throws InterruptedException {

//        WebDriver driver = setUpWebDriverManually("chrome");
//        System.setProperty("wdm.edgeDriverUrl", "https://msedgedriver.microsoft.com/");
        Thread.sleep(2000);

        WebElement logo = driver.findElement(By.className("navigation__logo"));
        WebElement buttonOurCourses = driver.findElement(By.linkText("Our courses"));
        Assert.assertTrue(logo.isDisplayed(), "Logo is not displayed on page");
        Assert.assertTrue(buttonOurCourses.isDisplayed(), "Button is not displayed on page");
        WebElement careerPathsLink = driver.findElement(By.linkText("Career Paths"));
        careerPathsLink.click();
        WebElement careerPathTitle = driver.findElement(By.tagName("h1"));
        Assert.assertEquals(careerPathTitle.getText(),
                "Not sure where to start?",
                "Title is not as expected");
        System.out.println("Title:" + careerPathTitle.getText());
        WebElement emailInputField = driver.findElement(By.name("email"));
        Assert.assertTrue(emailInputField.isDisplayed(), "Email input is not Displayed");
        emailInputField.sendKeys("test");
        Thread.sleep(1000);

        Thread.sleep(2000);
        System.out.println("Title:" + driver.getTitle());
        System.out.println("URL:" + driver.getCurrentUrl());
    }

    @Test()
    public void checkUpcomingCoursesCount() throws InterruptedException {
        System.out.println("Check if TDL School logo is Displayed");
        boolean isLogoDisplayed = driver.findElement(By.className("navigation__logo")).isDisplayed();
        Thread.sleep(5000);
        Assert.assertTrue(isLogoDisplayed, "TDL School logo can not be found");
        System.out.println("Check that 4 courses are displayed in upcoming section");
        int countOfVisibleCourses = driver.findElements(
                By.cssSelector("a.course-suggestions__course-card--active"))
                .size();
        Assert.assertEquals(countOfVisibleCourses, 4,
                "Visible courses in Upcoming section is "+countOfVisibleCourses+
                        " but expected was 9.");
    }

    private WebDriver setUpWebDriverManage(String browser) {
        WebDriver driver = null;
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
        }
        return driver;
    }

    private WebDriver setUpWebDriverManually(String browser) {
        WebDriver driver = null;
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src" + File.separator +
                        "test" + File.separator +
                        "resources" + File.separator +
                        "drivers" + File.separator +
                        "chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "src" + File.separator +
                        "test" + File.separator +
                        "resources" + File.separator +
                        "drivers" + File.separator +
                        "geckodriver.exe");
                driver = new FirefoxDriver();

                break;
            case "edge":
                System.setProperty("webdriver.msedge.driver", "src" + File.separator +
                        "test" + File.separator +
                        "resources" + File.separator +
                        "drivers" + File.separator +
                        "meedgedriver.exe");
                driver = new EdgeDriver();
                break;
        }
        return driver;
    }
}
