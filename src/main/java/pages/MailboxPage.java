package pages;


import helpers.ElementsHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

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

    @FindBy(xpath= "//a[contains(.,'Входящие')]")
    WebElement linkInbox;

//    @FindBys({@FindBy(xpath = "//div[contains(text(),'Непрочитанное письмо')]")})
    @FindBys(
            {@FindBy(xpath = "//tr[contains(.,'Непрочитанное письмо')]")}
            )
    List<WebElement> myMail;

    @FindBy(xpath = "//a[contains(@href,'logout')]")
    WebElement btnLogout;


    public MailboxPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void clickButtonWrite(){
        ElementsHelper.isElementClickable(webDriver, buttonWrite, 15)
                .click();
    }

    public void fillMailFieldsAndSend(String to, String subject, String body){
        //wait for new mail div is clickable
        (new WebDriverWait(webDriver, 10))
                .until(ExpectedConditions.elementToBeClickable(newMailDiv));
        ElementsHelper.typeStringIntoField(fieldTo, to);
        ElementsHelper.typeStringIntoField(fieldSubject, subject);
        ElementsHelper.typeStringIntoField(fieldMailBodyText, body);
        buttonSend.click();
    }

    public void refreshInbox() throws InterruptedException {
        ElementsHelper.isElementClickable(webDriver, msgEmailSent, 20);
        ElementsHelper.isElementClickable(webDriver, linkInbox, 20);
        webDriver.navigate().refresh();
        ElementsHelper.isElementClickable(webDriver, linkInbox, 30);
        logger.debug("length:" + myMail.size() );
    }

    public boolean checkMail(String sender, String subject, String bodyText){
        int count = 0;

        for(WebElement w: myMail){
            logger.debug(w.getText());
            if(w.findElement(By.xpath(ElementsHelper.dynamicBulderLocator("//span[@email='replace_me']", sender))) != null){
                // if body mail ...
                if(w.findElement(By.xpath(ElementsHelper.dynamicBulderLocator("//*[contains(.,'replace_me')]", bodyText))) != null){
                    // if subject ...
                    if(w.findElement(By.xpath(ElementsHelper.dynamicBulderLocator("//b[contains(.,'replace_me')]", subject))) != null){
//                        w.click();
                        count++;
                    }
                }
            }
        }
        if (count > 0){
            return true;
        } else {
            return false;
        }
    }


    public void logout(){
        btnLogout.click();
    }

}