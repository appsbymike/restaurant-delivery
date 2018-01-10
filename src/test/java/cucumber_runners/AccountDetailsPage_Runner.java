package cucumber_runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/java/cucumber_feature/AccountDetailsPage.feature",
		glue="cucumber_stepDefinition.AccountDetailsPage",
		plugin= {"pretty","html:target/CucumberReports/AccountDetailsPage","junit:target/CucumberReports/AccountDetailsPage/junit.xml"}
		)
public class AccountDetailsPage_Runner {

}
