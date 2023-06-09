package maganto.backendpages.catalogpages;

import maganto.utility.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



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

    //Catalog manager can add a new attributes under a catalog
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

    // Catalog Manager can filter search terms
    @FindBy(xpath = "//span[contains(text(),\"Search Terms\")]")
    WebElement searchTermsLink;
    @FindBy(xpath ="//input[@id=(\"catalog_search_grid_filter_search_query\")]" )
    WebElement searchQueryField;
    @FindBy(xpath ="//*[@class='scalable task']" )
    WebElement searchButton;
    @FindBy(xpath = "//input[@value='tea cup']")
    WebElement filterSearchSuccessMessage;


    public void filterSearchTerms(){
        testUtility.waitForElementPresent(catalogLink);
        actions.moveToElement(catalogLink).moveToElement(searchTermsLink).click().build().perform();
        testUtility.waitForElementPresent(searchQueryField);
        searchQueryField.sendKeys("tea cup");
        testUtility.waitForElementPresent(searchButton);
        searchButton.click();

    }
    public boolean verifyFilterSearchTerms(){
        testUtility.waitForElementPresent(filterSearchSuccessMessage);
        if(filterSearchSuccessMessage.isDisplayed())
            return true;
        else return false;
    }

    public void clickOnAddNewAttributeButton(){
        testUtility.waitForElementPresent(catalogLink);
        actions.moveToElement(catalogLink).build().perform();
        testUtility.waitForElementPresent(attributes);
        actions.moveToElement(attributes).build().perform();
        testUtility.waitForElementPresent(manageAttributes);
        actions.moveToElement(manageAttributes).click(manageAttributes).build().perform();
        testUtility.sleep(2);
        testUtility.waitForElementPresent(addNewAttributeButton);
        addNewAttributeButton.click();
        testUtility.waitForElementPresent(attributeCode);
        attributeCode.sendKeys("leyla"+testUtility.generateNumber());
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
