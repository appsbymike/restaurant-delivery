package cucumber_runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/java/cucumber_feature/LoginPage.feature",
		glue="cucumber_stepDefinition.LoginPage",
		format= {"pretty","html:target/CucumberReports/LoginPage"}
		)
public class LoginPage_Runner {

}