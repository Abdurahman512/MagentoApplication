package regressiontestsuit.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import org.openqa.selenium.WebDriver;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-pretty", "html:target/cucumber-report.html",
                "json:target/cucumber.json",

                "junit:target/cucumber-results.xml"},// for test report
        features="src/test/resources/features", // feature file location
        tags = "@CatalogPriceRule"
        //tags = "@updateexistingCatalogPriceRule"
        //tags = "@searchByIdAndRule"
)


public class CucumberTestRunner {

}
