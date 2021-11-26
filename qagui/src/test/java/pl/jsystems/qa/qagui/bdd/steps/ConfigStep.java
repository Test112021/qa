package pl.jsystems.qa.qagui.bdd.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import pl.jsystems.qa.qagui.GuiConfig;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;

public class ConfigStep {

    protected WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.edgedriver().setup();
        WebDriverManager.firefoxdriver().setup();
    }

    public WebDriver setUpWebDriver() {
        setUpMachine();
        confDriver();
        return driver;
    }

    private void setUpMachine() {
        if (GuiConfig.MACHINE.equals("local")) {
            setUpLocalConfiguration();
        } else {
            setUpRemoteConfiguration();
        }
    }

    private WebDriver setUpRemoteConfiguration() {
        setUpRemoteDriver();
        return driver;
    }

    private void setUpRemoteDriver() {
        try {
            driver = new RemoteWebDriver(new URL(GuiConfig.REMOTE_URL),
                    new DesiredCapabilities(GuiConfig.BROWSER, "", Platform.LINUX));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void setUpLocalConfiguration() {
        driver = setWebDriver();
    }

    private void confDriver() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    private WebDriver setWebDriver() {
        switch (GuiConfig.BROWSER) {
            case "chrome":
                setUpChromeEnv();
                return new ChromeDriver();
            case "firefox":
                setUpFirefoxEnv();
                return new FirefoxDriver();
            case "edge":
                setUpEdgeEnv();
                return new EdgeDriver();
        }
        return new FirefoxDriver();
    }


    private void setUpChromeEnv() {
        setUpEnv("webdriver.chrome.driver", "drivers/chromedriver.exe");
    }

    private void setUpFirefoxEnv() {
        setUpEnv("webdriver.gecko.driver", "drivers/geckodriver.exe");
    }

    private void setUpEdgeEnv() {
        setUpEnv("webdriver.edge.driver", "driver/msedgedriver.exe");
    }

    private void setUpEnv(String envName, String path) {
        try {
            System.setProperty(envName, Paths.get(getClass().getClassLoader()
                    .getResource(path).toURI()).toFile().getAbsolutePath());

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        System.out.println("=========================== @After Cucumber Test  =======================================");
        String status;
        if(!scenario.isFailed()) {
            status = "( ͡° ͜ʖ ͡°)";
//            status = "++++++++++";
            scenario.log("Scenario passed");
        } else {
            status = "(✖╭╮✖)";
//            status = "-------------";
            scenario.attach(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES),"images/png", scenario.getName());
            scenario.log("Scenario failed");
        }
        System.out.println("\n"+status+" End of: " + scenario.getName() + " scenario.");
        driver.quit();
        driver = null;
    }
}
