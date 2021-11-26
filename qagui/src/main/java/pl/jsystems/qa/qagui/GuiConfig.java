package pl.jsystems.qa.qagui;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class GuiConfig {

    private static final Config CONFIG = ConfigFactory.load("qaguiconfig.conf");
    private static final String ENVIRONMENT = CONFIG.getString("environment");
    private static final Config ENV = CONFIG.getConfig("environments").getConfig(ENVIRONMENT);

    public static final String BASE_URL = ENV.getString("baseUrl");
    public static final String LOGIN = ENV.getString("login");
    public static final String PASSWORD = ENV.getString("password");

    public static final String BROWSER = CONFIG.getString("browser");
    public static final String MACHINE = CONFIG.getString("machine");
    public static final String REMOTE_URL = CONFIG.getString("remoteUrl");

}
