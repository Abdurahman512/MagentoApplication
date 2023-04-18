package regressiontestsuit.testng;

import maganto.backendpages.BackEndLogin;
import maganto.backendpages.catalogpages.AttributesPage;
import maganto.backendpages.catalogpages.CatalogPage;
import maganto.backendpages.catalogpages.CategoriesPage;
import maganto.backendpages.catalogpages.ProductPage;
import maganto.utility.ApplicationConfig;
import maganto.utility.TestBase;
import maganto.utility.TestResultListener;
import maganto.utility.TestUtility;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

@Listeners(TestResultListener.class)
public class CatalogModuleTestRunner extends TestBase {
    TestUtility utility;
    CategoriesPage categoriesPage;
    AttributesPage attributesPage;
    CatalogPage catalogPage;
    ProductPage productPage;
    final static String configFile = "config.properties";

    @BeforeClass
    public void setUp(ITestContext context) {
        browserSetUp(ApplicationConfig.readFromConfigProperties(configFile, "backendurl"));
        BackEndLogin login = new BackEndLogin(driver);
        login.catalogPageLogin();
        categoriesPage = new CategoriesPage(driver);
        attributesPage = new AttributesPage(driver);
        productPage = new ProductPage(driver);
        catalogPage = new CatalogPage(driver);
        utility = new TestUtility(driver);
        context.setAttribute("driver", driver);
    }

    @Test
    public void addProduct() {
        productPage.userAddProduct();
        Assert.assertTrue(productPage.verifyNewProductAdded());
    }

    @Test(dependsOnMethods = {"addProduct"}, priority = 1)
    public void updateProduct() {
        productPage.updateProduct();
        Assert.assertTrue(productPage.verifyUpdateProduct());
    }

    @Test(dependsOnMethods = {"updateProduct"})
    public void deleteProduct() {
        productPage.deleteProduct();
        Assert.assertTrue(productPage.verifyDeleteProduct());
    }

    @Test(priority = 2, description = "Catalog Manager can view all categories under each Default Category")
    public void viewAllCategoriesTest() {
        Assert.assertTrue(categoriesPage.viewAllCategories());
    }

    @Test(dependsOnMethods = {"viewAllCategoriesTest"}, description = "Category Manager can filter products in the Category Products tab")
    public void filterProductsTest() {
        categoriesPage.filterProducts("Shoes", "15", "1000");
        Assert.assertTrue(categoriesPage.verifyFilter());
    }

    @Test(priority = 3)
    public void addNewAttributeTest() {
        attributesPage.clickOnAddNewAttributeButton();
        Assert.assertTrue(attributesPage.verifyAttributeAddedSuccessfully());
    }

    @Test(dependsOnMethods = {"addNewAttributeTest"})
    public void filterSearchTerms() {
        attributesPage.filterSearchTerms();
        Assert.assertTrue(attributesPage.verifyFilterSearchTerms());
    }

    @Test(priority = 4)
    public void addRootCategoryTest() {
        categoriesPage.addRootCategories();
        Assert.assertTrue(categoriesPage.isRootCategoryAdded());
    }

    @Test(dependsOnMethods = {"addRootCategoryTest"})
    public void addSubCategoryTest() {
        categoriesPage.addSubCategories();
        Assert.assertTrue(categoriesPage.isSubCategoryAdded());
    }

    @Test(dependsOnMethods = {"addSubCategoryTest"})
    public void editRootCategoryTest() {
        categoriesPage.editRootCategory();
        Assert.assertTrue(categoriesPage.isRootCategoryEdited());
    }

    @Test(dependsOnMethods = {"editRootCategoryTest"})
    public void editSubCategoryTest() {
        categoriesPage.editSubCategory();
        Assert.assertTrue(categoriesPage.isSubCategoryEdited());
    }

    @Test(dependsOnMethods = {"editSubCategoryTest"})
    public void deleteSubCategoryTest() {
        categoriesPage.deleteSubCategory();
        Assert.assertTrue(categoriesPage.isSubCategoryDeleted());
    }

    @Test(dependsOnMethods = {"deleteSubCategoryTest"})
    public void deleteRootCategoryTest() {
        categoriesPage.deleteRootCategory();
        Assert.assertTrue(categoriesPage.isRootCategoryDeleted());
    }

    @Test(description = "addNewSearchTerm", priority = 5)
    public void addNewSearchTerm() {
        catalogPage.addNewSearchTerm1();
        Assert.assertTrue(catalogPage.verifySuccessfullyMessage());
    }

    @Test(description = "editNewSearchTerm", dependsOnMethods = {"addNewSearchTerm"})
    public void editSearchTerm() {
        catalogPage.EditSearchTerm();
        Assert.assertTrue(catalogPage.verifyYouSavedTheSearchTermMessage());
    }

    @Test(description = "deletedSearchTerm",dependsOnMethods = {"editSearchTerm"} )
    public void deletedSearchTerm() {
        catalogPage.deleteSearchTerm();
        Assert.assertTrue(catalogPage.verifyDeleteSearchTerm());
    }

    @AfterClass
    public void tearDown() {
        closeBrowser();
    }


}
