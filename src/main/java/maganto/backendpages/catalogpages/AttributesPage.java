package maganto.backendpages.catalogpages;

import maganto.utility.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;


public class AttributesPage {
    WebDriver driver;
    TestUtility testUtility;
    Actions actions;

    public AttributesPage(WebDriver driver) {
        this.driver = driver;
        testUtility=new TestUtility(driver);
        PageFactory.initElements(driver,this);
        actions=new Actions(driver);
    }

    @FindBy(xpath ="//span[contains(text(),'Catalog')]" )
    WebElement catalogLink;

    @FindBy(xpath = "(//span[contains(text(),'Attributes')])[1]")
    WebElement attributes;

    @FindBy(xpath = "(//span[contains(text(),'Attributes')])[2]")
    WebElement manageAttributes;

    @FindBy(xpath = "//div[@class='wrapper']//*[@title='Add New Attribute']")
    WebElement addNewAttributeButton;

    @FindBy(xpath ="//input[@id='attribute_code']" )
    WebElement attributeCode;

    @FindBy(xpath = "(//span[text()='Save Attribute'])[1]")
    WebElement saveAttributeButton;

    @FindBy(xpath = "//input[@name=\"frontend_label[0]\"]")
    WebElement adminField;

    @FindBy(xpath = "//span[text()='Save Attribute']")
    WebElement savaAttributeBTN;

    @FindBy(xpath = "//span[text()='The product attribute has been saved.']")
    WebElement successfulMessage;


    public void clickOnAddNewAttributeButton(){
        testUtility.waitForElementPresent(catalogLink);
        actions.moveToElement(catalogLink).moveToElement(attributes).moveToElement(manageAttributes).click().build().perform();
        testUtility.waitForElementPresent(addNewAttributeButton);
        addNewAttributeButton.click();
        testUtility.waitForElementPresent(attributeCode);
        attributeCode.sendKeys("Leyla"+testUtility.generateNumber());
        saveAttributeButton.click();
        testUtility.waitForElementPresent(adminField);
        adminField.sendKeys(testUtility.generateAdminName());
        savaAttributeBTN.click();

    }

    public boolean verifyAttributeAddedSuccessfully(){
        testUtility.waitForElementPresent(successfulMessage);
        return successfulMessage.isDisplayed();
    }
}
