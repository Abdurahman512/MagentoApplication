package maganto.frontendpages;

import maganto.utility.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddressBookPage {

    WebDriver driver;
    // update/view address book
    @FindBy(xpath = "//span[@class='label' and text()='Account']")
    WebElement accountLink;
    @FindBy(linkText = "My Account")
    WebElement myAccountLink;

    @FindBy(linkText = "Address Book")
    WebElement addressBookLink;

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
    @FindBy(xpath= "//span[text()='Save Address']")
    WebElement saveAddressButton;

    TestUtility utility;


    public void updateAddressBook(String firstName, String lastName, int telephone, String streetAddress,
                                  String city, String state, int zipCode, String country)
    {
        utility.waitForElementPresent(accountLink);
        accountLink.click();
        utility.waitForElementPresent(myAccountLink);
        myAccountLink.click();
        utility.waitForElementPresent(addressBookLink);
        addressBookLink.click();
        utility.waitForElementPresent(firstNameField);
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        telephoneField.sendKeys(String.valueOf(telephone));
        streetAddressDropDown.sendKeys(streetAddress);
        cityField.sendKeys(city);
        stateDropDown.sendKeys(state);
        zipCodeField.sendKeys(String.valueOf(zipCode));
        countryDropDown.sendKeys(country);
        saveAddressButton.click();
    }

    public void viewAddressBook(){
        utility.waitForElementPresent(accountLink);
        accountLink.click();
        utility.waitForElementPresent(myAccountLink);
        myAccountLink.click();
        utility.waitForElementPresent(addressBookLink);
        addressBookLink.click();
    }

    public boolean isAddressBookAbleToView(){
        utility.waitForElementPresent(addNewAddressBookPage);
        if (addNewAddressBookPage.isDisplayed())
            return true;
        else return false;
    }
}
