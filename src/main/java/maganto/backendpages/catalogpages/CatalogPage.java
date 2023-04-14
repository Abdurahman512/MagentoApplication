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
    //String newSearchTerm;



    public CatalogPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        testUtility =new TestUtility(driver);
    }
    @FindBy(xpath ="//span[text()='Catalog']" )
    WebElement addNewSearchtermButton;
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
    WebElement DisplayinSuggestedTerms;
    @FindBy(xpath = "//span[text()='Save Search']")
    WebElement SaveSearch;
    @FindBy(xpath = "//span[text()='You saved the search term.']")
    WebElement successfullyMessage;
    String searchName=null;

//for searchTerm

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


    // for delete  search term

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
            WebElement DeleteSearch;









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
        testUtility.waitForElementPresent(addNewSearchtermButton);
        addNewSearchtermButton.click();
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
        Select select1=new Select(DisplayinSuggestedTerms);
        select1.selectByValue("1");
        DisplayinSuggestedTerms.click();
        SaveSearch.click();
        //return searchQuery;


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
        updatedSearchQuery=testUtility.generateStateName()+System.currentTimeMillis();
        searchQueryField.sendKeys(updatedSearchQuery);
        testUtility.sleep(1);
        testUtility.waitForElementPresent(editSaveButton);
        editSaveButton.click();



    }
    public boolean verifyYousavedthesearchtermMessage(){
        testUtility.waitForElementPresent(successfullyMessage);
        if (successfullyMessage.isDisplayed());
        return true;
    }
    public void deleteSearchTerm(){
        testUtility.sleep(1);
        testUtility.waitForElementPresent(CatalogDeleted);
        CatalogDeleted.click();
        testUtility.sleep(1);
        testUtility.waitForElementPresent(searchTermForDeleted);
        searchTermForDeleted.click();
        testUtility.sleep(2);
        testUtility.waitForElementPresent(query1);
        query1.click();
        testUtility.sleep(1);
        query1.sendKeys(updatedSearchQuery);
        testUtility.sleep(1);
        testUtility.waitForElementPresent(searchDeleted);
        searchDeleted.click();
        testUtility.sleep(1);
        testUtility.waitForElementPresent(editIconDeleted);
        editIconDeleted.click();
        testUtility.sleep(1);
        testUtility.waitForElementPresent(DeleteSearch);
        DeleteSearch.click();
        Alert alert=driver.switchTo().alert();
        alert.accept();

    }



}
