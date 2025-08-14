package testScripts.TDLSchool;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.Method;

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

    @Test (testName = "Navigate to Career Paths page",
            description = "We are opening TDL School page and entering text in footer")
    public void openTDLSchoolPage(Method method) throws InterruptedException {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(
                System.getProperty("user.dir") +
                        File.separator + "reports" +
                        File.separator + "TestReport.html");
        ExtentReports reports = new ExtentReports();
        reports.attachReporter(sparkReporter);

        String testName = method.getAnnotation(Test.class).testName();
        String description = method.getAnnotation(Test.class).description();

        ExtentTest test = reports.createTest(testName, description);
        Thread.sleep(2000);

        test.log(Status.INFO, "Check that logo is Displayed");
        WebElement logo = driver.findElement(By.className("navigation__logo"));
        Assert.assertTrue(logo.isDisplayed(), "Logo is not displayed on page");
        test.log(Status.PASS, "Logo is displayed");

        test.log(Status.INFO, "Click on Career Paths button");
        WebElement careerPathsLink = driver.findElement(By.linkText("Career Paths"));
        careerPathsLink.click();
        test.log(Status.PASS, " Click action on Career path did work");

        test.log(Status.INFO, "Title Not sure where to start? is displayed");
        WebElement careerPathTitle = driver.findElement(By.tagName("h1"));
        Assert.assertEquals(careerPathTitle.getText(),
                "Not sure where to start?",
                "Title is not as expected");
        test.log(Status.PASS, "Title "+ careerPathTitle.getText()+" was displayed correctly");

        test.log(Status.INFO, "Enter email in email field");
        WebElement emailInputField = driver.findElement(By.name("email"));
        Assert.assertTrue(emailInputField.isDisplayed(), "Email input is not Displayed");
        emailInputField.sendKeys("test");
        test.log(Status.PASS, "Text entered in email field");

        Thread.sleep(1000);

        Thread.sleep(2000);
        test.log(Status.INFO, "Title:" + driver.getTitle());
        test.log(Status.INFO, "URL:" + driver.getCurrentUrl());

        addScreenshotToReport(Status.PASS, test);

        reports.flush();
    }

    private void addScreenshotToReport (Status status, ExtentTest test){
        // Capture the screenshot as a Base64 string and prepend with the data URI scheme for a PNG image
        String base64ScreenShot = "data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

        // Log the screenshot to the report with the specified status (PASS, FAIL, etc.)
        test.log(status, MediaEntityBuilder.createScreenCaptureFromBase64String(base64ScreenShot).build());
    }

    @Test(testName = "Upcoming Courses check",
    description = "Check that correct amount of courses is shown in landing page")
    public void checkUpcomingCoursesCount(Method method) throws InterruptedException {
        ExtentSparkReporter templateReport = new ExtentSparkReporter(System.getProperty("user.dir")+
                File.separator +"reports"+
                File.separator+ "TestReport2.html");

        ExtentReports reports = new ExtentReports();
        reports.attachReporter(templateReport);

        ExtentTest test = reports.createTest(method.getAnnotation(Test.class).testName(),
                method.getAnnotation(Test.class).description());

        test.log(Status.INFO, "Check if TDL School logo is Displayed");
        boolean isLogoDisplayed = driver.findElement(By.className("navigation__logo")).isDisplayed();
        Thread.sleep(5000);
        Assert.assertTrue(isLogoDisplayed, "TDL School logo can not be found");
        test.log(Status.PASS, "TDL School logo is Displayed");

        test.log(Status.INFO, "Check that 4 courses are displayed in upcoming section");
        int countOfVisibleCourses = driver.findElements(
                By.cssSelector("a.course-suggestions__course-card--active"))
                .size();
        Assert.assertEquals(countOfVisibleCourses, 4,
                "Visible courses in Upcoming section is "+countOfVisibleCourses+
                        " but expected was 9.");
        test.log(Status.PASS, "Visible count of upcoming courses is as expected");

        addScreenshotToReport(Status.PASS, test);

        reports.flush();
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
