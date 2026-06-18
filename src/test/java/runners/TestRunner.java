package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        		glue = {"stepdefinitions","hooks"},
        monochrome = true,
        publish = false,
        tags = "@Cart or @Sort"
)
public class TestRunner extends AbstractTestNGCucumberTests {
	
}
