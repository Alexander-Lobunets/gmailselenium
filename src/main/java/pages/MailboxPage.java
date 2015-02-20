package pages;


import helpers.ElementsHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by alobunets on 2/20/2015.
 */
public class MailboxPage {

    private WebDriver webDriver;
    private static final Logger logger = LogManager.getLogger(MailboxPage.class);

    @FindBy (xpath = "//div[@role='button' and contains(text(), 'НАПИСАТЬ')]")
    WebElement buttonWrite;

    @FindBy(xpath = "//div[@role='dialog']")
    WebElement newMailDiv;

    @FindBy(xpath = "//textarea[@name='to']")
    WebElement fieldTo;

    @FindBy(xpath = "//input[@name='subjectbox']")
    WebElement fieldSubject;

    @FindBy(xpath = "//div[@contenteditable='true' and @aria-label='Тело письма']")
    WebElement fieldMailBodyText;

    @FindBy(xpath = "//div[@role='button' and contains(text(), 'Отправить')]")
    WebElement buttonSend;

    @FindBy(xpath = "//div[contains(text(), 'Письмо отправлено')]")
    WebElement msgEmailSent;

//    @FindBy(xpath = '//div[]')
//    WebElement


    public MailboxPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void clickButtonWrite(){
        ElementsHelper.isElementPresence(webDriver, buttonWrite)
                .click();
    }

    public void fillMailFieldsAndSend(){
        //wait for new mail div is clickable
        (new WebDriverWait(webDriver, 10))
                .until(ExpectedConditions.elementToBeClickable(newMailDiv));

        ElementsHelper.typeStringIntoField(fieldTo, "lobunets@gmail.com");
        ElementsHelper.typeStringIntoField(fieldSubject, "test subject");
        ElementsHelper.typeStringIntoField(fieldMailBodyText, "wedwedewd@DEWDW.COM");
        buttonSend.click();
    }

    public void checkNewMail(){
//        webDriver.navigate().refresh();
        ElementsHelper.isElementPresence(webDriver, msgEmailSent);
    }



}
