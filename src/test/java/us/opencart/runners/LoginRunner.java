package us.opencart.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/opencart",
        glue = {"us/opencart/stepDefinitions", "us/opencart/hooks"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "json:target/cucumber-reports/cucumber.json"
        },
        monochrome = true,
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        tags = "@your_store_login_feature"
)
public class LoginRunner {
}
