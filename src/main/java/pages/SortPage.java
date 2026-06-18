package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import models.Product;
import utils.WaitUtils;

public class SortPage {

	private WebDriver driver;
    private WaitUtils waitUtils;

    private By productPrices = By.xpath("//span[@class='a-price-whole']");
    private By productNames = By.xpath("//a[@target='_blank']/h2/span");

    public SortPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver, 10);
    }
    
    public List<Product> getAllProducts() {

        List<WebElement> names = waitUtils.waitForAllElementsPresence(productNames);

        List<WebElement> prices = waitUtils.waitForAllElementsPresence(productPrices);

        List<Product> productList = new ArrayList<>();

        int size = Math.min(names.size(), prices.size());

        for (int i = 0; i < size; i++) {

            String name = names.get(i).getText();

            String priceText =prices.get(i).getText().replace(",", "").trim();

            double price = Double.parseDouble(priceText);

            productList.add(new Product(name, price));
        }

        return productList;
    }
}
