package cucumber_runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/java/cucumber_feature/PaymentPage.feature",
		glue="cucumber_stepDefinition.PaymentPage",
		plugin= {"pretty","html:target/CucumberReports/PaymentPage","junit:target/CucumberReports/PaymentPage/junit.xml"}
		)
public class PaymentPage_Runner {

}
