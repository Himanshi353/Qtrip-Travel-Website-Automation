package qtriptest.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage {
    String url="https://qtripdynamic-qa-frontend.vercel.app/";
    WebDriver driver;
    @FindBy(xpath = "//a[text()='Register']")
    WebElement registerbtn;
    @FindBy(xpath = "//a[text()='Login Here']")
    WebElement loginherebtn;
    @FindBy(xpath = "//a[text()='Reservations']")
    WebElement reservations;
    @FindBy(id = "autocomplete")
    WebElement searchbox;
    @FindBy(xpath ="//div[text()='Logout']")
    WebElement logoutbtn;
    @FindBy(xpath = "//ul[@id='results']/h5")
    WebElement nosearchresultText;
    @FindBy(xpath="//ul[@id='results']/a/li")
    WebElement validcityresultText;
    @FindBy(xpath = "//ul[@id='results']/a")
    WebElement searchresultLink;

    public HomePage(WebDriver driver){
    this.driver=driver;
    AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 10);
    PageFactory.initElements(factory,this);
    }
    
    public void navigateToHomepage(){
        WebDriverWait wait=new WebDriverWait(driver, 10);
        driver.get(url);
        wait.until(ExpectedConditions.urlToBe("https://qtripdynamic-qa-frontend.vercel.app/"));
    }

    public boolean validateHomepage(){
       if(driver.getCurrentUrl().equalsIgnoreCase("https://qtripdynamic-qa-frontend.vercel.app/")){
            return true;
        }
        return false;
    }

    public void clickOnRegisterButton(){
        registerbtn.click();
        WebDriverWait wait=new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.urlContains("register"));
    }

    public void clickOnLoginhereButton(){
        loginherebtn.click();
        WebDriverWait wait=new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.urlContains("login"));
    }

    public boolean isUserLoggedIn(){
       WebDriverWait wait=new WebDriverWait(driver, 10);
       wait.until(ExpectedConditions.visibilityOf(logoutbtn));
       if(logoutbtn.isDisplayed()){
        return true;
       }
       return false;
    }
    public void performLogout(){
        WebDriverWait wait=new WebDriverWait(driver, 10);
       wait.until(ExpectedConditions.visibilityOf(logoutbtn));
        logoutbtn.click();
    }

    public boolean isUserLoggedOut(){
        if(loginherebtn.isDisplayed()){
            return true;
        }
        return false;
    }
     
    public void searchCity(String cityname) throws InterruptedException{
        searchbox.clear();
        searchbox.sendKeys(cityname);  
        Thread.sleep(2000);
        //searchbox.sendKeys(Keys.SPACE);
    }
    
    public boolean isNoCityFound(){
        WebDriverWait wait=new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(nosearchresultText));
        if(nosearchresultText.getText().equals("No City found")){
            return true;
        }
        return false;
    }
    public boolean selectCity(String cityname) throws InterruptedException{
        WebDriverWait wait=new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(validcityresultText));
        wait.until(ExpectedConditions.elementToBeClickable(searchresultLink));
      
        if(validcityresultText.getText().trim().equalsIgnoreCase(cityname)){
           // System.out.println("trying clicking on cityname");
        searchresultLink.click();
        Thread.sleep(2000);
        return true;
        }
        return false;
    }
}


