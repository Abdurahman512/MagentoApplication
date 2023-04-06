package maganto.frontendpages;

import maganto.utility.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountInfoPage {
    //create account
    WebDriver driver;
    @FindBy(xpath = "//span[@class='label' and text()='Account']")
    WebElement accountLink;
    @FindBy(linkText = "Register")
    WebElement registerLink;
    @FindBy(id = "firstname")
    WebElement firstNameField;
    @FindBy(id = "lastname")
    WebElement lastNameField;
    @FindBy(id = "email_address")
    WebElement emailField;
    @FindBy(id = "password")
    WebElement passwordField;
    @FindBy(id = "confirmation")
    WebElement confirmPassword;
    @FindBy(xpath = "//span[text()='Register']")
    WebElement registerButton;
    @FindBy(xpath = "//span[text()='Thank you for registering with eCommerce Shopping.']")
    WebElement registerSuccessMessage;

    //edit account
    @FindBy(xpath = "//a[text()='Account Information']")
    WebElement accountInfoLink;
    @FindBy(id="middlename")
    WebElement middleNameField;
    @FindBy(id="current_password")
    WebElement currentPasswordField;
    @FindBy(xpath = "//span[text()='Save']")
    WebElement saveButton;
    @FindBy(xpath = "//span[text()='The account information has been saved.']")
    WebElement editSuccessMessage;
    //view account
    @FindBy(xpath = "//h2[text()='Account Information']")
    WebElement accountInfoPage;
    @FindBy(id = "change_password")
     WebElement changepasswordChechbox;
    @FindBy(id = "password")
     WebElement newPassword;
    @FindBy(id = "confirmation")
     WebElement getConfirmNewPassword;



    TestUtility utility;

    public AccountInfoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        utility=new TestUtility(driver);
    }

    public void userCreateAccount(String firstName, String lastname, String email,String password){
        utility.waitForElementPresent(accountLink);
        accountLink.click();
        utility.waitForElementPresent(registerLink);
        registerLink.click();
        utility.waitForElementPresent(firstNameField);
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastname);
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        confirmPassword.sendKeys(password);
        registerButton.click();

    }
    public boolean isAccountCreated(){
        utility.waitForElementPresent(registerSuccessMessage);
        if(registerSuccessMessage.isDisplayed())
            return true;
        else return false;
    }

    public void editAccount(String midName, String currentPassword){
        utility.waitForElementPresent(accountInfoLink);
        accountInfoLink.click();
        utility.waitForElementPresent(middleNameField);
        middleNameField.sendKeys(midName);
        currentPasswordField.sendKeys(currentPassword);
        saveButton.click();
    }

    public boolean isAccountEdited(){
        utility.waitForElementPresent(editSuccessMessage);
        if(editSuccessMessage.isDisplayed())
            return true;
        else return false;
    }

    public void viewAccount(){
        utility.waitForElementPresent(accountInfoLink);
        accountInfoLink.click();
    }
    public boolean isAccountViewed(){
        utility.waitForElementPresent(accountInfoPage);
        if(accountInfoPage.isDisplayed())
            return true;
        else return false;
    }
    public void changePassword(String password,String NewPassword,String ConfirmNewPassword){
        utility.waitForElementPresent(accountInfoLink);
        accountInfoLink.click();
        utility.waitForElementPresent(currentPasswordField);
        currentPasswordField.sendKeys(password);
        utility.waitForElementPresent(changepasswordChechbox);
        changepasswordChechbox.click();
        utility.waitForElementPresent(newPassword);
        newPassword.sendKeys(NewPassword);
        utility.waitForElementPresent(getConfirmNewPassword);
        getConfirmNewPassword.sendKeys(ConfirmNewPassword);

    }



}
