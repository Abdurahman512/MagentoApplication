package maganto.backendpages.catalogpages;

import com.github.javafaker.Faker;
import maganto.utility.DataHelper;
import maganto.utility.TestUtility;
import org.apache.tools.ant.taskdefs.Sleep;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CatalogPage {


    TestUtility testUtility;
    WebDriver driver;

    public CatalogPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        testUtility =new TestUtility(driver);
    }

    // Add New SearchTerm
    @FindBy(xpath ="//span[text()='Catalog']" )
    WebElement CatalogButton;
    @FindBy(xpath = "//span[text()='Search Terms']")
    WebElement SearchTerm;
    @FindBy(xpath = "//span[text()='Add New Search Term']")
    WebElement addNewSearchTerm;
    @FindBy(id ="query_text")
    WebElement searchQueryField;
    @FindBy(id = "store_id")
   WebElement Store;
    @FindBy(id = "synonym_for")
    WebElement SynonymFor;
    @FindBy(id = "display_in_terms")
    WebElement DisPlayingSuggestedTerms;
    @FindBy(xpath = "//span[text()='Save Search']")
    WebElement SaveSearch;
    @FindBy(xpath = "//span[text()='You saved the search term.']")
    WebElement successfullyMessage;
    String searchName=null;

    // Edit Search Term

    @FindBy(xpath = "//input[@id='catalog_search_grid_filter_search_query']")
     WebElement queryField;
    @FindBy(css = ".scalable.task")
            WebElement searchButton;
    @FindBy(xpath ="//table[@id=\"catalog_search_grid_table\"]//tbody/tr/td[9]//a[.='Edit']" )
            WebElement editIcon;
    @FindBy(id = "catalog_search_grid_filter_search_query")
            WebElement editSearchQueryField;
    @FindBy(xpath ="//span[text()='Save Search']")
            WebElement editSaveButton;
    @FindBy(id = "catalog_search_grid_filter_search_query")
            WebElement SearchQueryFieldForEdit;
    String updatedSearchQuery;


    // Deleted Search Term

    @FindBy(xpath ="//span[text()='Catalog']" )
    WebElement CatalogDeleted;
    @FindBy(xpath = "//span[text()='Search Terms']")
            WebElement searchTermForDeleted;
    @FindBy(id = "catalog_search_grid_filter_search_query")
            WebElement query1;
    @FindBy(xpath = "(//span[text()='Search'])[1]")
            WebElement searchDeleted;
    @FindBy(xpath = "//tbody/tr/td[9]//a[.='Edit']")
            WebElement editIconDeleted;
    @FindBy(xpath = "//span[text()='Delete Search']")
            WebElement deleteSearch;

    Faker faker=new Faker();
    public String generateName(){
        String searchName=faker.name().title();
        return searchName;

    }
    public String generateNumber(){
        String searchNumber=faker.number().digit();
        return searchNumber;
    }
    public String generateSearchQueryName() {

        String searchQuery1=faker.book().title();
        return searchQuery1;

    }
    public void addNewSearchTerm1(){
        testUtility.sleep(1);
        testUtility.waitForElementPresent(CatalogButton);
        CatalogButton.click();
        testUtility.sleep(1);
        testUtility.waitForElementPresent(SearchTerm);
        SearchTerm.click();
       testUtility.sleep(1);
        testUtility.waitForElementPresent(addNewSearchTerm);
        addNewSearchTerm.click();
        testUtility.sleep(1);
        searchName=generateSearchQueryName();
        DataHelper.setQueryName(searchName);
        testUtility.waitForElementPresent(searchQueryField);
        searchQueryField.sendKeys(searchName);
        testUtility.sleep(1);
        testUtility.waitForElementPresent(Store);
        Select select=new Select(Store);
        select.selectByValue("159");
        Store.click();
        testUtility.sleep(1);
        testUtility.waitForElementPresent(SynonymFor);
        SynonymFor.sendKeys(generateNumber()+System.currentTimeMillis());
        Select select1=new Select(DisPlayingSuggestedTerms);
        select1.selectByValue("1");
        DisPlayingSuggestedTerms.click();
        SaveSearch.click();



    }
    public boolean verifySuccessfullyMessage(){

        testUtility.waitForElementPresent(successfullyMessage);
        if (successfullyMessage.isDisplayed());
        return true;
    }
    public void EditSearchTerm(){
        testUtility.sleep(2);
        testUtility.waitForElementPresent(SearchQueryFieldForEdit);
        SearchQueryFieldForEdit.sendKeys(DataHelper.getQueryName());
        testUtility.waitForElementPresent(searchButton);
        searchButton.click();
        testUtility.sleep(2);
        testUtility.waitForElementPresent(editIcon);
        editIcon.click();
        testUtility.sleep(1);
        testUtility.waitForElementPresent(searchQueryField);
        searchQueryField.clear();
        DataHelper.setQueryName1(testUtility.generateStateName()+System.currentTimeMillis());
        searchQueryField.sendKeys(DataHelper.getQueryName1());
        testUtility.sleep(1);
        testUtility.waitForElementPresent(editSaveButton);
        editSaveButton.click();



    }
    public boolean verifyYouSavedTheSearchTermMessage(){
        testUtility.waitForElementPresent(successfullyMessage);
        if (successfullyMessage.isDisplayed());
        return true;
    }
    public void deleteSearchTerm(){
        testUtility.sleep(2);
        testUtility.waitForElementPresent(SearchQueryFieldForEdit);
        SearchQueryFieldForEdit.clear();
        SearchQueryFieldForEdit.sendKeys(DataHelper.getQueryName1());
        testUtility.waitForElementPresent(searchButton);
        searchButton.click();
        testUtility.sleep(2);
        testUtility.waitForElementPresent(editIcon);
        editIcon.click();
        testUtility.sleep(1);
        testUtility.waitForElementPresent(deleteSearch);
        deleteSearch.click();
        Alert alert=driver.switchTo().alert();
        alert.accept();






    }



}
