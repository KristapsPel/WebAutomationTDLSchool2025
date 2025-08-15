package testScripts.TDLSchool;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.CareerPathsPage;
import pages.FooterClass;
import pages.HomePage;
import utils.ExtentReportHelper;
import utils.WebDriverHelper;
import java.lang.reflect.Method;


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
        this.driver = WebDriverHelper.setUpWebDriverManage("chrome");
        WebDriverHelper.openBrowser("https://tdlschool.com/", driver);
    }

    @AfterMethod
    private void tearDown(ITestResult testResult) {
        ExtentReportHelper.checkIfTestDidFail(testResult, test, driver);
        WebDriverHelper.closeBrowser(driver);
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
}
