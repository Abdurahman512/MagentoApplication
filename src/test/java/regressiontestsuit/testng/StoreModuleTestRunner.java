package regressiontestsuit.testng;
import maganto.backendpages.BackEndLogin;
import maganto.backendpages.storepages.*;
import maganto.utility.ApplicationConfig;
import maganto.utility.TestBase;
import maganto.utility.TestResultListener;
import maganto.utility.TestUtility;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;


@Listeners(TestResultListener.class)
public class StoreModuleTestRunner extends TestBase {
    StoreOrdersPage storeOrdersPage;
    StoreDashboardPage storeDashboardPage;
    StoreProductPage storeProductPage;
    StoreViewPage storeViewPage;
    StoreWebsitePage storeWebsitePage;
    StorePage storePage;
    StoreProductCategoriesPage storeProductCategoriesPage;
    TestUtility utility;
    final static String configFile = "config.properties";
    BackEndLogin backEndLogin;

    @BeforeClass
    public void setUp(ITestContext context){
        browserSetUp(ApplicationConfig.readFromConfigProperties(configFile, "backendurl"));
        utility = new TestUtility(driver);
        context.setAttribute("driver",driver);
        storeOrdersPage=new StoreOrdersPage(driver);
        storeDashboardPage=new StoreDashboardPage(driver);
        storeViewPage=new StoreViewPage(driver);
        storeProductPage=new StoreProductPage(driver);
        backEndLogin=new BackEndLogin(driver);
        backEndLogin.storePageLogin();
        storeProductCategoriesPage=new StoreProductCategoriesPage(driver);
        storeWebsitePage=new StoreWebsitePage(driver);
        storePage=new StorePage(driver);
    }

    @Test(dataProvider = "productData", groups = "regression test", description = "add product",priority = 1)
    public void addProduct(String name, String description, String shortDescription, String sku,
                           String weight, String price){
        storeDashboardPage.clickOnCatalogLink();
        storeProductPage.addProductsMethod(name, description, shortDescription, sku, weight, price);
        Assert.assertTrue(storeProductPage.confirmationProductAdded());
    }
    @Test(dataProvider = "productUpdate",groups = "regression test", description = "update product",dependsOnMethods = {"addProduct"})
    public void updateProduct(String name,String description){
        storeProductPage.updateProductMethod(name, description);
        Assert.assertTrue(storeProductPage.confirmationProductAdded());
    }
    @Test(groups = "regression test",description = "delete product",dependsOnMethods = {"updateProduct"})
    public void deleteProduct(){
        storeProductPage.deleteProductMethod();
        Assert.assertTrue(storeProductPage.confirmationProductDeleted());
    }



    @Test(description = "createorder",priority = 2)
    @Ignore
    public void createOrderTest(){
        storeOrdersPage.createNewOrderMethod();
        Assert.assertTrue(storeOrdersPage.verifyCreateNewOrder());
    }
    @Test(dependsOnMethods = {"createOrderTest"})
    @Ignore
    public void updateOrderTest(){
        storeOrdersPage.updateOrder();
        Assert.assertTrue(storeDashboardPage.orderSuccessfullyCreated());
    }
    @Test(dependsOnMethods = {"updateOrderTest"})
    @Ignore
    public void cancelOrders(){
        storeOrdersPage.cancelOrder();
        Assert.assertTrue(storeOrdersPage.deleteOrderSuccessfully());
    }


    @Test(description = "addcatalog",priority = 3)
    public void addProductCatalog(){
        storeProductCategoriesPage.addProductCatalog();
        Assert.assertTrue(storeProductCategoriesPage.verifyAddedProductCatalog());
    }

    @Test(dependsOnMethods = "addProductCatalog")
    public void updateProductCatalog(){
        storeProductCategoriesPage.updateProductCatalog();
        Assert.assertTrue(storeProductCategoriesPage.verifyUpdatedProductCatalog());
    }

    @Test(dependsOnMethods = "updateProductCatalog")
    public void deleteProductCatalog(){
        storeProductCategoriesPage.deleteProductCatalog();
        Assert.assertTrue(storeProductCategoriesPage.verifyDeletedProductCatalog());
    }


    @Test(description = "createstoreview",priority = 4)
    public void createStoreView(){
        storeViewPage.createStoreView();
        Assert.assertTrue(storeViewPage.verifyStoreViewSaved());

    }

    @Test(priority = 5)
    public void editStoreView(){
        storeViewPage.editStoreView();
        Assert.assertTrue(storeViewPage.verifyStoreViewEdit());

    }
    @Test(priority = 6)
    public void viewAllStore(){
        storeViewPage.viewAllStore();
        Assert.assertTrue(storeViewPage.verifyViewAllStore());
    }

    @Test(priority = 7)
    public void addNewWebsite(){
        storeWebsitePage.CreateNewWepsite();
        Assert.assertTrue(storeWebsitePage.VerifySuccessfulMessage());
    }

    @Test(dependsOnMethods = {"addNewWebsite"})
    public void updatedNewWebsite(){
        storeWebsitePage.editWepsiye();
        Assert.assertTrue(storeWebsitePage.VerifyEditWepsiteMessage());
    }
    @Test(dependsOnMethods = "updatedNewWebsite")
    public void DeletedNewWebsite(){
        storeWebsitePage.DeletedWepsite();
        Assert.assertTrue(storeWebsitePage.DeletedWepsiteMessage());
    }

    @Test(description = "Store Manager can create a store",priority = 8)
    public void createStoreTest(){
        storePage.createStore();
        Assert.assertTrue(storePage.addStoreSuccessfullyMessage());
    }

    @Test(dependsOnMethods = "createStoreTest")
    public void editStoreTest(){
        storePage.editStore();
        Assert.assertTrue(storePage.editStoreSuccessfullyMessage());
    }

    @Test(dependsOnMethods = "editStoreTest")
    public void deleteStoreTest(){
        storePage.deleteStore();
        Assert.assertTrue(storePage.deleteStoreSuccessfullyMessage());
    }


    @AfterClass
    public void tearDown() {
        closeBrowser();
    }

    @DataProvider
    public Object[][] productData(){
        Object[][] data=new Object[][]{
                {"Ring","Diamond Ring 40kr ",
                        "Diamond Ring",utility.generateZipCode(),"15","1200"},
        };
        return data;
    }
    @DataProvider
    public Object[][] productUpdate(){
        Object[][] data2=new Object[][]{
                {"Ring",utility.generateZipCode()},
        };
        return data2;
    }

}


