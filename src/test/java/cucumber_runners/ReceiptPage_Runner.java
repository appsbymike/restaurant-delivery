package cucumber_runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/java/cucumber_feature/ReceiptPage.feature",
		glue="cucumber_stepDefinition.ReceiptPage",
		plugin= {"pretty","html:target/CucumberReports/ReceiptPage","junit:target/CucumberReports/ReceiptPage/junit.xml"}
		)
public class ReceiptPage_Runner {

}
