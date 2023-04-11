package regressiontestsuit.testng;

import maganto.backendpages.BackEndLogin;
import maganto.backendpages.catalogpages.AttributesPage;
import maganto.backendpages.catalogpages.CategoriesPage;
import maganto.backendpages.catalogpages.ProductPage;
import maganto.utility.ApplicationConfig;
import maganto.utility.TestBase;
import maganto.utility.TestResultListener;
import maganto.utility.TestUtility;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestResultListener.class)
public class CatalogModuleTestRunner extends TestBase {

    TestUtility utility;
    CategoriesPage categoriesPage;
    AttributesPage attributesPage;

    ProductPage productPage;
    final static String configFile = "config.properties";

    @BeforeClass
    public void setUp(ITestContext context) {
        browserSetUp(ApplicationConfig.readFromConfigProperties(configFile, "backendurl"));
        BackEndLogin login=new BackEndLogin(driver);
        login.catalogPageLogin();
        categoriesPage = new CategoriesPage(driver);
        attributesPage=new AttributesPage(driver);
        utility = new TestUtility(driver);
        productPage=new ProductPage(driver);
        context.setAttribute("driver",driver);
    }
    @Test
    public void addNewAttributeTest(){
        attributesPage.clickOnAddNewAttributeButton();
        Assert.assertTrue(attributesPage.verifyAttributeAddedSuccessfully());
    }
    @Test
    public void filterSearchTerms(){
        attributesPage.filterSearchTerms();
        Assert.assertTrue(attributesPage.verifyFilterSearchTerms());
    }
    @Test
    public void addRootCategoryTest(){
        categoriesPage.addRootCategories("TestData/TestData-M.xlsx","Category",1,0);
        Assert.assertTrue(categoriesPage.isRootCategoryAdded());
    }
    @Test(dependsOnMethods ={"addRootCategoryTest"})
    public void addSubCategoryTest(){
        categoriesPage.addSubCategories("TestData/TestData-M.xlsx","Category",1,1);
        Assert.assertTrue(categoriesPage.isSubCategoryAdded());
    }
    @Test(dependsOnMethods ={"addSubCategoryTest"})
    public void editRootCategoryTest(){
        categoriesPage.editRootCategory("TestData/TestData-M.xlsx","Category",1,2);
        Assert.assertTrue(categoriesPage.isRootCategoryEdited());
    }
    @Test(dependsOnMethods ={"editRootCategoryTest"})
    public void editSubCategoryTest(){
        categoriesPage.editSubCategory("TestData/TestData-M.xlsx","Category",1,2);
        Assert.assertTrue(categoriesPage.isSubCategoryEdited());
    }

    @Test(dependsOnMethods ={"editSubCategoryTest"})
    public void deleteSubCategoryTest(){
        categoriesPage.deleteSubCategory();
        Assert.assertTrue(categoriesPage.isSubCategoryDeleted());
    }

    @Test(dependsOnMethods ={"deleteSubCategoryTest"})
    public void deleteRootCategoryTest(){
        categoriesPage.deleteRootCategory();
        Assert.assertTrue(categoriesPage.isRootCategoryDeleted());

    }

    @Test()
    public void addProduct(){
        productPage.userAddProduct();

        Assert.assertTrue(productPage.verifyNewProductAdded());
    }

    @Test(dependsOnMethods = "addProduct")
    public void updateProduct(){
        productPage.updateProduct();
        Assert.assertTrue(productPage.verifyUpdateProduct());
    }
    @Test(dependsOnMethods = "addProduct")
    public void deleteProduct(){
        productPage.deleteProduct();
        Assert.assertTrue(productPage.verifyDeleteProduct());
    }

    @Test(description = "Catalog Manager can view all categories under each Default Category")
    public void viewAllCategoriesTest(){
        Assert.assertTrue(categoriesPage.viewAllCategories());
    }

    @Test(description = "Category Manager can filter products in the Category Products tab")
    public void filterProductsTest(){
        categoriesPage.filterProducts("Shoes","15","1000");
        Assert.assertTrue(categoriesPage.verifyFilter());
    }
    @AfterClass
    public void tearDown(){
        closeBrowser();
    }




}
