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
import utils.ExtentReportHelper;

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
        this.reports = ExtentReportHelper.createReport("TDLSchoolTest");
    }

    @BeforeMethod
    private void setUpBrowser(Method method) {
        String testName = method.getAnnotation(Test.class).testName();
        String description = method.getAnnotation(Test.class).description();

        test = ExtentReportHelper.createTest(reports, testName, description);
        this.driver = setUpWebDriverManage("edge");
        //this.driver.manage().window().maximize();
        this.driver.get("https://tdlschool.com/");
    }

    @AfterMethod
    private void tearDown(ITestResult testResult) {
        ExtentReportHelper.checkIfTestDidFail(testResult, test, driver);

        this.driver.close();
        this.driver.quit(); // No need to run quit() if we are using FireFox
    }

    @AfterClass
    private void createReport() {
        ExtentReportHelper.generateReport(this.reports);
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
        ExtentReportHelper.addScreenshotToReport(Status.INFO, test, driver);
    }

    @Test(testName = "Upcoming Courses check",
            description = "Check that correct amount of courses is shown in landing page")
    public void checkUpcomingCoursesCount() {
        HomePage homePage = new HomePage(driver, test);

        homePage.verifyTDLSchoolLogoIsDisplayed();
        homePage.checkUpcomingLectureCount(4);
        ExtentReportHelper.addScreenshotToReport(Status.INFO, test, driver);
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
