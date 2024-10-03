package qtriptest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class SeleniumWrapper {
    /**
     * Check if the element is displayed before attempting to click.
     * Scroll into view before clicking on an element.
     * Return false if the element is not displayed or any other exception occurs.
     */
    public static boolean click(WebElement elementToClick, WebDriver driver) throws InterruptedException {
        try {
            if (elementToClick.isDisplayed()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elementToClick);
                elementToClick.click();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Clear the existing text on the inputBox.
     * Type in the new keys.
     */
    public static boolean sendKeys(WebElement inputBox, String keysToSend) {
        try {
            inputBox.clear();
            inputBox.sendKeys(keysToSend);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Navigate to the given URL if the current URL is not equal to the given URL.
     * After navigation, ensure that the current URL is updated as per the given URL.
     */
    public static boolean navigate(WebDriver driver, String url) {
        try {
            if (!driver.getCurrentUrl().equals(url)) {
                driver.get(url);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
