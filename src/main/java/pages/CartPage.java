package pages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.WaitUtils;

public class CartPage {
	private WebDriver driver;
    private WaitUtils waitUtils;

    private By brandFilter = By.xpath("//div[@id='brandsRefinements']//span[@data-csa-c-type='element'][1]//i");
    private By storageFilter = By.xpath("//span[text()='Storage Capacity']/ancestor::div[@role='group']//span[@data-csa-c-type='element'][1]//i");
    private By addToCart = By.xpath("//div[@role='listitem'][1]//button[text()='Add to cart']");
    private By goToCart = By.linkText("Go to Cart");
    private By verifyCartCount = By.cssSelector("#sc-subtotal-label-activecart");
    private By removeProduct = By.xpath("//div[@role='listitem'][1]//span[@class='a-icon a-icon-small-trash']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver, 10);
    }

    
    public void clickBrandFilter() {
    	waitUtils.waitForVisibility(brandFilter).click();
    }
    
    public void clickStorageFilter() {
    	waitUtils.waitForVisibility(storageFilter).click();
    }
    
    public void clickAddToCart() {
    	waitUtils.waitForVisibility(addToCart).click();
    }
    
    public void clickGoToCart() {
    	waitUtils.waitForVisibility(goToCart).click();
    	
    }
    
    public int getCartCount() {

        WebElement element = waitUtils.waitForVisibility(verifyCartCount);

        String text = element.getText();

        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return Integer.parseInt(matcher.group());
        }

        throw new RuntimeException("Product count not found: " + text);
    }
    
    public void removeProduct() {
    	waitUtils.waitForVisibility(removeProduct).click();
    }
}
