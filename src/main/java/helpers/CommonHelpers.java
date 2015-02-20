package helpers;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

/**
 * Created by alobunets on 2/20/2015.
 */
public class CommonHelpers {

    public static void getScreenshot(WebDriver webDriver, String filePath) throws IOException {
        File scrFile = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(filePath));
    }

}
