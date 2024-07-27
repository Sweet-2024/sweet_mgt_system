package acceptance.pkg;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "use_cases",
        plugin = {"summary", "html:target/cucumber/report.html"},
        monochrome = false,
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        glue = "acceptance.pkg"
)
public class AcceptanceTest {

}
