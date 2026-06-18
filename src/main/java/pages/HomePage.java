package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.WaitUtils;

public class HomePage {
	private WebDriver driver;
    private WaitUtils waitUtils;

    private By searchfield = By.id("twotabsearchtextbox");;
    
    
    public HomePage(WebDriver driver)
    {
    	this.driver = driver;
        this.waitUtils = new WaitUtils(driver, 10);
    }
    
    public void enterSearchText(String text)
    {
    	   WebElement element = waitUtils.waitForVisibility(searchfield);
    	    element.sendKeys(text);
    	    element.sendKeys(Keys.ENTER);    	
    }

}
