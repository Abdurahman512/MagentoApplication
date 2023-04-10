package maganto.backendpages.storepages;

import maganto.utility.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class StoreProductPage {
    WebDriver driver;
    TestUtility utility;
    Actions actions;

    public StoreProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        utility=new TestUtility(driver);
        actions=new Actions(driver);
    }

    @FindBy(xpath = "//button[@id=\"id_c87ef06a6ee0f71fef6bc2f0f48437fe\"]//span[contains(text(),\"Add Product\")]")
    WebElement addProductButton;
    @FindBy(id = "attribute_set_id")
    WebElement attributeSet;
    @FindBy(xpath = "//button[@title=\"Continue\"]//span[contains(text(),\"Continue\")]")
    WebElement continueButton;


    public void addProductsMethod(){
        utility.waitForElementPresent(addProductButton);
        addProductButton.click();
        utility.waitForElementPresent(attributeSet);
        Select select=new Select(attributeSet);
        select.selectByValue("17");
        utility.sleep(1);
        utility.waitForElementPresent(continueButton);
        continueButton.click();
    }
}
