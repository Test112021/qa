package pl.jsystems.qa.qagui.bdd.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jsystems.qa.qagui.GuiConfig.BASE_URL;

public class ParamCucumberSteps {

    private WebDriver driver;

    public ParamCucumberSteps(ConfigStep configStep) {
        this.driver = configStep.setUpWebDriver();
    }

    @Given("website has got title")
    public void websiteHasGotTitle(Map<String, String> dataMap) {
        String website = dataMap.get("website");
        String title = dataMap.get("title");
        driver.get(website);
        assertEquals(driver.getTitle(), title);
    }

    @Given("Name of the website {string}")
    public void nameOfTheWebsite(String website) {
        driver.get(website);
    }

    @Then("Title is {string}")
    public void titleIs(String title) {
        assertEquals(driver.getTitle(), title);
    }

    @Given("Name of the website is {}")
    public void nameOfTheWebsiteIsWebsite(String website) {
        driver.get(website);
    }

    @Then("Title of this website is {}")
    public void titleOfThisWebsiteIsTitle(String title) {
        assertEquals(driver.getTitle(), title);
    }
}
