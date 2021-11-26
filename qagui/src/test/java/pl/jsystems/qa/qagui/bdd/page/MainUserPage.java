package pl.jsystems.qa.qagui.bdd.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainUserPage extends BasePage {

    public MainUserPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

//    private final static String AVATAR_SELECTOR = "a[data-tip-target=\"me\"]";
    private final static String AVATAR_SELECTOR = "gravatar";

    @FindBy(className = AVATAR_SELECTOR)
    public WebElement avatar;
//    public WebElement avatar = driver.findElement(By.cssSelector(".masterbar__item.masterbar__item-me"));

    public void clickAvatar() {
//        visibilityOfElementLocated(By.cssSelector(AVATAR_SELECTOR), 3);
        click(this.avatar, 5);
    }

}
