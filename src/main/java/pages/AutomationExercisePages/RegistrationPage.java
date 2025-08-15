package pages.AutomationExercisePages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utils.WebElementAction;

import java.util.List;

public class RegistrationPage {
    private WebDriver driver;
    private ExtentTest test;

    @FindBy (css = "div.radio > span >input")
    private List<WebElement> titleRadioButtons;

    @FindBy (id = "name")
    private WebElement nameInput;

    @FindBy (id ="password")
    private WebElement passwordInput;

    @FindBy (css ="select#years")
    private WebElement yearsDropDown;

    @FindBy (css ="select#days")
    private WebElement daysDropDown;

    @FindBy (css ="select#months")
    private WebElement monthsDropDown;

    @FindBy (name="first_name")
    private WebElement firstNameInput;

    @FindBy (name="last_name")
    private WebElement lastNameInput;

    @FindBy (name="address1")
    private WebElement addressInput;

    @FindBy (name="state")
    private WebElement stateInput;

    @FindBy (name="mobile_number")
    private WebElement mobileNumberInput;

    @FindBy (name="zipcode")
    private WebElement zipcodeInput;

    @FindBy (name="city")
    private WebElement cityInput;

    @FindBy (id="country")
    private WebElement countryDropBox;

    @FindBy (css = "[data-qa=create-account]")
    private WebElement createButton;

    public RegistrationPage(WebDriver driver, ExtentTest extentTest) {
        this.driver = driver;
        this.test = extentTest;
        PageFactory.initElements(driver, this);
    }

    public void choseTitle (String tile) {
        boolean isTitleFound = false;
        for (WebElement oneElement: titleRadioButtons){
            if(oneElement.getAttribute("value").equals(tile)){
                oneElement.click();
                test.log(Status.PASS, "Click on title: "+tile);
                isTitleFound = true;
            }
        }
        Assert.assertTrue(isTitleFound, "Can not find tile:"+tile);
    }

    public void enterName (String name) {
        test.log(Status.INFO, "Enter name in name field");
        nameInput.clear();
        nameInput.sendKeys(name);
        test.log(Status.PASS, "Enter "+name+" in name field");
    }

    public void enterPassword (String password) {
        test.log(Status.INFO, "Enter password in name field");
        passwordInput.clear();
        passwordInput.sendKeys(password);
        test.log(Status.PASS, "Enter "+password+" in password field");
    }

    public void setDateOfBirth (String days, String months, String years) {
        Select dayDropdown = new Select(daysDropDown);
        Select monthDropdown = new Select(monthsDropDown);
        Select yearDropdown = new Select(yearsDropDown);

        test.log(Status.INFO, "Select day:");
        dayDropdown.selectByVisibleText(days);
        test.log(Status.PASS, "Set "+days+" in Day dropdown");

        test.log(Status.INFO, "Select month:");
        monthDropdown.selectByVisibleText(months);
        test.log(Status.PASS, "Set "+months+" in Month dropdown");

        test.log(Status.INFO, "Select year:");
        yearDropdown.selectByVisibleText(years);
        test.log(Status.PASS, "Set "+years+" in Years dropdown");
    }

    public void enterFirstName (String firstName) {
        test.log(Status.INFO, "Enter first name in first name field");
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
        test.log(Status.PASS, "Enter "+firstName+" in first name field");
    }

    public void enterLastName (String lastName) {
        test.log(Status.INFO, "Enter last name in last name field");
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);
        test.log(Status.PASS, "Enter "+lastName+" in Last name field");
    }

    public void enterAddress(String address) {
        test.log(Status.INFO, "Enter address in address field");
        addressInput.clear();
        addressInput.sendKeys(address);
        test.log(Status.PASS, "Enter "+address+" in address field");
    }

    public void setCountry (String country){
        Select countrySelect= new Select(countryDropBox);
        countrySelect.selectByVisibleText(country);
        test.log(Status.PASS, "Select "+ country+" for Country Dropbox");
    }

    public void enterState (String state) {
        test.log(Status.INFO, "Enter State in state field");
        stateInput.clear();
        stateInput.sendKeys(state);
        test.log(Status.PASS, "Enter "+state+" in state field");
    }

    public void enterCity (String city) {
        test.log(Status.INFO, "Enter City in city field");
        cityInput.clear();
        cityInput.sendKeys(city);
        test.log(Status.PASS, "Enter "+city+" in city field");
    }

    public void enterZipCode (String zipCode) {
        test.log(Status.INFO, "Enter Zip Code in zipCode field");
        zipcodeInput.clear();
        zipcodeInput.sendKeys(zipCode);
        test.log(Status.PASS, "Enter "+zipCode+" in Zip Code field");
    }

    public void enterMobileNumber(String phoneNumber) {
        test.log(Status.INFO, "Enter mobile number in number field");
        mobileNumberInput.clear();
        mobileNumberInput.sendKeys(phoneNumber);
        test.log(Status.PASS, "Enter "+phoneNumber+" in number field");
    }

    public void clickCreateAccountButton () {
        test.log(Status.INFO, "Click on Create account button");
        createButton.click();
        test.log(Status.PASS, "Click action completed on Create Account");
    }

    public void fillRegistrationForm(String title, String name, String password,
                                     String day, String month, String year,
                                     String firstName, String lastName, String address,
                                     String country, String state, String city,
                                     String zipCode, String mobilePhone){
        choseTitle(title);
        enterName(name);
        enterPassword(password);
        setDateOfBirth(day,month,year);
        enterFirstName(firstName);
        enterLastName(lastName);
        enterAddress(address);
        setCountry(country);
        enterState(state);
        enterCity(city);
        enterZipCode(zipCode);
        enterMobileNumber(mobilePhone);
        WebElementAction.scrollToElement(createButton, driver);
        clickCreateAccountButton();

    }




}
