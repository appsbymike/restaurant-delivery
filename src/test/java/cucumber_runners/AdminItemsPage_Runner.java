package cucumber_runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/java/cucumber_feature/AdminItemsPage.feature",
		glue="cucumber_stepDefinition",
		plugin= {"pretty","html:target/CucumberReports/AdminItemsPage","junit:target/CucumberReports/AdminItemsPage/junit.xml"}
		)
public class AdminItemsPage_Runner {

}
