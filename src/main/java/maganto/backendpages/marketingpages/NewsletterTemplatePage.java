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
        @FindBy(css = "input[name='code']")
        WebElement templateNameBox;
        @FindBy(xpath = "//*[contains(text(),'Search')]")
        WebElement searchButton;
       @FindBy(xpath = "//*[@class=\"even pointer\"]")
        WebElement successfulTemplateName;
       @FindBy(xpath = "//*[@class=\"even pointer\"]")
       WebElement selectNewsletterTemplate;
        @FindBy(xpath = "//*[contains(text(),'Delete Template')]")
        WebElement deleteTemplateButton;
        @FindBy(xpath = "//td[contains(text(),'No records found.')]")
        WebElement noRecordsFoundMessage;
        @FindBy(xpath = "(//*[contains(text(),'Template123')])[1]")
        WebElement myTemplate;



        public void addNewNewsletterTemplate(){
            testUtility.waitForElementPresent(newsletterLink);
            actions.moveToElement(newsletterLink).moveToElement(newsletterTemplatesLink).click().build().perform();
            testUtility.waitForElementPresent(addNewTemplateButton);
            addNewTemplateButton.click();
            testUtility.waitForElementPresent(templateNameField);
            templateNameField.sendKeys(ApplicationConfig.readFromConfigProperties(configFile,"tempLateName"));
            testUtility.waitForElementPresent(templateSubjectField);
            templateSubjectField.sendKeys(ApplicationConfig.readFromConfigProperties(configFile,"templateSubject"));
            testUtility.waitForElementPresent(saveTemplateButton);
            saveTemplateButton.click();

        }
        public void clickOnNewsletterTemplate(){
            testUtility.waitForElementPresent(newsletterLink);
            actions.moveToElement(newsletterLink).moveToElement(newsletterTemplatesLink).click().build().perform();
        }
        public void clickOnAddTemplate(){
            testUtility.waitForElementPresent(addNewTemplateButton);
            addNewTemplateButton.click();
        }
        public void fillInTemplateInformationfield(){
            testUtility.waitForElementPresent(templateNameField);
            templateNameField.sendKeys(ApplicationConfig.readFromConfigProperties("config.properties","tempLateName"));
            testUtility.waitForElementPresent(templateSubjectField);
            templateSubjectField.sendKeys(ApplicationConfig.readFromConfigProperties("config.properties","templateSubject"));
            testUtility.waitForElementPresent(saveTemplateButton);

        }
        public void saveTemplateButton(){
            testUtility.waitForElementPresent(saveTemplateButton);
            saveTemplateButton.click();
        }
    public boolean verifyAddNewsletterTemplate() {
        testUtility.waitForElementPresent(successfulTemplateName);
        return successfulTemplateName.getText().equals(ApplicationConfig
                .readFromConfigProperties("config.properties", "templateName"));
    }

    public void updateNewslettersTemplate(){
            testUtility.waitForElementPresent(newsletterLink);
            actions.moveToElement(newsletterLink).moveToElement(newsletterTemplatesLink).click().build().perform();
            testUtility.waitForElementPresent(templateNameField);
        templateNameField.sendKeys(ApplicationConfig
                .readFromConfigProperties("config.properties","TemplateName"));
        testUtility.waitForElementPresent(searchButton);
        searchButton.click();
        testUtility.waitForElementPresent(templateSubjectField);
        templateNameField.sendKeys(ApplicationConfig.readFromConfigProperties("config.properties","templateSubject1"));
        testUtility.waitForElementPresent(saveTemplateButton);
        saveTemplateButton.click();

    }
    public void clickOnTemplateNameField(){
        testUtility.waitForElementPresent(templateNameBox);
        templateNameBox.sendKeys(ApplicationConfig
                .readFromConfigProperties("config.properties","tempLateName"));
    }
    public void clickOnSearchButton(){
        testUtility.waitForElementPresent(searchButton);
        searchButton.click();
        testUtility.waitForElementPresent(myTemplate);
        myTemplate.click();
    }
    public void changeExistingSubjectNameAndSave(){
        testUtility.waitForElementPresent(templateSubjectField);
        templateSubjectField.clear();
        templateSubjectField.sendKeys(ApplicationConfig.readFromConfigProperties("config.properties","templateSubject1"));
        testUtility.waitForElementPresent(saveTemplateButton);
        saveTemplateButton.click();
    }
//    public boolean verifyUpdateNewsletterTemplate() {
//        testUtility.waitForElementPresent(successfulTemplateName);
//        return successfulTemplateName.getText().equals(ApplicationConfig
//                .readFromConfigProperties("config.properties", "templateSubject1"));
//    }


    public void deleteNewsletterTemplate(){
        testUtility.waitForElementPresent(newsletterLink);
        actions.moveToElement(newsletterLink).moveToElement(newsletterTemplatesLink).click().build().perform();
        testUtility.waitForElementPresent(templateNameField);
        templateNameField.sendKeys(ApplicationConfig
                .readFromConfigProperties(configFile,"TemplateName"));
        testUtility.waitForElementPresent(searchButton);
        searchButton.click();
        testUtility.waitForElementPresent(selectNewsletterTemplate);
        selectNewsletterTemplate.click();
        testUtility.waitForElementPresent(deleteTemplateButton);
        deleteTemplateButton.click();

    }


    public void searchTheExistingTemplate(){
        testUtility.waitForElementPresent(newsletterLink);
        actions.moveToElement(newsletterLink).moveToElement(newsletterTemplatesLink).click().build().perform();
        testUtility.waitForElementPresent(templateNameBox);
        templateNameBox.sendKeys(ApplicationConfig
                .readFromConfigProperties("config.properties","tempLateName"));
        testUtility.waitForElementPresent(searchButton);
        searchButton.click();
        testUtility.waitForElementPresent(myTemplate);
       myTemplate.click();

    }
    public void clickOnTheDeleteTemplateButton(){
        testUtility.waitForElementPresent(deleteTemplateButton);
        deleteTemplateButton.click();
    }


}


