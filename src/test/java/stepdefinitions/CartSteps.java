package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import driver.DriverFactory;
import pages.CartPage;
import pages.HomePage;
import utils.LoggerUtils;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class CartSteps {
	private HomePage homePage;
    private CartPage cartPage;

    private static final Logger log = LoggerUtils.getLogger(CartSteps.class);

    // ---------------- HOME PAGE ----------------

    @Given("the user is on home page")
    public void the_user_is_on_the_home_page() {
        log.info("Launching Home Page");
        homePage = new HomePage(DriverFactory.getDriver());
    }

    // ---------------- SEARCH ----------------

    @When("the user searches for {string}")
    public void the_user_searches_for(String searchValue) {
        log.info("Searching product: " + searchValue);
        homePage.enterSearchText(searchValue);
    }

    // ---------------- FILTERS ----------------

    @When("the user applies brand filter")
    public void the_user_applies_brand_filter() {
        log.info("Applying brand filter");
        cartPage = new CartPage(DriverFactory.getDriver());
        cartPage.clickBrandFilter();
    }

    @When("the user applies storage filter")
    public void the_user_applies_storage_filter() {
        log.info("Applying storage filter");
        cartPage.clickStorageFilter();
    }

    // ---------------- ADD TO CART ----------------

    @When("the user adds the product to cart")
    public void the_user_adds_the_product_to_cart() {
        log.info("Adding product to cart");
        cartPage.clickAddToCart();
    }

    @When("the user navigates to cart page")
    public void the_user_navigates_to_cart_page() {
        log.info("Navigating to cart page");
        cartPage.clickGoToCart();
    }

    // ---------------- VERIFY CART COUNT ----------------

    @Then("the cart count should be {int}")
    public void the_cart_count_should_be(Integer expectedCount) {

        int actualCount = cartPage.getCartCount();

        log.info("Cart count fetched from UI: " + actualCount);

        Assert.assertEquals(actualCount,expectedCount,"Cart count mismatch");
    }

    // ---------------- REMOVE CART ----------------

    @Given("the user has a product in the cart")
    public void the_user_has_a_product_in_the_cart() {
        log.info("Precondition: product already exists in cart");

        homePage = new HomePage(DriverFactory.getDriver());
        cartPage = new CartPage(DriverFactory.getDriver());

    }

    @When("the user removes the product from cart")
    public void the_user_removes_the_product_from_cart() {
        log.info("Removing product from cart");
        cartPage.removeProduct();
    }

}
