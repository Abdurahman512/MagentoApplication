package testng;

import maganto.backendpages.BackEndLogin;
import maganto.backendpages.catalogpages.CategoriesPage;
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
    final static String configFile = "config.properties";

    @BeforeClass
    public void setUp(ITestContext context) {
        browserSetUp(ApplicationConfig.readFromConfigProperties(configFile, "backendurl"));
        BackEndLogin login=new BackEndLogin(driver);
        login.catalogPageLogin();
        categoriesPage = new CategoriesPage(driver);
        utility = new TestUtility(driver);
        context.setAttribute("driver",driver);
    }
    @Test
    public void addRootCategoryTest(){
        categoriesPage.addRootCategories("TastData/CategoryTestData.xlsx","Sheet1",1,0);
        Assert.assertTrue(categoriesPage.isRootCategoryAdded());
    }
    @Test(dependsOnMethods ={"addRootCategoryTest"})
    public void addSubCategoryTest(){
        categoriesPage.addSubCategories("TastData/CategoryTestData.xlsx","Sheet1",1,1);
        Assert.assertTrue(categoriesPage.isSubCategoryAdded());
    }
    @Test(dependsOnMethods ={"addSubCategoryTest"})
    public void editRootCategoryTest(){
        categoriesPage.editRootCategory("TastData/CategoryTestData.xlsx","Sheet1",1,2);
        Assert.assertTrue(categoriesPage.isRootCategoryEdited());
    }
    @Test(dependsOnMethods ={"editRootCategoryTest"})
    public void editSubCategoryTest(){
        categoriesPage.editSubCategory("TastData/CategoryTestData.xlsx","Sheet1",1,2);
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






    @AfterClass
    public void tearDown(){
        closeBrowser();
    }


}
