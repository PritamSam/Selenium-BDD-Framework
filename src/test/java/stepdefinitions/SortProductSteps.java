package stepdefinitions;

import java.util.Comparator;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Product;
import pages.HomePage;
import pages.SortPage;
import utils.LoggerUtils;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import driver.DriverFactory;


public class SortProductSteps {
    private HomePage homePage;
    private SortPage sortPage;
    private List<Product> products;

    private static final Logger log =
            LoggerUtils.getLogger(SortProductSteps.class);

    // ------------------- GIVEN -------------------

    @Given("the user is on the home page")
    public void the_user_is_on_the_home_page() {

        log.info("Launching Home Page");

        homePage = new HomePage(DriverFactory.getDriver());
    }

    // ------------------- WHEN SEARCH -------------------

    @When("user searches for {string}")
    public void the_user_searches_for(String searchValue) {

        log.info("Searching for product: " + searchValue);

        homePage.enterSearchText(searchValue);
    }

    // ------------------- THEN SEARCH VALIDATION -------------------

    @Then("matching products should be displayed")
    public void matching_products_should_be_displayed() {

        sortPage = new SortPage(DriverFactory.getDriver());

        products = sortPage.getAllProducts();

        log.info("Products found: " + products.size());

        Assert.assertTrue(products.size() > 0,
                "No matching products found after search");
    }

    // ------------------- WHEN SORT -------------------

    @When("the user sorts the products by price from low to high")
    public void the_user_sorts_the_products_by_price_low_to_high() {

    	log.info("Sorting products by price (Low to High)"); 
    	
    	products.sort(Comparator.comparing(Product::getPrice));
    }

    // ------------------- THEN LIST NOT EMPTY -------------------

    @Then("the product list should contain at least one product")
    public void the_product_list_should_contain_at_least_one_product() {

        Assert.assertNotNull(products, "Product list is null");

        Assert.assertTrue(products.size() > 0,
                "Product list is empty");
    }

    // ------------------- THEN LOWEST PRODUCT -------------------

    @Then("the lowest priced product should be displayed")
    public void the_lowest_priced_product_should_be_displayed() {

        Product lowest = products.get(0);

        Assert.assertNotNull(lowest, "Lowest product not found");

        System.out.println("Lowest Price Product:");
	    System.out.println("Lowest Product Detail - "+lowest.getName());
	    System.out.println("Lowest Product Price - "+lowest.getPrice());
    }

    // ------------------- THEN HIGHEST PRODUCT -------------------

    @Then("the highest priced product should be displayed")
    public void the_highest_priced_product_should_be_displayed() {

        Product highest = products.get(products.size() - 1);

        Assert.assertNotNull(highest, "Highest product not found");

	    System.out.println("Highest Price Product:");
	    System.out.println("Highest Product Detail - "+highest.getName());
	    System.out.println("Highest Prodcut Price - "+highest.getPrice());
    }
}
