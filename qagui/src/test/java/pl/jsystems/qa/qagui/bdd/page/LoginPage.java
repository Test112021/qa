package pl.jsystems.qa.qagui.bdd.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private static final String USER_NAME_OR_EMAIL = "usernameOrEmail";
    private static final String PASSWORD = "password";

    @FindBy(id = USER_NAME_OR_EMAIL)
    public WebElement userLogInput;
//    public WebElement userLogInput = driver.findElement(By.id("usernameOrEmail"));

    @FindBy(className = "login__form-action")
    public WebElement userContinueButton;
//    public WebElement userContinueButton =  driver.findElement(By.className("login__form-action"));

    @FindBy(id = PASSWORD)
    public WebElement passLogInput;
//    public WebElement passLogInput = driver.findElement(By.id("password"));

    @FindBy(className = "login__form-action")
    public WebElement passConfirmButton;
//    public WebElement passConfirmButton = driver.findElement(By.className("login__form-action"));

    public void enterUser(String userName) {
        visibilityOfElementLocated(By.id(USER_NAME_OR_EMAIL), 3);
        this.userLogInput.click();
        this.userLogInput.clear();
        this.userLogInput.sendKeys(userName);
    }

    public void enterPass(String pass) {
        visibilityOfElementLocated(By.id(PASSWORD), 3);
        this.passLogInput.click();
        this.passLogInput.clear();
        this.passLogInput.sendKeys(pass);
    }

}
