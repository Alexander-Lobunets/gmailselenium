import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.CommonHelpers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.LoginPage;
import pages.MailboxPage;

import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.StringContains.containsString;
import static org.hamcrest.junit.MatcherAssert.assertThat;


/**
 * Created by alobunets on 2/18/2015.
 */
public class StepsDefinition {
    WebDriver webDriver;
    private String url = "https:\\gmail.com";
//    private String login = "adfdesrfc";
//    private String password = "aewdwed";
    LoginPage loginPage;
    MailboxPage mailboxPage;
    private String folderPath = "d:\\";


    @Given("^I open gmail\\.com page$")
    public void i_open_gmail_com_page() throws Throwable {
        webDriver = new FirefoxDriver();
        webDriver.navigate().to(url);
        assertThat(webDriver.getTitle(), equalTo("Gmail"));
        loginPage = new LoginPage(webDriver);
    }

    @When("^I type <login> and <password>$")
    public void i_type_and(DataTable dataTable) throws Throwable {
        List<List<String>> credentialsList = dataTable.raw();
        loginPage.login(credentialsList.get(1).get(0), credentialsList.get(1).get(1));
    }

    @When("^I press login button$")
    public void i_press_login_button() throws Throwable {
        loginPage.pressLoginBtn();
    }

    @Then("^I see gmail mailbox$")
    public void i_see_gmail_mailbox() throws Throwable {
        // wait for any element
        mailboxPage = new MailboxPage(webDriver);
        mailboxPage.clickButtonWrite();
        mailboxPage.fillMailFieldsAndSend();
        assertThat(webDriver.getCurrentUrl(), containsString("https://mail.google.com"));
        mailboxPage.checkNewMail();
        CommonHelpers.getScreenshot(webDriver, folderPath + "gmail.png");

    }

    @Then("^I close browser$")
    public void i_close_browser() throws Throwable {

    }


    public WebDriver getWebdriver(){
        return webDriver;
    }

}
