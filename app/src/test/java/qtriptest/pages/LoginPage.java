package qtriptest.pages;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocator;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage {
    WebDriver driver;
    String url="https://qtripdynamic-qa-frontend.vercel.app/pages/login/";
    @FindBy(name="email")
    WebElement emailtextbox;
    @FindBy(name="password")
    WebElement passwordtextbox;
    @FindBy(xpath = "//button[text()='Login to QTrip']")
    WebElement logintoqtripBtn;
    public LoginPage(WebDriver driver){
        this.driver=driver;
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        AjaxElementLocatorFactory factory=new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(factory, this);
    }
    public void navigateToLoginpage(){
        driver.get(url);
    }
    public boolean validateLoginpage(){
        if(driver.getCurrentUrl().contains("login")){
            return true;
        }  
      return false;
    }
    public void performLogin(String emailid,String password) throws InterruptedException{
        emailtextbox.sendKeys(emailid);
        Thread.sleep(2000);
        passwordtextbox.sendKeys(password);
        Thread.sleep(2000);
        logintoqtripBtn.click();
    }
}
