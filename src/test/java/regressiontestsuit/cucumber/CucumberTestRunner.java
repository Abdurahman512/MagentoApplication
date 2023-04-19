package regressiontestsuit.cucumber;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-pretty", "html:target/cucumber-report.html",
                "json:target/cucumber.json",
                "junit:target/cucumber-results.xml"},
        dryRun = false,//for test report
        features = {"src/test/resources/features/sales.feature"},//feature file location
        // tags ="@SeeSales-TotalInvoicedVsPaidReport or @CustomerByOrdersTotal")
        //tags = "@ViewInvoiceCommentHistory")
          tags="@AddInvoiceComment")


public class CucumberTestRunner {


}
