package maganto.backendpages.reportingpages;

import maganto.utility.ExcelUtility;
import maganto.utility.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.logging.XMLFormatter;

public class DownloadsPage {
    WebDriver driver;
    TestUtility testUtility;
    Actions actions;

    public DownloadsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        testUtility=new TestUtility(driver);
        actions=new Actions(driver);
    }

    //Reporting Manager should be able to see Products - Products Downloads Report
    @FindBy(xpath = "//span[contains(text(),\"Reports\")]")
    WebElement reportsMenu;
    @FindBy(xpath = "//*[text()='Products']")
    WebElement productsField;
    @FindBy(xpath = "//*[text()=\"Downloads\"]")
    WebElement downloadsLink;
    @FindBy(css = ".data>tbody>tr")
    List<WebElement> downloadsTable;

    //Reporting Manager should be able to see Reviews - Product Reviews Report
    @FindBy(xpath = "//*[text()=\"Reviews\"]")
    WebElement reviewMenu;
    @FindBy(xpath = "//span[contains(text(),\"Products Reviews\")]")
    WebElement productsReviewLink;
    @FindBy(xpath = "//*[text()=\"Product Name\"]")
    WebElement productName;
    @FindBy(xpath = "//tbody/tr[@class=\"even pointer\"]/td")
    List<WebElement> productsReviewsPage;




    public void seeProductsDownloadsReport (){
        testUtility.waitForElementPresent(reportsMenu);
        reportsMenu.click();
        testUtility.waitForElementPresent(productsField);
        productsField.click();
        testUtility.waitForElementPresent(downloadsLink);
        downloadsLink.click();
//        actions.moveToElement(reportsMenu).click().build().perform();
//        actions.moveToElement(productsField).click().build().perform();
//        actions.moveToElement(downloadsLink).click().build().perform();
    }
    public boolean verifySeeProductsDownloadsReport() {
        testUtility.waitForElementPresent(downloadsTable.get(1));
        if (downloadsTable.size() >= 1) {
            return true;
        } else {
            return false;
        }
    }
    public void seeProductsReviews(){
        testUtility.waitForElementPresent(reportsMenu);
        actions.moveToElement(reportsMenu).moveToElement(reviewMenu).moveToElement(productsReviewLink).click().build().perform();
    }
    public boolean verifySeeProductsReview(){

        if (productsReviewsPage.size()>=1){
            return true;
        }
        else {
            return false;
        }
    }


}

