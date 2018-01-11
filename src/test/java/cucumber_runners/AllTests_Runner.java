package cucumber_runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/java/cucumber_feature/",
		glue="cucumber_stepDefinition",
		plugin= {"pretty","html:target/CucumberReports/AccountDetailsPage","junit:target/CucumberReports/AccountDetailsPage/junit.xml"}
		)
public class AllTests_Runner {

}
