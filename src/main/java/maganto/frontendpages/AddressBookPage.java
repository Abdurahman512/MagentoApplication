package maganto.frontendpages;

import maganto.utility.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddressBookPage {

    WebDriver driver;
    // update/view address book

    String addressBook;

    @FindBy(xpath = "//span[@class='label' and text()='Account']")
    WebElement accountLink;
    @FindBy(linkText = "My Account")
    WebElement myAccountLink;

    @FindBy(xpath = "//strong[contains(text(),'Address Book')]")
    WebElement addressBookLink;

    @FindBy(xpath = "@FindBy(xpath = \"//strong[contains(text(),'Address Book')]\")\n" +
            "    WebElement addressBookLink;")
    WebElement editAddressLink;

    @FindBy(id = "firstname")
    WebElement firstNameField;

    @FindBy(id = "lastname")
    WebElement lastNameField;

    @FindBy(id = "telephone")
    WebElement telephoneField;

    @FindBy(id = "street_1")
    WebElement streetAddressDropDown;

    @FindBy(id = "city")
    WebElement cityField;

    @FindBy(id = "region_id")
    WebElement stateDropDown;

    @FindBy(id = "zip")
    WebElement zipCodeField;

    @FindBy(id = "country")
    WebElement countryDropDown;

    @FindBy(xpath = "//h1[text()=('Add New Address')]")
    WebElement addNewAddressBookPage;
    @FindBy(css = ".buttons-set > button")
    WebElement saveAddressButton;

    @FindBy(xpath = "//a[contains(text(),'Change Billing Address')]")
    WebElement changeBillingAddressLink;

    @FindBy(xpath = "//span[contains(text(),'The address has been saved.')]")
    WebElement addedAddressBookSuccessfullyMessage;

    @FindBy(xpath = "//span[contains(text(),'The address has been saved.')]")
    WebElement updatedAddressBookSuccessfullyMessage;

    @FindBy(xpath = "//a[contains(text(),'Manage Addresses')]")
    WebElement manageAddressLink;


    TestUtility utility;

    public AddressBookPage(WebDriver driver) {
        this.driver = driver;
        utility=new TestUtility(driver);
        PageFactory.initElements(driver,this);
    }

    public void addAddressBook(String firstName, String lastName, String telephone, String streetAddress,
                               String city, String zipCode){

        utility.waitForElementPresent(manageAddressLink);
        manageAddressLink.click();
        utility.waitForElementPresent(firstNameField);
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        telephoneField.sendKeys(telephone);
        streetAddressDropDown.sendKeys(streetAddress);
        cityField.sendKeys(city);
        Select select1=new Select(stateDropDown);
        select1.selectByValue("2");
        zipCodeField.sendKeys(zipCode);
        Select select2=new Select(countryDropDown);
        select2.selectByValue("US");
        saveAddressButton.click();
    }

    public boolean verifyAddedAddressBook(){
        utility.waitForElementPresent(addedAddressBookSuccessfullyMessage);
        if (addedAddressBookSuccessfullyMessage.isDisplayed()){
            return true;
        }
        else
            return false;
    }
    public void updateAddressBook(String firstName)
    {
        utility.waitForElementPresent(changeBillingAddressLink);
        changeBillingAddressLink.click();
        utility.waitForElementPresent(firstNameField);
        firstNameField.sendKeys(firstName);
        saveAddressButton.click();
    }

    public boolean verifyUpdateSuccessfully(){
        utility.waitForElementPresent(updatedAddressBookSuccessfullyMessage);
        if (updatedAddressBookSuccessfullyMessage.isDisplayed())
            return true;
        else return false;
    }
}

