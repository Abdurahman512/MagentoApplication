package maganto.backendpages.salespages;

import maganto.utility.TestDataHolder;
import maganto.utility.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class InvoicesPage {
    WebDriver driver;
    TestUtility utility;
    Actions actions;
    SalesDashboardPage dashboardPage;
    String text;
    public InvoicesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        actions=new Actions(driver);
        utility=new TestUtility(driver);
        dashboardPage=new SalesDashboardPage(driver);
    }
    @FindAll(
            @FindBy(xpath = "//table[@id=\"sales_invoice_grid_table\"]//tbody//tr//td[9]")
    )
    List<WebElement> allInvoice;

    @FindAll(@FindBy(xpath = "//ul[@class=\"note-list\"]//li"))
    List<WebElement>  allInvoiceHistory;
    @FindBy(xpath = "//ul[@class=\"note-list\"]//li")
    WebElement addedInvoiceCommentHistory;

    @FindBy(tagName = "#history_comment")
    WebElement invoiceCommentHistoryField;
    @FindBy(xpath = "//span[text()=\"Submit Comment\"]")
    WebElement submitCommentButton;
    @FindBy(xpath = "//input[@id=\"history_visible\"]")
    WebElement visibleOnFrontendCheckBox;
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    public void viewInvoiceCommentHistory(){
        int i= (int) ((Math.random()*20)+1);
        allInvoice.get(i).click();
    }
    public boolean verifyViewInvoiceCommentHistorySuccessfully(){
        if(allInvoiceHistory.size()>=1)
            return true;
        else
            return false;
    }
    public String addInvoiceComment(String invoiceComment){
        int i= (int) ((Math.random()*20)+1);
        allInvoice.get(i).click();
        utility.waitForElementPresent(invoiceCommentHistoryField);
        setText(invoiceComment);
        utility.waitForElementPresent(visibleOnFrontendCheckBox);
        visibleOnFrontendCheckBox.click();
        utility.waitForElementPresent(submitCommentButton);
        submitCommentButton.click();
        return invoiceComment;
    }
    public boolean verifyAddInvoiceCommentSuccessfully(){
        if (addedInvoiceCommentHistory.getText().contains(getText()))
            return true;
        else return false;

    }



}
