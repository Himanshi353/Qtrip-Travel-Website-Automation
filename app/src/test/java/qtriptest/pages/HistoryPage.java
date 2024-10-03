
package qtriptest.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HistoryPage {
    WebDriver driver;
    @FindBy(xpath="//tbody[@id='reservation-table']/tr/th")
    List<WebElement> transactionid;
    @FindBy(className = "cancel-button")
    WebElement cancelbutton ;
    @FindBy(id ="no-reservation-banner")
    WebElement noreservation ;
    public HistoryPage(WebDriver driver){
        this.driver=driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(factory,this);
    }
    public List<String> noteTransactionid(){
        List<String> transcationIds=new ArrayList<>();
        for(WebElement tranid:transactionid){
        transcationIds.add(tranid.getText());
        }
        return transcationIds;
    }
    public void cancelResrvation(){
        cancelbutton.click();
    }
    public void refreshPage(){
        driver.navigate().refresh();
    }
    public boolean checkTransactionidRemoved(){
        if(noreservation.isDisplayed())
        return true;
        return false;
    }
    
}