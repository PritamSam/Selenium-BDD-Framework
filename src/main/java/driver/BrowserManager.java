package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

//A BrowserManager is usually responsible for creating and configuring browser instances.

public class BrowserManager {
	   public WebDriver createDriver(String browserName) {

	        WebDriver driver;

	        switch (browserName.toLowerCase()) {

	            case "chrome":
	                ChromeOptions chromeOptions = new ChromeOptions();
	                driver = new ChromeDriver(chromeOptions);
	                break;

	            case "firefox":
	                FirefoxOptions firefoxOptions = new FirefoxOptions();
	                driver = new FirefoxDriver(firefoxOptions);
	                break;

	            case "edge":
	                driver = new EdgeDriver();
	                break;

	            default:
	                throw new RuntimeException("Browser not supported: " + browserName);
	        }

	        return driver;
	    }
}
