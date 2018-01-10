package cucumber_runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/java/cucumber_feature/MenuPage.feature",
		glue="cucumber_stepDefinition.MenuPage",
		format= {"pretty","html:target/CucumberReports/MenuPage"}
		)
public class MenuPage_Runner {

}
