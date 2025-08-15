package testScripts;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.ExtentReportHelper;
import utils.WebDriverHelper;

import java.lang.reflect.Method;

public class BaseTest {
    protected WebDriver driver;
    protected static ExtentReports reports;
    protected ExtentTest test;

    @BeforeSuite
    public void setUpReport() {
        this.reports = ExtentReportHelper.createReport("TDLSchoolTest");
    }

    @BeforeMethod
    public void setUpBrowser(Method method) {
        String testName = method.getAnnotation(Test.class).testName();
        String description = method.getAnnotation(Test.class).description();

        System.out.println("Report:"+reports);
        test = ExtentReportHelper.createTest(reports, testName, description);
        this.driver = WebDriverHelper.setUpWebDriverManage("chrome");
        WebDriverHelper.openBrowser("https://tdlschool.com/", driver);
    }

    @AfterMethod
    public void tearDown(ITestResult testResult) {
        ExtentReportHelper.checkIfTestDidFail(testResult, test, driver);
        WebDriverHelper.closeBrowser(driver);
    }

    @AfterSuite
    public void createReport() {
        System.out.println("Create report");
        ExtentReportHelper.generateReport(this.reports);
    }
}
