package helpers;

import org.openqa.selenium.WebElement;

/**
 * Created by alobunets on 2/18/2015.
 */
public class HelperFields {

    public static void typeStringIntoField(WebElement webElement,
                        String text){
        webElement.clear();
        webElement.sendKeys(text);


    }

}
