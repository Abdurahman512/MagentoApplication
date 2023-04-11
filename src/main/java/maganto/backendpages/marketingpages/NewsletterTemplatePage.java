package maganto.backendpages.marketingpages;

import maganto.utility.ApplicationConfig;
import maganto.utility.TestUtility;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class NewsletterTemplatePage {
        WebDriver driver;
        TestUtility testUtility;
        Actions actions;
        String configFile = "config-qa.properties";



    public NewsletterTemplatePage(WebDriver driver) {
        this.driver = driver;
        testUtility = new TestUtility(driver);
        PageFactory.initElements(driver, this);
        actions =new Actions(driver);
    }

    // marketing manager can add newsletter template
        @FindBy(xpath = "//span[contains(text(),\"Newsletter\")]")
        WebElement newsletterLink;
        @FindBy(xpath = "//span[contains(text(),\"Newsletter Templates\")]")
        WebElement newsletterTemplatesLink;
        @FindBy(xpath = "//*[contains(text(),\"Add New Template\")]")
        WebElement addNewTemplateButton;
        @FindBy(css = "input#code")
        WebElement templateNameField;
        @FindBy(css = "input#subject")
        WebElement templateSubjectField;
        @FindBy(xpath = "//*[@class='scalable save']")
        WebElement saveTemplateButton;
        @FindBy(xpath = "//*[contains(text(),'Search')]")
        WebElement SearchButton;
       @FindBy(xpath = "/*[@class=\"even pointer\"]")
        WebElement SuccessfulTemplateName;
       @FindBy(xpath = "//*[@class=\"even pointer\"]")
       WebElement selectNewsletterTemplate;
        @FindBy(xpath = "//*[contains(text(),'Delete Template')]")
        WebElement deleteTemplateButton;
        @FindBy(xpath = "//td[contains(text(),'No records found.')]")
        WebElement noRecordsFoundMessage;



        public void addNewNewsletterTemplate(){
            testUtility.waitForElementPresent(newsletterLink);
            actions.moveToElement(newsletterLink).moveToElement(newsletterTemplatesLink).click().build().perform();
            testUtility.waitForElementPresent(addNewTemplateButton);
            addNewTemplateButton.click();
            testUtility.waitForElementPresent(templateNameField);
            templateNameField.sendKeys(ApplicationConfig.readFromConfigProperties(configFile,"config.properties"));
            testUtility.waitForElementPresent(templateSubjectField);
            templateSubjectField.sendKeys(ApplicationConfig.readFromConfigProperties(configFile,"config.properties"));
            testUtility.waitForElementPresent(saveTemplateButton);
            saveTemplateButton.click();

        }
    public boolean verifyAddNewsletterTemplate() {
        testUtility.waitForElementPresent(SuccessfulTemplateName);
        return SuccessfulTemplateName.getText().equals(ApplicationConfig
                .readFromConfigProperties(configFile, "templateName"));
    }

}


