package cucumber_runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/java/cucumber_feature/OrderReviewPage.feature",
		glue="cucumber_stepDefinition",
		plugin= {"pretty","html:target/CucumberReports/OrderReviewPage","junit:target/CucumberReports/OrderReviewPage/junit.xml"}
		)
public class OrderReviewPage_Runner {

}
