package maganto.frontendpages;

import maganto.utility.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WishListPage {

    // view wishlist
    WebDriver driver;

    @FindBy(xpath = "//span[@class='label' and text()='Account']")
    WebElement accountLink;

    @FindBy(xpath = "(//a[contains(text(),'My Wishlist')])[2]")
    WebElement myWishlist;

    @FindBy(xpath = "//h1[text()='My Wishlist']")
    WebElement myWishListPage;
    TestUtility utility;

    public WishListPage(WebDriver driver){
        this.driver=driver;
        utility=new TestUtility(driver);
        PageFactory.initElements(driver,this);
    }
    public void viewMyWishList(){
        utility.waitForElementPresent(myWishlist);
        myWishlist.click();
    }

    public boolean isMyWishListAbleToView(){
        utility.waitForElementPresent(myWishListPage);
        if (myWishListPage.isDisplayed())
            return true;
        else return false;
    }

}
