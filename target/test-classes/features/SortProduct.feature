@Sort
Feature: Sort products by price

  As a customer
  I want to search for products
  So that I can identify the cheapest and most expensive products

  Scenario Outline: View lowest and highest priced products after search
    Given the user is on the home page
    When user searches for "<searchValue>"
    Then matching products should be displayed

    When the user sorts the products by price from low to high
    Then the product list should contain at least one product
    And the lowest priced product should be displayed
    And the highest priced product should be displayed

    Examples:
      | searchValue |
      | iphone      |