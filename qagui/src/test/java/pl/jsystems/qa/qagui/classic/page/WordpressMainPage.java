package pl.jsystems.qa.qagui.classic.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WordpressMainPage extends BasePage {

    public WordpressMainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Log In")
    public WebElement logIn;
//    public WebElement logIn = driver.findElement(By.linkText("Log In"));

    @FindBy(css = "#lpc-picture\\.3 > div")
    public WebElement picture;

    @FindBy(id = "lpc-button")
    public WebElement startYourWebsite;

    public void clickLogIn() {
        click(logIn, 5);
    }

}
