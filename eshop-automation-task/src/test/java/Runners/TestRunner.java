package Runners;

import io.cucumber.core.gherkin.Feature;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources",glue = "StepDefinition",
        plugin = {"html:target/reports/report.html","json:target/reports/report.json"})

public class TestRunner {
}
