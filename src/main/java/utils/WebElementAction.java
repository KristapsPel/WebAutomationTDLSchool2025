package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebElementAction {
    public static void scrollToElement(WebElement element, WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript(
                "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);" +
                        "var elementTop = arguments[0].getBoundingClientRect().top;" +
                        "window.scrollBy(0, elementTop - (viewPortHeight / 2));",
                element
        );
    }
}
