package testScripts.TDLSchool;
import com.aventstack.extentreports.Status;
import org.testng.annotations.*;
import pages.CareerPathsPage;
import pages.FooterClass;
import pages.HomePage;
import testScripts.BaseTest;
import utils.ExtentReportHelper;



public class NavigationTest extends BaseTest {
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
