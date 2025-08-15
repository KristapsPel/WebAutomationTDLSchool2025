package testScripts.TDLSchool;

import org.testng.annotations.Test;
import pages.TDLSchoolPages.HomePage;
import testScripts.BaseTest;

public class test2 extends BaseTest {
    @Test(testName = "Test2", description = "description")
    public void navigate(){
        HomePage homePage = new HomePage(driver, test);

        homePage.verifyTDLSchoolLogoIsDisplayed();
        homePage.clickOnCareerPathButton();
    }
}
