package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HistoryPage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testCase_03 {
    static RemoteWebDriver driver;
    static ExtentReports report;
    static ExtentTest test;
    @BeforeTest
    public static void createDriver() throws MalformedURLException{
       DriverSingleton driverSigleton=DriverSingleton.getInstanceOfSingletonBrowserclass();
       driver=driverSigleton.getDriver();
      }
      @BeforeTest
      public static void createReport() throws MalformedURLException{
        ReportSingleton reportSingleton=ReportSingleton.getInstanceOfSingletonReportclass();
        report=reportSingleton.getReport();
        test=report.startTest("TestCase03");
      }
      @Test(groups={"Booking and Cancellation Flow"},priority = 3,description ="Verify adventure booking and cancellation",dataProvider = "testdata",dataProviderClass = DP.class, enabled = true)
      public static void TestCase03(String username,String password,String cityname,String adventurename,String guestname,String date,String noOfpersons) throws InterruptedException, IOException{
        HomePage homepage=new HomePage(driver);
        homepage.navigateToHomepage();
        homepage.clickOnRegisterButton();
        RegisterPage registerpage=new RegisterPage(driver);
        registerpage.registerUser(username, password, true);
        WebDriverWait wait=new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.urlContains("login"));
        LoginPage loginpage=new LoginPage(driver);
        loginpage.performLogin(registerpage.lastusedemailid, registerpage.lastusedpassword);
        Thread.sleep(3000);
        homepage.searchCity(cityname);
        homepage.selectCity(cityname);
        
       AdventurePage adventurePage=new AdventurePage(driver);
       wait.until(ExpectedConditions.urlContains("adventures"));
       Thread.sleep(2000);
       adventurePage.searchForAdventure(adventurename);
       Thread.sleep(3000);
       AdventureDetailsPage adventuredetailspage=new AdventureDetailsPage(driver);
       adventuredetailspage.enterName(guestname);
       Thread.sleep(2000);
       adventuredetailspage.enterDate(date);
       Thread.sleep(2000);
       adventuredetailspage.enterPerson(noOfpersons);
       Thread.sleep(2000);
       adventuredetailspage.clickOnReserveButton();
       Thread.sleep(2000);
       if(adventuredetailspage.verifyAdventureBooking()){
        //System.out.println("Verified that adventure booking successfull");
        test.log(LogStatus.PASS, "Verified that adventure booking successfull");
       }
       else{
        //System.out.println("Failed: Adventure booking is unsuccessful");
        test.log(LogStatus.FAIL,test.addScreenCapture(ReportSingleton.capture(driver))+  "Failed: Adventure booking is unsuccessfull");
       }
       adventuredetailspage.clicktoViewBookingHistory();
       Thread.sleep(2000);
       HistoryPage historypage=new HistoryPage(driver);
       List<String> transactionid=historypage.noteTransactionid();
       Thread.sleep(2000);
       System.out.println("transaction id : " +transactionid.get(0));
       historypage.cancelResrvation();
       Thread.sleep(2000);
       historypage.refreshPage();
       Thread.sleep(2000);
       if(historypage.checkTransactionidRemoved()){
        //System.out.println("Transaction id succussfully removed after cancelation");
        test.log(LogStatus.PASS, "Transaction id succussfully removed after cancelation");
       }
       else{
        //System.out.println("Transaction id is not removed after cancelation");
        test.log(LogStatus.FAIL, test.addScreenCapture(ReportSingleton.capture(driver))+ "Transaction id is not removed after cancelation");
       }
       homepage.performLogout();
       test.addScreenCapture(ReportSingleton.capture(driver));
    }
    @AfterSuite
    public static void quitDriver(){
      
      report.endTest(test);
      report.flush();
    }
}
