package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by alobunets on 2/18/2015.
 */
public class ElementsHelper {

    public static void typeStringIntoField(WebElement webElement,
                        String text){
        webElement.clear();
        webElement.sendKeys(text);
    }

    public static WebElement  isElementPresence (WebDriver webDriver, WebElement webElement){
        webElement = (new WebDriverWait(webDriver, 10))
                .until(ExpectedConditions.elementToBeClickable(webElement));
        return webElement;
    }




}
