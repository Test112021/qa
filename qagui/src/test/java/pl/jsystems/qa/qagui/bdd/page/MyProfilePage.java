package pl.jsystems.qa.qagui.bdd.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyProfilePage extends BasePage {

    public MyProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private static final String PROFILE_NAME_SELECTOR = "profile-gravatar__user-display-name";

    @FindBy(className = PROFILE_NAME_SELECTOR)
    public WebElement profileName;
//    public WebElement profileName = driver.findElement(By.className("profile-gravatar__user-display-name"));

    @FindBy(css = "button[title=\"Log out of WordPress.com\"]")
    public WebElement logOutButton;
//    public WebElement logOutButton = driver.findElement(By.cssSelector("button[title=\"Log out of WordPress.com\"]"));

    @FindBy(css = "a[href=\"/me/notifications\"]")
    public WebElement notificationLabel;

    public String getProfileName() {
        visibilityOfElementLocated(By.className(PROFILE_NAME_SELECTOR), 5);
        return profileName.getText();
    }

    public void clickLogOutButton() {
        click(logOutButton, 3);
    }
}
