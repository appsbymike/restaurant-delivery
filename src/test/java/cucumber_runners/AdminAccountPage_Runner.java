package cucumber_runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/java/cucumber_feature/AdminAccountPage.feature",
		glue="cucumber_stepDefinition",
		plugin= {"pretty","html:target/CucumberReports/AdminAccountPage","junit:target/CucumberReports/AdminAccountPage/junit.xml"}
		)
public class AdminAccountPage_Runner {

}
