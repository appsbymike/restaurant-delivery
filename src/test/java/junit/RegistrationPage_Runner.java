package junit;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/java/cucumber_feature/RegistrationPage.feature",
		glue="cucumber_stepDefinition/",
		format= {"pretty","html:target/Destination"}
		)
public class RegistrationPage_Runner {

}
