package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        //features = "@rerun/failed_scenarios.txt",
		 features = "src/test/resources/NTTData",
         glue = {"TestStepDefinitions"},
         plugin = { "html:target/cucumber-reports.html"},
         //tags = "@FillFormFullData",
         monochrome = false,
         dryRun = false
)

public class MyRunner extends AbstractTestNGCucumberTests {
	
	

}
