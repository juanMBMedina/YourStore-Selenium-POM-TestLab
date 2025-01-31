package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",        // Path to feature files
        glue = {"stepDefinitions", "hooks"},                   // Package containing step definitions
        plugin = { "pretty", "html:target/cucumber-reports.html" }, // Reporting plugins
        monochrome = true,                          // Makes console output more readable
        snippets = CucumberOptions.SnippetType.CAMELCASE, //generates stepDefinitions
        tags = "@this_test"                             // (Optional) Specify tags to run specific tests
)
public class Runner {
}
