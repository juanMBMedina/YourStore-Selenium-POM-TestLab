package us.opencart.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/opencart",
        glue = {"us/opencart/stepDefinitions", "us/opencart/hooks"},
        plugin = { "pretty", "html:target/cucumber-reports.html" },
        monochrome = true,
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        tags = "@login_opencart"
)
public class LoginRunner {
}
