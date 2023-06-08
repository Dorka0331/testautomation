package hu.masterfield.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        /* features = "@target/cucumber-rerun.txt"*/
        glue = {"hu.masterfield.steps"},
        tags = "(@TC_ChangeLang and @important) or @TC_ChangeLangWithDataTable")/*,
        plugin = { "pretty", "summary", "json:target/cucumber-reports.json",
                "junit:target/cucumber-reports.xml", "html:target/cucumber-reports.html",
                "rerun:target/cucumber-rerun.txt"},
        publish = true)*/
public class TestRunner {
}
