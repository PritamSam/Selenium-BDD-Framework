@Cart
Feature: Cart Functionality

  As a user
  I want to add and remove products from cart
  So that I can validate cart operations

  Scenario Outline: Add product to cart and verify cart count
    Given the user is on home page
    When the user searches for "<searchValue>"
    And the user applies brand filter
    And the user applies storage filter
    And the user adds the product to cart
    And the user navigates to cart page
    Then the cart count should be 1

    Examples:
      | searchValue |
      | iphone      |

  Scenario: Remove product from cart and verify cart count
    Given the user has a product in the cart
    When the user removes the product from cart
    Then the cart count should be 0
