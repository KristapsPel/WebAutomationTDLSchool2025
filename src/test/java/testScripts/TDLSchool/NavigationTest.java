package testScripts.TDLSchool;

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
        System.setProperty("webdriver.chrome.driver", "src"+ File.separator+
                "test" + File.separator +
                "resources" + File.separator +
                "drivers" + File.separator +
                "chromedriver.exe");

        WebDriver driver = new ChromeDriver();
//        WebDriver firefox = new FirefoxDriver();
//        WebDriver edge = new EdgeDriver();
//        WebDriver safari = new SafariDriver();

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
        driver.quit();
    }
}
