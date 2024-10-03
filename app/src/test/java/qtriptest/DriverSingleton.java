package qtriptest;

import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;


public class DriverSingleton {
    private static DriverSingleton driverSigleton;
    private RemoteWebDriver driver;
    private DriverSingleton() throws MalformedURLException{
        final DesiredCapabilities capabilities=new DesiredCapabilities();
        capabilities.setBrowserName(BrowserType.CHROME);
        driver=new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"),capabilities);
        driver.manage().window().maximize();
    }
    public static DriverSingleton getInstanceOfSingletonBrowserclass() throws MalformedURLException{
        if(driverSigleton==null){
           driverSigleton=new DriverSingleton();
        }
       return driverSigleton;
    }
    public RemoteWebDriver getDriver(){
        return driver;
    }
}