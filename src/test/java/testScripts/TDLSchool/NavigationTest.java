package testScripts.TDLSchool;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.Test;

import java.io.File;

public class NavigationTest {

    @Test
    public void openTDLSchoolPage () {

//        WebDriver driver = setUpWebDriverManually("chrome");
//        System.setProperty("wdm.edgeDriverUrl", "https://msedgedriver.microsoft.com/");
        WebDriver driver = setUpWebDriverManage("edge");
        driver.manage().window().maximize();
        driver.get("https://tdlschool.com/");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Title:"+ driver.getTitle());
        System.out.println("URL:"+ driver.getCurrentUrl());
        driver.close();
        //driver.quit();
    }
    private WebDriver setUpWebDriverManage (String browser) {
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
    private WebDriver setUpWebDriverManually (String browser) {
        WebDriver driver = null;
        switch (browser){
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src"+ File.separator+
                        "test" + File.separator +
                        "resources" + File.separator +
                        "drivers" + File.separator +
                        "chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "src"+ File.separator+
                        "test" + File.separator +
                        "resources" + File.separator +
                        "drivers" + File.separator +
                        "geckodriver.exe");
                driver = new FirefoxDriver();

                break;
            case "edge":
                System.setProperty("webdriver.msedge.driver", "src"+ File.separator+
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
