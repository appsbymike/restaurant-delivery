package cucumber_runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/java/cucumber_feature/PastOrdersPage.feature",
		glue="cucumber_stepDefinition",
		plugin= {"pretty","html:target/CucumberReports/PastOrdersPage","junit:target/CucumberReports/PastOrdersPage/junit.xml"}
		)
public class PastOrdersPage_Runner {

}
