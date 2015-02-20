package pages;

import helpers.ElementsHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by alobunets on 2/18/2015.
 */
public class LoginPage {

    WebDriver webDriver;
    private static final Logger logger = LogManager.getLogger(LoginPage.class);

    @FindBy (id = "Email")
    WebElement loginField;

    @FindBy(id = "Passwd")
    WebElement passwdField;

    @FindBy(id = "signIn")
    WebElement signInBtn;

    public LoginPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void login(String username, String password){
        ElementsHelper.typeStringIntoField(loginField, username);
        ElementsHelper.typeStringIntoField(passwdField, password);
    }

    public void pressLoginBtn(){
        signInBtn.click();
    }

}
