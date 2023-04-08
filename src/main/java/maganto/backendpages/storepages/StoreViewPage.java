package maganto.backendpages.storepages;

import maganto.utility.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StoreViewPage {

    //Store Manager can create a store view
    WebDriver driver;
    TestUtility testUtility;

    @FindBy(xpath = "//span[contains(text(),'System')]")
    WebElement systemLink;
    @FindBy(xpath = "//span[contains(text(),'Manage Stores')]")
    WebElement manageStoresLink;
    @FindBy(xpath = "/html/body/div[1]/div[3]/div/div[2]/table/tbody/tr/td[2]/button[3]/span/span/span")
    WebElement createStoreViewLink;



}
