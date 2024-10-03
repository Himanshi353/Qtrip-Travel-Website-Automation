package qtriptest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdventureDetailsPage {
    String url="https://qtripdynamic-qa-frontend.vercel.app/pages/adventures/detail/";
    WebDriver driver;
    @FindBy(name="name")
    WebElement nametextbox;
    @FindBy(name="date")
    WebElement datetextbox;
    @FindBy(name="person")
    WebElement persontextbox;
    @FindBy(xpath = "//button[text()='Reserve']")
    WebElement reservebutton;
    @FindBy(id="reserved-banner")
    WebElement bookingmessage;
    @FindBy(xpath="//div[@id='reserved-banner']/a")
    WebElement viewbookinghistory;
   

    public AdventureDetailsPage(WebDriver driver){
    this.driver=driver;
    AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 10);
    PageFactory.initElements(factory,this);
    }
    public void enterName(String name){
        WebDriverWait wait=new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(nametextbox));
        nametextbox.sendKeys(name);
    }
    public void enterDate(String date){
        WebDriverWait wait=new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(datetextbox));
        datetextbox.sendKeys(date);
    }
    public void enterPerson(String person){
        WebDriverWait wait=new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(persontextbox));
        persontextbox.clear();
        persontextbox.sendKeys(person);
        
    }
    public void clickOnReserveButton(){
        WebDriverWait wait=new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(reservebutton));
        reservebutton.click();
    }
    public boolean verifyAdventureBooking(){
        if(bookingmessage.isDisplayed()){
            return true;
        }
        return false;
    }
    public void clicktoViewBookingHistory(){
        viewbookinghistory.click();
        WebDriverWait wait=new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlContains("reservations"));
    }
}