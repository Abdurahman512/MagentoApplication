package regressiontestsuit.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-product-ui-testReport.html",
                "json:target/cucumber.json"},
        features = {"src/test/resources"},
       //tags = "@TotalOrderReportTest or @RegressionTest or @MarketingModuleTest or @SalesModuleTests or @CreateOrderTest or @UpdateShipments")
        tags = "@SalesModuleTests or @CreateOrderTest or  @UpdateShipments")

public class CucumberTestRunner {


}
