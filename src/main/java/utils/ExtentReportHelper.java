package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.io.File;

public class ExtentReportHelper {

    public static void addScreenshotToReport(Status status, ExtentTest test, WebDriver driver) {
        // Capture the screenshot as a Base64 string and prepend with the data URI scheme for a PNG image
        String base64ScreenShot = "data:image/png;base64," +
                ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

        // Log the screenshot to the report with the specified status (PASS, FAIL, etc.)
        test.log(status, MediaEntityBuilder
                .createScreenCaptureFromBase64String(base64ScreenShot).build());
    }

    public static void generateReport(ExtentReports reports) {
        reports.flush();
    }

    public static void checkIfTestDidFail(ITestResult result, ExtentTest test,
                                          WebDriver driver) {
        if(result.getStatus()==ITestResult.FAILURE){
            test.log(Status.FAIL, result.getThrowable().getMessage());
            addScreenshotToReport(Status.FAIL, test, driver);
        }
    }

    public static ExtentTest createTest(ExtentReports reports,
                                        String name, String description) {
        return reports.createTest(name, description);
    }

    public static ExtentReports createReport (String reportName) {
        ExtentSparkReporter reportTemplate = new ExtentSparkReporter(
                System.getProperty("user.dir") +
                        File.separator + "reports" +
                        File.separator + reportName+".html");

        ExtentReports reports = new ExtentReports();
        reports.attachReporter(reportTemplate);

        return  reports;
    }
}
