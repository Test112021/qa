package pl.jsystems.qa.qagui.bdd.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pl.jsystems.qa.qagui.classic.page.*;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.jsystems.qa.qagui.GuiConfig.*;

public class LoginSteps {

    private WebDriver driver;

    public LoginSteps(ConfigStep configStep) {
        this.driver = configStep.setUpWebDriver();
    }

    WordpressMainPage wordpressMainPage;
    LoginPage loginPage;
    MainUserPage mainUserPage;
    MyProfilePage myProfilePage;
    NotificationPage notificationPage;

    @Given("User starts on main page")
    public void userStartsOnMainPage() {
        driver.get(BASE_URL);
    }

    @When("user logs to the user panel")
    public void userLogsToTheUserPanel() {
        logIn();
        mainUserPage = new MainUserPage(driver);
        assertTrue(mainUserPage.avatar.isDisplayed());
        driver.get(BASE_URL + "/me");
        myProfilePage = new MyProfilePage(driver);
    }

    @Then("User can modify user profile")
    public void userCanModifyUserProfile() {
        myProfilePage.notificationLabel.click();
        notificationPage = new NotificationPage(driver);

        assertTrue(notificationPage.commentNotificationCheckBox.isSelected());
        assertFalse(notificationPage.aveSettingsButton.isEnabled());
        notificationPage.commentNotificationCheckBox.click();

        assertFalse(notificationPage.commentNotificationCheckBox.isSelected());
        assertTrue(notificationPage.aveSettingsButton.isEnabled());

        notificationPage.commentNotificationCheckBox.click();
        assertTrue(notificationPage.commentNotificationCheckBox.isSelected());
        assertFalse(notificationPage.aveSettingsButton.isEnabled());

    }

    @And("User logs out")
    public void userLogsOut() {
        myProfilePage.clickLogOutButton();
    }

    private void logIn() {
        wordpressMainPage = new WordpressMainPage(driver);
        wordpressMainPage.clickLogIn();

        loginPage = new LoginPage(driver);
        loginPage.enterUser("automation112021");
        loginPage.userContinueButton.click();
        loginPage.enterPass("Test112021");
        loginPage.passConfirmButton.click();
    }

}
