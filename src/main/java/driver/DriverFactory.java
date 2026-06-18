package driver;

import org.openqa.selenium.WebDriver;

import utils.ConfigReader;

//A DriverFactory is usually responsible for managing WebDriver objects throughout the framework lifecycle.

public class DriverFactory {
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void setDriver(WebDriver webDriver) {
        driver.set(webDriver);
        
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
