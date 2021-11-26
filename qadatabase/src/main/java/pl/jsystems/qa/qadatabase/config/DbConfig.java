package pl.jsystems.qa.qadatabase.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class DbConfig {

    private static final Config CONFIG = ConfigFactory.load("qadbconfig.conf");
    private static final String ENVIRONMENT = CONFIG.getString("environment");
    private static final Config ENV = CONFIG.getConfig("environments").getConfig(ENVIRONMENT);

    public static final String DB_CLASS = ENV.getString("dbClass");
    public static final String DB_URL = ENV.getString("dbUrl");
    public static final String DB_USER = ENV.getString("dbUser");
    public static final String DB_PASS = ENV.getString("dbPass");
}
