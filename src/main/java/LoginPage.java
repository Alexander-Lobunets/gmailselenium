import helpers.HelperFields;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by alobunets on 2/18/2015.
 */
public class LoginPage {
    WebDriver webDriver;

    @FindBy (id = "Email")
    WebElement loginField;

    @FindBy(id = "Passwd")
    WebElement passwdField;

    @FindBy(id = "signIn")
    WebElement signInBtn;

    LoginPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    void login(String username, String password){
        HelperFields.typeStringIntoField(loginField, username);
        HelperFields.typeStringIntoField(passwdField, password);


    }

    void pressLoginBtn(){
        signInBtn.click();
    }

}
