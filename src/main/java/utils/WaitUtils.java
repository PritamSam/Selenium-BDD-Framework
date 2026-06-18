package utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

	private WebDriver driver;
    private WebDriverWait wait;

    // Constructor
    public WaitUtils(WebDriver driver, long timeoutInSeconds) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    }

    // ---------------------------
    // WAIT FOR VISIBILITY
    // ---------------------------
    public WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    // ---------------------------
    // WAIT FOR CLICKABLE
    // ---------------------------
    public WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitForClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // ---------------------------
    // WAIT FOR PRESENCE
    // ---------------------------
    public WebElement waitForPresence(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public List<WebElement> waitForAllElementsPresence(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
    
    // ---------------------------
    // WAIT FOR CLICK + CLICK
    // ---------------------------
    public void clickWhenReady(By locator) {
        waitForClickable(locator).click();
    }

    public void clickWhenReady(WebElement element) {
        waitForClickable(element).click();
    }

    // ---------------------------
    // WAIT FOR TEXT
    // ---------------------------
    public boolean waitForText(By locator, String text) {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    // ---------------------------
    // WAIT FOR ALERT
    // ---------------------------
    public void waitForAlertAndAccept() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    // ---------------------------
    // PAGE TITLE
    // ---------------------------
    public boolean waitForTitleContains(String title) {
        return wait.until(ExpectedConditions.titleContains(title));
    }  

}
