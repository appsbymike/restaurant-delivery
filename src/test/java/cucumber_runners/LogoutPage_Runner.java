package cucumber_runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/java/cucumber_feature/LogoutPage.feature",
		glue="cucumber_stepDefinition",
		plugin= {"pretty","html:target/CucumberReports/LogoutPage","junit:target/CucumberReports/LogoutPage/junit.xml"}
		)
public class LogoutPage_Runner {
}
