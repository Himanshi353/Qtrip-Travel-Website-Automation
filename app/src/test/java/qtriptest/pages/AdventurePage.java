package qtriptest.pages;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdventurePage {
    String url="https://qtripdynamic-qa-frontend.vercel.app/pages/adventures/";
    WebDriver driver;
    List<String> durationoptionsList=new ArrayList<>();

    @FindBy(xpath="//select[@id='duration-select']")
    WebElement durationdropdown;
    @FindBy(xpath="//select[@id='category-select']")
    WebElement categorydropdown;
    @FindBy(id="search-adventures")
    WebElement searchadventureSearchBox;
    @FindBy(xpath = "//select[@id='duration-select']/option/following-sibling::option")
    List<WebElement> durationOptions;
    @FindBy(xpath="//select[@id='category-select']/option/following-sibling::option")
    List<WebElement> categoryOptions;
    @FindBy(xpath="//div[@class='col-6 col-lg-3 mb-4']")
    List<WebElement> results;
    @FindBy(xpath = "(//div[@class='ms-3'])[1]")
    WebElement cleardurationfilter;
    @FindBy(xpath="(//div[@class='ms-3'])[2]")
    WebElement clearcategoryfilter;
    @FindBy(xpath="//div[@class='activity-card-text text-md-center w-100 mt-3']/div[1]/h5")
    List<WebElement> adventuresText;
    @FindBy(id = "search-adventures")
    WebElement searchadventure;
    @FindBy(xpath="//div[@class='col-6 col-lg-3 mb-4']/a")
    WebElement adventureLink;

    
    
    public AdventurePage(WebDriver driver){
        this.driver=driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(factory,this);
    }
   
    public void setDurationFilter(String durationFilter){
        Select selectduration=new Select(durationdropdown);
        WebDriverWait wait=new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(durationdropdown));
        durationdropdown.click();
        for(WebElement option:durationOptions){
            if(option.getText().equalsIgnoreCase(durationFilter)){
              selectduration.selectByVisibleText(durationFilter);
            }  
        }     
    }
    public void setCategoryFilter(String category_filter){
        Select selectcategory=new Select(categorydropdown);
        WebDriverWait wait=new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(categorydropdown));
        categorydropdown.click();
        for(WebElement option:categoryOptions){
            if(option.getText().equalsIgnoreCase(category_filter)){
                selectcategory.selectByVisibleText(category_filter);
            }
        }
    }
    public int getResultCount(){
        return results.size();
    }
    public void clearFilterOfDuration(){
      cleardurationfilter.click();
    }
    public void clearCategoryFilter(){
        clearcategoryfilter.click();
    }

    public void searchForAdventure(String adventurename) throws InterruptedException{
    WebDriverWait wait=new WebDriverWait(driver, 10);
    searchadventure.sendKeys(adventurename);

    wait.until(ExpectedConditions.visibilityOf(adventureLink));
    //  for(WebElement ele:adventuresText){
    //     if(ele.getText().equalsIgnoreCase(adventurename)){
    //         ele.click();
    //     }
    //  }
    Thread.sleep(2000);
    adventureLink.click();
    Thread.sleep(2000);
    wait.until(ExpectedConditions.urlContains("detail"));
    }
    
}

