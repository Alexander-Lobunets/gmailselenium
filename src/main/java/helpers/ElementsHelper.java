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

    public static WebElement  isElementClickable (WebDriver webDriver, WebElement webElement,
                                                 int timeout){
        webElement = (new WebDriverWait(webDriver, timeout))
                .until(ExpectedConditions.elementToBeClickable(webElement));
        return webElement;
    }


    public static String dynamicBulderLocator(String pattern, String str){
        return pattern.replaceFirst("replace_me", str);

    }





//    public static WebElement  isElementPresence (WebDriver webDriver, WebElement webElement,
//                                                  int timeout){
//        try {
//            webElement = (new WebDriverWait(webDriver, timeout))
//                    .un(webElement.isEnabled());
//            return webElement;
//        } catch (NoSuchElementException e){
//            throw e;
//        }
//    }

//    public static WebElement isElementPresence (WebDriver webDriver, WebElement webElement,
//                                                  int timeout) {
//
//          webElement.isDisplayed()
//    }
//}

}
