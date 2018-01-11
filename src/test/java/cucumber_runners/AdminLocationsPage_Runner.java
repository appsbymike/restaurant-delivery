package cucumber_runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/java/cucumber_feature/AdminLocationsPage.feature",
		glue="cucumber_stepDefinition",
		plugin= {"pretty","html:target/CucumberReports/AdminLocationsPage","junit:target/CucumberReports/AdminLocationsPage/junit.xml"}
		)
public class AdminLocationsPage_Runner {

}
