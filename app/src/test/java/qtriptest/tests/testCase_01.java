package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.io.IOException;
import java.net.MalformedURLException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testCase_01 {
    static RemoteWebDriver driver;
    static ExtentReports report;
    static ExtentTest test;
    @BeforeTest
    public static void createDriver() throws MalformedURLException{
       DriverSingleton driverSigleton=DriverSingleton.getInstanceOfSingletonBrowserclass();
       driver=driverSigleton.getDriver();
       System.out.println("driver created");

      }
      @BeforeTest
      public static void createReport() throws MalformedURLException{
        ReportSingleton reportSingleton=ReportSingleton.getInstanceOfSingletonReportclass();
        report=reportSingleton.getReport();
        test=report.startTest("TestCase01");
      }

    @Test(groups={"Login Flow"},priority = 1,description ="Verify user registration - login - logout",dataProvider = "testdata",dataProviderClass = DP.class, enabled = true)
    public static void TestCase01(String username,String password) throws InterruptedException, IOException{
        HomePage homepage=new HomePage(driver);
        homepage.navigateToHomepage();
        homepage.clickOnRegisterButton();
        RegisterPage registerpage=new RegisterPage(driver);
        boolean status=registerpage.validateRegisterPage();
        if(status){
            test.log(LogStatus.PASS,"Successfully navigated to Registerpage");
        }
        else{
            test.log(LogStatus.FAIL,test.addScreenCapture(ReportSingleton.capture(driver))+"Failed to navigate to Registerpage");
        }
        registerpage.registerUser(username, password, true);
        WebDriverWait wait=new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.urlContains("login"));
        LoginPage loginpage=new LoginPage(driver);
        if(loginpage.validateLoginpage()){
            //System.out.println("Successfully navigated to Loginpage");
            test.log(LogStatus.PASS,"Successfully navigated to Loginpage");
        }
        else{
           // System.out.println("Failed to navigate to Loginpage");
           test.log(LogStatus.FAIL,test.addScreenCapture(ReportSingleton.capture(driver))+"Failed to navigate to Loginpage");
        }
        //System.out.println("emailid: "+registerpage.lastusedemailid+"password: "+registerpage.lastusedpassword);
            
            loginpage.performLogin(registerpage.lastusedemailid, registerpage.lastusedpassword);
            Thread.sleep(3000);
            if(homepage.isUserLoggedIn()){
                //System.out.println("Login successfull");
                test.log(LogStatus.PASS,"Login successfull");
            }
            else{
                //System.out.println("Login Failed");
                test.log(LogStatus.FAIL,test.addScreenCapture(ReportSingleton.capture(driver))+"Login Failed");
            }
        homepage.performLogout();
        if(homepage.isUserLoggedOut()){
            //System.out.println("Successfully Logged out");
            test.log(LogStatus.PASS,"Successfully Logged out");
        }
        else{
            //System.out.println("Logout Failed"); 
            test.log(LogStatus.FAIL,test.addScreenCapture(ReportSingleton.capture(driver))+"Logout Failed");
            
        }
        test.addScreenCapture(ReportSingleton.capture(driver));
    }

    @AfterSuite
    public static void quitDriver(){
        
        report.endTest(test);
        report.flush();
    }
    
}
