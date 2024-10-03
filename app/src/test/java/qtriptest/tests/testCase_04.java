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
public class testCase_04 {
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
        test=report.startTest("TestCase04");
      }
      @Test(groups={"Reliability Flow"},priority = 4,description ="Verify that booking history can be viewed",dataProvider = "testdata",dataProviderClass = DP.class, enabled = true)
      public static void TestCase04(String username,String password,String dataset1,String dataset2,String dataset3) throws InterruptedException, IOException{
        String [] dataset1arr=dataset1.split(";");
        String [] dataset2arr=dataset2.split(";");
        String[] dataset3arr=dataset3.split(";");
        String cityname1=dataset1arr[0];
        String adventurename1=dataset1arr[1];
        String guestname1=dataset1arr[2];
        String date1=dataset1arr[3];
        String noOfperson1=dataset1arr[4];

        String cityname2=dataset2arr[0];
        String adventurename2=dataset2arr[1];
        String guestname2=dataset2arr[2];
        String date2=dataset2arr[3];
        String noOfperson2=dataset2arr[4];

        String cityname3=dataset3arr[0];
        String adventurename3=dataset3arr[1];
        String guestname3=dataset3arr[2];
        String date3=dataset3arr[3];
        String noOfperson3=dataset3arr[4];
        
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
        homepage.searchCity(cityname1);
        homepage.selectCity(cityname1);
        
       AdventurePage adventurePage=new AdventurePage(driver);
       wait.until(ExpectedConditions.urlContains("adventures"));
       Thread.sleep(2000);
       adventurePage.searchForAdventure(adventurename1);
       Thread.sleep(3000);
       AdventureDetailsPage adventuredetailspage=new AdventureDetailsPage(driver);
       adventuredetailspage.enterName(guestname1);
       Thread.sleep(2000);
       adventuredetailspage.enterDate(date1);
       Thread.sleep(2000);
       adventuredetailspage.enterPerson(noOfperson1);
       Thread.sleep(2000);
       adventuredetailspage.clickOnReserveButton();
       Thread.sleep(2000);
       if(adventuredetailspage.verifyAdventureBooking()){
        //System.out.println("Verified that adventure booking1 successfull");
        test.log(LogStatus.PASS, "Verified that adventure 1st-booking successfull");
       }
       else{
        //System.out.println("Failed: Adventure booking1 is unsuccessful");
        test.log(LogStatus.FAIL,test.addScreenCapture(ReportSingleton.capture(driver))+  "Failed: Adventure 1st-booking is unsuccessful");
       }

       homepage.navigateToHomepage();
       homepage.searchCity(cityname2);
        homepage.selectCity(cityname2);
       wait.until(ExpectedConditions.urlContains("adventures"));
       Thread.sleep(2000);
       adventurePage.searchForAdventure(adventurename2);
       Thread.sleep(3000);
       adventuredetailspage.enterName(guestname2);
       Thread.sleep(2000);
       adventuredetailspage.enterDate(date2);
       Thread.sleep(2000);
       adventuredetailspage.enterPerson(noOfperson2);
       Thread.sleep(2000);
       adventuredetailspage.clickOnReserveButton();
       Thread.sleep(2000);
       if(adventuredetailspage.verifyAdventureBooking()){
        //System.out.println("Verified that adventure booking2 successfull");
        test.log(LogStatus.PASS, "Verified that adventure 2nd-booking successfull");
       }
       else{
        //System.out.println("Failed: Adventure booking2 is unsuccessful");
        test.log(LogStatus.FAIL,test.addScreenCapture(ReportSingleton.capture(driver))+  "Failed: Adventure 2nd-booking is unsuccessful");
       }

       homepage.navigateToHomepage();
       homepage.searchCity(cityname3);
        homepage.selectCity(cityname3);
       wait.until(ExpectedConditions.urlContains("adventures"));
       Thread.sleep(2000);
       adventurePage.searchForAdventure(adventurename3);
       Thread.sleep(3000);
       adventuredetailspage.enterName(guestname3);
       Thread.sleep(2000);
       adventuredetailspage.enterDate(date3);
       Thread.sleep(2000);
       adventuredetailspage.enterPerson(noOfperson3);
       Thread.sleep(2000);
       adventuredetailspage.clickOnReserveButton();
       Thread.sleep(2000);
       if(adventuredetailspage.verifyAdventureBooking()){
        //System.out.println("Verified that adventure booking3 successfull");
        test.log(LogStatus.PASS, "Verified that adventure 3rd-booking successfull");
       }
       else{
        //System.out.println("Failed: Adventure booking3 is unsuccessful");
        test.log(LogStatus.FAIL,test.addScreenCapture(ReportSingleton.capture(driver))+  "Failed: Adventure 3rd-booking is unsuccessful");
       }
       adventuredetailspage.clicktoViewBookingHistory();
       Thread.sleep(2000);
       HistoryPage historypage=new HistoryPage(driver);
       List<String> transactionids=historypage.noteTransactionid();
       Thread.sleep(2000);
       int count=1;
       for(String tranid:transactionids){
         System.out.println("Transactionid "+count+" :"+tranid);
         count++;
       }
       if(transactionids.size()==3){
        //System.out.println("Verified that All bookings are displayed");
        test.log(LogStatus.PASS, "Verified that All the bookings are displayed");
       }
       else{
        //System.out.println("Failed: All bookings are not displayed");
        test.log(LogStatus.FAIL,test.addScreenCapture(ReportSingleton.capture(driver))+  "Failed: All bookings are not displayed");
       }
       
       homepage.performLogout();
       test.addScreenCapture(ReportSingleton.capture(driver));
       
    }
    @AfterSuite
    public static void quitDriver(){
      driver.quit();
      report.endTest(test);
      report.flush();
    }
}
