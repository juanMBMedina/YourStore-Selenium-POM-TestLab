package example.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/example",
        glue = {"example/stepDefinitions", "example/hooks"},
        plugin = { "pretty", "html:target/cucumber-reports.html" },
        monochrome = true,
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        tags = "@this_test"
)
public class Runner {
}
