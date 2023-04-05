package maganto.frontendpages;

import maganto.utility.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
public class WishListPage {

    // view wishlist
    WebDriver driver;

    @FindBy(xpath = "//span[@class='label' and text()='Account']")
    WebElement accountLink;

    @FindBy(linkText = "My Wishlist (1 item)")
    WebElement myWishlist;

    @FindBy(xpath = "//h1[text()='My Wishlist']")
    WebElement myWishListPage;


    TestUtility utility;



    public void viewMyWishList(){
        utility.waitForElementPresent(accountLink);
        accountLink.click();
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
