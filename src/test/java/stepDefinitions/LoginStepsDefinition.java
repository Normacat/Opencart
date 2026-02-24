package stepDefinitions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags="@smoke", features={"src/test/resources/features"}, glue= {"testCasesLogin", "testBase"},
plugin= {"pretty", "html:target/report.html"})
public class LoginStepsDefinition extends AbstractTestNGCucumberTests{

}
