import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.CommonHelpers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import pages.LoginPage;
import pages.MailboxPage;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.StringContains.containsString;
import static org.hamcrest.junit.MatcherAssert.assertThat;


/**
 * Created by alobunets on 2/18/2015.
 */
public class StepsDefinition {
    WebDriver webDriver;
    LoginPage loginPage;
    MailboxPage mailboxPage;
    private String url = "https:\\gmail.com";
    private String folderPath = "d:\\";


    @Given("^Login to Gmail with <login> and <password>$")
    public void login_to_Gmail_with_login_and_password(DataTable dataTable) throws Throwable {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("intl.accept_languages", "en-us, en");
        webDriver = new FirefoxDriver(profile);

        webDriver.navigate().to(url);
        assertThat(webDriver.getTitle(), equalTo("Gmail"));
        loginPage = new LoginPage(webDriver);
        List<List<String>> credentialsList = dataTable.raw();
        loginPage.login(credentialsList.get(1).get(0), credentialsList.get(1).get(1));
        loginPage.pressLoginBtn();
    }

    @When("^I create mail to \"(.*?)\" with subject \"(.*?)\" and body \"(.*?)\" and send it$")
    public void i_create_mail_to_with_subject_and_body_and_send_it(String to, String subject, String body) throws Throwable {
        mailboxPage = new MailboxPage(webDriver);
        mailboxPage.clickButtonWrite();
        mailboxPage.fillMailFieldsAndSend(to, subject, body);
        assertThat(webDriver.getCurrentUrl(), containsString("https://mail.google.com"));
    }

    @Then("^I see received mail from \"(.*?)\" with subject \"(.*?)\" and body \"(.*?)\" and make screenshot$")
    public void i_see_received_mail_from_with_subject_and_body_and_make_screenshot(String from, String subject, String body) throws Throwable {

        mailboxPage.refreshInbox();
        assertThat(true, is(mailboxPage.checkMail(from, subject, body)));
        CommonHelpers.getScreenshot(webDriver, folderPath + "gmail.png");
    }

    @Then("^I close the browser$")
    public void i_close_the_browser() throws Throwable {
        mailboxPage.logout();
        webDriver.close();
    }

}
