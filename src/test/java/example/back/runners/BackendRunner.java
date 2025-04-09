package example.back.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",        // Path to feature files
        glue = {"example/back/stepDefinitions", "example/back/hooks"},                   // Package containing step definitions
        plugin = { "pretty", "html:target/cucumber-reports.html" }, // Reporting plugins
        monochrome = true,                          // Makes console output more readable
        snippets = CucumberOptions.SnippetType.CAMELCASE, //generates stepDefinitions
        tags = "@this_test_back"                             // (Optional) Specify tags to run specific tests
)
public class BackendRunner {
}
