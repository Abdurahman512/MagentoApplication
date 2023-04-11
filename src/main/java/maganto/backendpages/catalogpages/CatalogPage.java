package maganto.backendpages.catalogpages;

import com.github.javafaker.Faker;
import maganto.utility.TestUtility;
import org.apache.tools.ant.taskdefs.Sleep;
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
    //@FindBy(xpath = "//span[text()='Catalog']")
    //WebElement Catelog;
    @FindBy(xpath ="//span[text()='Catalog']" )
    WebElement addNewSearchtermButton;
    @FindBy(xpath = "//span[text()='Search Terms']")
    WebElement SearchTerm;
    @FindBy(xpath = "//span[text()='Add New Search Term']")
    WebElement addNewSearchTerm;
    @FindBy(id = "query_text")
    WebElement SearchQuery;
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


    //for searchTerm

    @FindBy(id= "catalog_search_grid_filter_search_query")
     WebElement queryField;
    @FindBy(css = ".scalable.task")
            WebElement searchButton;
    @FindBy(xpath ="//table[@id=\"catalog_search_grid_table\"]//tbody/tr/td[9]//a[.='Edit']" )
            WebElement editIcon;
    @FindBy(id = "query_text")
            WebElement editSearchQueryField;
    @FindBy(xpath ="(//span[text()='Save Search'])[1]")
            WebElement editSaveButton;


    Faker faker=new Faker();
    public String generateName(){
        String searchName=faker.name().title();
        return searchName;

    }
    public String generateNumber(){
        String searchNumber=faker.number().digit();
        return searchNumber;
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
        testUtility.waitForElementPresent(SearchQuery);
        SearchQuery.sendKeys(generateName()+System.currentTimeMillis());
        testUtility.sleep(1);
        testUtility.waitForElementPresent(Store);
        Select select=new Select(Store);
        select.selectByValue("47");
        Store.click();
        testUtility.sleep(1);
        testUtility.waitForElementPresent(SynonymFor);
        SynonymFor.sendKeys(generateNumber()+System.currentTimeMillis());
        Select select1=new Select(DisplayinSuggestedTerms);
        select1.selectByValue("1");
        DisplayinSuggestedTerms.click();
        SaveSearch.click();


    }
    public boolean verifySuccessfullyMessage(){

        testUtility.waitForElementPresent(successfullyMessage);
        if (successfullyMessage.isDisplayed());
        return true;
    }
    public void EditSearchTerm(){
        testUtility.sleep(1);
        testUtility.waitForElementPresent(addNewSearchtermButton);
        addNewSearchtermButton.click();
        testUtility.sleep(1);
        testUtility.waitForElementPresent(SearchTerm);
        SearchTerm.click();

        testUtility.sleep(1);
        testUtility.waitForElementPresent(queryField);
        queryField.sendKeys("alkamar");
        testUtility.waitForElementPresent(searchButton);
        searchButton.click();
        testUtility.sleep(1);
        testUtility.waitForElementPresent(editIcon);
        editIcon.click();
        testUtility.sleep(1);
        testUtility.waitForElementPresent(editSearchQueryField);
        editSearchQueryField.sendKeys(generateName()+System.currentTimeMillis());
        testUtility.sleep(1);
        testUtility.waitForElementPresent(editSaveButton);
        editSaveButton.click();


    }
    public boolean verifyYousavedthesearchtermMessage(){
        testUtility.waitForElementPresent(successfullyMessage);
        if (successfullyMessage.isDisplayed());
        return true;
    }



}
