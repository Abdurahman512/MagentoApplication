package maganto.backendpages.reportingpages;
import maganto.utility.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CustomersByOrdersTotal {
    WebDriver driver;
    TestUtility testUtility;

    @FindBy(css = "#period_date_from")
    WebElement dataFromField;
    @FindBy(css = "#period_date_to")
    WebElement dataToField;
    @FindBy(xpath = "//span[text()='Refresh']")
    WebElement refreshButton;
    @FindBy(css = "#gridTotalsCustomer_table")
    WebElement customerTable;
    @FindAll(@FindBy(css = "#gridTotalsCustomer_table>tbody>tr"))
    List<WebElement> customerList;

    public CustomersByOrdersTotal(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        testUtility=new TestUtility(driver);
    }
    public void enterFromData(String fromData){
        testUtility.waitForElementPresent(dataFromField);
        dataFromField.sendKeys(fromData);
    }
    public void enterToData(String toData){
        testUtility.waitForElementPresent(dataToField);
        dataFromField.sendKeys(toData);
    }
    public void clickOnRefreshButton(){
        testUtility.waitForElementPresent(refreshButton);
        refreshButton.click();
    }
    public void customerByOrdersTotalMethod(String fromdata,String todata){
        enterFromData(fromdata);
        enterToData(todata);
        clickOnRefreshButton();

    }
    public boolean verifyManagerCanSeeCustomersByOrdersTotal(){

        return customerList.size()>=1;
    }
}

