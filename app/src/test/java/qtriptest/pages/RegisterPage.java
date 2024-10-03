package qtriptest.pages;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class RegisterPage {
  WebDriver driver;
    String url="https://qtripdynamic-qa-frontend.vercel.app/pages/register/";
    public String lastusedemailid;
    public String lastusedpassword;
    @FindBy(name="email")
    WebElement emailidtextbox;
    @FindBy(name = "password")
    WebElement paswrdtextbox;
    @FindBy(name="confirmpassword")
    WebElement confrmpwdtexbox;
    @FindBy(xpath = "//button[text()='Register Now']")
    WebElement registernowbtn;
    public RegisterPage(WebDriver driver){
          this.driver=driver;
          AjaxElementLocatorFactory factory=new AjaxElementLocatorFactory(driver, 10);
          PageFactory.initElements(factory, this);
    }
    public void navigateToRegisterpage(){
      driver.get(url);
    }
    public boolean validateRegisterPage(){
      if(driver.getCurrentUrl().contains("register")){
        return true;
      }
      return false;
    }
    public void registerUser(String emailid,String password,boolean makedynamic){
      String testUsername="";
        if(makedynamic){
          testUsername=emailid.split("@")[0];
          emailid=testUsername+UUID.randomUUID().toString()+"@gmail.com"; 
        }
        emailidtextbox.sendKeys(emailid);
        this.lastusedemailid=emailid;
        paswrdtextbox.sendKeys(password);
        confrmpwdtexbox.sendKeys(password);
        this.lastusedpassword=password;
        registernowbtn.click();
    }

}
