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
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.CareerPathsPage;
import pages.FooterClass;
import pages.HomePage;

import java.io.File;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class NavigationTest {
    private WebDriver driver;
    private ExtentReports reports;
    private ExtentTest test;

    @BeforeClass
    private void setUpReport() {
        ExtentSparkReporter reportTemplate = new ExtentSparkReporter(
                System.getProperty("user.dir") +
                        File.separator + "reports" +
                        File.separator + "FULL_TestReport.html");

        this.reports = new ExtentReports();
        this.reports.attachReporter(reportTemplate);
    }

    @BeforeMethod
    private void setUpBrowser(Method method) {
        String testName = method.getAnnotation(Test.class).testName();
        String description = method.getAnnotation(Test.class).description();

        test = reports.createTest(testName, description);
        this.driver = setUpWebDriverManage("edge");
        //this.driver.manage().window().maximize();
        this.driver.get("https://tdlschool.com/");
    }

    @AfterMethod
    private void tearDown(ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, testResult.getThrowable().getMessage());
            addScreenshotToReport(Status.FAIL, test);
        }

        this.driver.close();
        this.driver.quit(); // No need to run quit() if we are using FireFox
    }

    @AfterClass
    private void createReport() {
        this.reports.flush();
    }

    @Test(testName = "Navigate to Career Paths page",
            description = "We are opening TDL School page and entering text in footer")
    public void openTDLSchoolPage() {
        HomePage homePage = new HomePage(driver, test);
        CareerPathsPage careerPathsPage = new CareerPathsPage(driver, test);
        FooterClass footerClass = new FooterClass(driver, test);

        homePage.verifyTDLSchoolLogoIsDisplayed();
        homePage.clickOnCareerPathButton();
        careerPathsPage.checkPageTitle("Not sure where to start?");
        footerClass.enterEmail("test");

        addScreenshotToReport(Status.PASS, test);
    }

    private void addScreenshotToReport(Status status, ExtentTest test) {
        // Capture the screenshot as a Base64 string and prepend with the data URI scheme for a PNG image
        String base64ScreenShot = "data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

        // Log the screenshot to the report with the specified status (PASS, FAIL, etc.)
        test.log(status, MediaEntityBuilder.createScreenCaptureFromBase64String(base64ScreenShot).build());
    }

    @Test(testName = "Upcoming Courses check",
            description = "Check that correct amount of courses is shown in landing page")
    public void checkUpcomingCoursesCount() throws InterruptedException {
        HomePage homePage = new HomePage(driver, test);

        homePage.verifyTDLSchoolLogoIsDisplayed();

        test.log(Status.INFO, "Check that 4 courses are displayed in upcoming section");
        int countOfVisibleCourses = driver.findElements(
                        By.cssSelector("a.course-suggestions__course-card--active"))
                .size();
        Assert.assertEquals(countOfVisibleCourses, 5,
                "Visible courses in Upcoming section is " + countOfVisibleCourses +
                        " but expected was 4.");
        test.log(Status.PASS, "Visible count of upcoming courses is as expected");

        addScreenshotToReport(Status.PASS, test);
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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
