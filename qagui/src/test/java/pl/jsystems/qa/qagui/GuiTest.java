package pl.jsystems.qa.qagui;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.jsystems.qa.qagui.page.*;

import java.time.Duration;

import static com.google.common.truth.Truth.assertThat;
import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GuiTest extends GuiConfig {

    @Test
    public void lgInTest() {
        driver.get("https://wordpress.com/");

        WordpressMainPage wordpressMainPage = new WordpressMainPage(driver);

        wordpressMainPage.logIn.click();

        final WebElement usernameOrEmail = driver.findElement(By.id("usernameOrEmail"));

        usernameOrEmail.click();
        usernameOrEmail.clear();
        usernameOrEmail.sendKeys("automation112021");
        driver.findElement(By.className("login__form-action")).click();

        Wait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));

//        try {
//            sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        driver.findElement(By.id("password")).click();

        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("Test112021");
        driver.findElement(By.className("login__form-action")).click();

        final WebElement avatar = driver.findElement(By.cssSelector(".masterbar__item.masterbar__item-me"));

        assertTrue(avatar.isDisplayed());

        avatar.click();

        assertThat(driver.findElement(By.className("profile-gravatar__user-display-name")).getText()).isEqualTo("automation112021");

        driver.findElement(By.cssSelector("button[title=\"Log out of WordPress.com\"]")).click();

    }

    WordpressMainPage wordpressMainPage;
    LoginPage loginPage;
    MainUserPage mainUserPage;
    MyProfilePage myProfilePage;
    NotificationPage notificationPage;

    @Test
    public void lgIn() {
        driver.get("https://wordpress.com/");
        wordpressMainPage = new WordpressMainPage(driver);
        wordpressMainPage.clickLogIn();

        loginPage = new LoginPage(driver);
        loginPage.enterUser("automation112021");
        loginPage.userContinueButton.click();
        loginPage.enterPass("Test112021");
        loginPage.passConfirmButton.click();
        mainUserPage = new MainUserPage(driver);
        assertTrue(mainUserPage.avatar.isDisplayed());

        driver.get("https://wordpress.com/me");

        myProfilePage = new MyProfilePage(driver);

        assertThat(myProfilePage.getProfileName()).isEqualTo("automation112021");
        myProfilePage.clickLogOutButton();

    }

    @Test
    public void notification() {
        driver.get("https://wordpress.com/");
        logIn();
        mainUserPage = new MainUserPage(driver);
        assertTrue(mainUserPage.avatar.isDisplayed());

        driver.get("https://wordpress.com/me");

        myProfilePage = new MyProfilePage(driver);
        
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

        myProfilePage.clickLogOutButton();


    }

    @DisplayName("Keys short")
    @Test
    public void kaysInteraction() {
        Actions action = new Actions(driver);
        action.sendKeys(Keys.chord(Keys.CONTROL, "R")).perform();
        action.sendKeys(Keys.chord(Keys.ESCAPE)).perform();
        action.sendKeys(Keys.chord(Keys.ENTER)).perform();


    }

    @DisplayName("Simple action")
    @Test
    public void actionTest() {
        driver.navigate().to("https://wordpress.com");
        wordpressMainPage = new WordpressMainPage(driver);

        Actions action = new Actions(driver);

        action.moveToElement(wordpressMainPage.picture)
                .clickAndHold()
                .moveToElement(wordpressMainPage.startYourWebsite)
                .release();
        action.build().perform();

        action.moveToElement(wordpressMainPage.logIn)
                .click();
        action.build().perform();



    }


    private void assertFalse(boolean selected) {
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

    @DisplayName("scroll")
    @Test
    public void pageScroll() {

        String contactUrl = "http://www.testdiary.com/training/selenium/selenium-test-page/";

        driver.navigate().to(contactUrl);

        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Open page in the same window")));

        int hyperlinkYCoordinate = driver.findElement(By.linkText("Open page in the same window")).getLocation().getY();
        int hyperlinkXCoordinate = driver.findElement(By.linkText("Open page in the same window")).getLocation().getX();



        JavascriptExecutor jsexecutor = (JavascriptExecutor) driver;
        jsexecutor.executeScript("window.scrollBy(" + hyperlinkXCoordinate + "," + hyperlinkYCoordinate + ")", "");


        (new WebDriverWait(driver, 100))
                .until(ExpectedConditions.elementToBeClickable(By.linkText("Open page in the same window")));

        driver.findElement(By.linkText("Open page in the same window")).click();
    }

    @Test
    void scrollIntoView(){
        driver.get("http://manos.malihu.gr/repository/custom-scrollbar/demo/examples/complete_examples.html");
        JavascriptExecutor je = (JavascriptExecutor) driver;

        WebElement element = driver.findElement(By.xpath("//*[@id=\"mCSB_9_container\"]/ul/li[4]/img"));

        je.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    @Disabled
    @DisplayName("alert")
    @Test
    public void popupHandler() {
        driver.switchTo().alert();
        driver.findElement(By.id("userId")).sendKeys("username");
        driver.findElement(By.id("password")).sendKeys("password");
        driver.switchTo().alert().accept();
        driver.switchTo().alert().dismiss();
        driver.switchTo().defaultContent();

        String pageId = driver.getWindowHandle();
        driver.switchTo().window(pageId);

        String title = driver.getTitle();
        assertThat(title).isEqualTo("title");
    }

    @Test
    public void frameTest(){
        String contactUrl = "http://www.testdiary.com/training/selenium/selenium-test-page/";
        driver.get(contactUrl);
        new WebDriverWait(driver, 10)

                .until(ExpectedConditions.visibilityOfElementLocated(By.name("testframe")));

        WebElement testframe = driver.findElement(By.name("testframe"));

        driver.switchTo().frame(testframe);

        String expectedFrameText = driver.findElement(By.id("testpagelink")).getText();

        String actualFrameText = "My frame text";
        if(actualFrameText.equals(expectedFrameText)){
            System.out.println("Found my frame text");
        }
        else {
            System.out.println("Did not find my frame text");
        }

        driver.switchTo().parentFrame();
    }
}

