package cucumber_runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/java/cucumber_feature/AdminUserDetailsPage.feature",
		glue="cucumber_stepDefinition",
		plugin= {"pretty","html:target/CucumberReports/AdminUserDetailsPage","junit:target/CucumberReports/AdminUserDetailsPage/junit.xml"}
		)
public class AdminUserDetailsPage_Runner {

}
