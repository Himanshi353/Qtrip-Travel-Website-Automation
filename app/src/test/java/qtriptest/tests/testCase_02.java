package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HomePage;
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


public class testCase_02 {
  static RemoteWebDriver driver;
  static ExtentReports report;
  static ExtentTest test;

  @BeforeTest
  public static void createDriver() throws MalformedURLException {
    DriverSingleton driverSigleton = DriverSingleton.getInstanceOfSingletonBrowserclass();
    driver = driverSigleton.getDriver();
  }

  @BeforeTest
  public static void createReport() throws MalformedURLException {
    ReportSingleton reportSingleton = ReportSingleton.getInstanceOfSingletonReportclass();
    report = reportSingleton.getReport();
    test = report.startTest("TestCase02");
  }

  @Test(groups={"Search and Filter flow"},priority = 2, description = "Verify that search and filters works fine",
      dataProvider = "testdata", dataProviderClass = DP.class, enabled = true)
  public static void TestCase02(String cityname, String category_filter, String duration_filter,
      String expectedfilteredresult, String expectedUnfilteredresult) throws InterruptedException, IOException {
    HomePage homePage = new HomePage(driver);
    homePage.navigateToHomepage();
    Thread.sleep(2000);
    homePage.searchCity("nowhere");
    if (homePage.isNoCityFound()) {
      // System.out.println("No City Found message successfully displayed");
      test.log(LogStatus.PASS, "No City Found message successfully displayed");
    } else {
      // System.out.println("Failed: No City Found message is not dispalyed");
      test.log(LogStatus.FAIL,test.addScreenCapture(ReportSingleton.capture(driver))+ "No City Found message is not dispalyed");
    }

    homePage.searchCity(cityname);
    if (homePage.selectCity(cityname)) {
      // System.out.println("Verified that City is displayed on auto complete");
      test.log(LogStatus.PASS, "Verified that City is displayed on auto complete");
    } else {
      // System.out.println("City auto complete Verification Failed");
      test.log(LogStatus.FAIL, test.addScreenCapture(ReportSingleton.capture(driver))+"City auto complete Verification Failed");
    }
    AdventurePage adventurePage = new AdventurePage(driver);
    WebDriverWait wait = new WebDriverWait(driver, 10);
    wait.until(ExpectedConditions.urlContains("adventures"));
    Thread.sleep(2000);

    adventurePage.setDurationFilter(duration_filter);
    Thread.sleep(2000);
    adventurePage.setCategoryFilter(category_filter);
    Thread.sleep(2000);
    if (adventurePage.getResultCount() == Integer.parseInt(expectedfilteredresult)) {
      // System.out.println("Verified that result count is as expected after selecting filter");
      test.log(LogStatus.PASS, "Verified that result count is as expected after selecting filter");
    } else {
      // System.out.println("Verification of Filtered result count is Unsuccessful");
      test.log(LogStatus.FAIL,test.addScreenCapture(ReportSingleton.capture(driver))+ "Verification of Filtered result count is Unsuccessful");
    }
    adventurePage.clearFilterOfDuration();
    adventurePage.clearCategoryFilter();
    if (adventurePage.getResultCount() == Integer.parseInt(expectedUnfilteredresult)) {
      // System.out.println("Verified that result count is as expected after clearing filter");
      test.log(LogStatus.PASS, "Verified that result count is as expected after clearing filter");
    } else {
      // System.out.println("Verification of Unfiltered result Failed");
      test.log(LogStatus.FAIL,test.addScreenCapture(ReportSingleton.capture(driver))+ "Verification of Unfiltered result Failed");
    }
    test.addScreenCapture(ReportSingleton.capture(driver));
  }

  @AfterSuite
  public static void quitDriver() {

    report.endTest(test);
    report.flush();
  }
}
