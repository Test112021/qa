package pl.jsystems.qa.qagui.bdd;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.platform.engine.Cucumber;
import org.junit.platform.suite.api.*;
import org.junit.runner.RunWith;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

//@RunWith(Cucumber.class)
//@CucumberOptions(
//        features = "src/test/resources",
//        glue = "classpath:pl.jsystems.qa.qagui.bdd",
//        plugin = {"pretty", "summary", "html:target/cucumber/report.html", "json:target/cucumber.json",
//        "junit:target/cucumber.xml", "rerun:target/rerun.txt"},
//        tags =
////                "@BDD "
////                        +
////                        "and "
////                        +
////                        "@Login "
////                        +
////                        "and "
////                        +
////                        "@wordpress "
////                        +
////                        "and "
////                        +
////                        "@userpanel"
////        "@website"
////        "@website2"
//        "@website3"
//)
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("/")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "pl.jsystems.qa.qagui.bdd")
@IncludeTags({
//        "BDD"
//        ,
//        "BDD-NO-PARAM"
//        ,
//        "BDD-PARAM"
//        ,
        "Login"
//        ,
//        "wordpress"
//        ,
//        "userpanel"
//        ,
//        "website"
//        ,
//        "website2"
//        ,
//        "website3"
})
public class RunTest {


}
