package regressiontestsuit.cucumber;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty","html:target/cucumber-report.html",
                "json:target/cucumber.json",
                "junit:target/cucumber-results.xml"},//for test report
        //features = {"src/test/resources/features/reporting.feature"},//feature file location
        features="src/test/resources/features",
        dryRun = false
        ,
        //tags ="@SeeSales-TotalInvoicedVsPaidReport or @CustomerByOrdersTotal")
        //tags  = "@MarketingManagerViewAllReviews")
        //tags="@MarketingManagerViewPendingReviews")
       //tags="@MarketingManagerUpdateExistingReviews")
        tags="@MarektingManagerUpdateExistingPendingReviews")
//tags="@viewRefundsInReports"
//tags="@MarketingModuleTest"))

public class CucumberTestRunner {




}
