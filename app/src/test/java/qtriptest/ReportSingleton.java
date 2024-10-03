package qtriptest;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import com.relevantcodes.extentreports.ExtentReports;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
public class ReportSingleton {
    private static ReportSingleton reportsingleton;
    private static ExtentReports report;

    private ReportSingleton() {
       report = new ExtentReports(System.getProperty("user.dir") + "/ExtentReportsResult_"+getTimeStamp()+".html");
       report.loadConfig(new File("extent_customization_configs.xml"));
        
    }

    public static ReportSingleton getInstanceOfSingletonReportclass() {
        if (reportsingleton == null) {
            reportsingleton = new ReportSingleton();
        }
        return reportsingleton;
    }

    public ExtentReports getReport() {
        return report;
    }
    public static String getTimeStamp(){
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        return String.valueOf(timestamp.getTime());
    }
    public static String capture(WebDriver driver) throws IOException{
        File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File dest=new File(System.getProperty("user.dir")+"/QTripImages/"+ System.currentTimeMillis()+".png");
        String filepath=dest.getAbsolutePath();
        FileUtils.copyFile(src, dest);
        return filepath;
    }
}