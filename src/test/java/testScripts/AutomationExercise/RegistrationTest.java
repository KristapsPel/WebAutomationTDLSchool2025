package testScripts.AutomationExercise;

import org.testng.annotations.Test;
import pages.AutomationExercisePages.AccountCreatedPage;
import pages.AutomationExercisePages.HomePage;
import pages.AutomationExercisePages.LoginSignupPage;
import pages.AutomationExercisePages.RegistrationPage;
import testScripts.BaseTest;


public class RegistrationTest extends BaseTest {
    @Test(testName = "Registration test",
    description = "Fill out every field in registration form")
    public void registrationTest (){
        HomePage homePage = new HomePage(driver, test);
        LoginSignupPage loginSignupPage = new LoginSignupPage(driver, test);
        RegistrationPage registrationPage = new RegistrationPage(driver, test);
        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver, test);

        homePage.clickConsentButton();
        homePage.clickOnSignUpLoginButton();

        loginSignupPage.fillOutSignUpForm("asdsads@sdsads.con", "sdfsdfsfsd");

        registrationPage.fillRegistrationForm("Mr", "Kristaps", "Password123",
                "7", "July", "1999", "Kristaps", "Pelcbergs",
                "TDLShool address 123", "Canada", "Kvebeka", "Toronto",
                "CA23432", "234234234234");

        accountCreatedPage.confirmAccountCreation();


    }
}
