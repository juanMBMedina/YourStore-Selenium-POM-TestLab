@your_store_add_to_cart_feature
Feature: User Add Items to Cart in Your Store Page
  As a registered user,
  I want to log in to the Your Store application,
  So that to interact with shopping cart in the page.

  Background:
    Given the user is on the login page of Your Store
    And the user enters credentials with test file
    And the user submits the login form
    And the user should see a successful login message

  @YS-9
  Scenario: Validate the correct display of the product comparison message
    Given the user searches for an item in the navigation bar
      # If the option doesn't have subcategory you must add N/A (default value) text or delete subcategory column
      | category | subcategory | itemName |
      | Desktops | Mac         | iMac     |
    When the user clicks the "Compare this Product" link for the item
    Then the user should see a successful comparison item message

  @YS-10
  Scenario: Validate the selection of favorite product
    Given the user searches for an item in the navigation bar
    # If the option doesn't have a subcategory, you must add "N/A" (default value) text or remove the subcategory column
      | category | subcategory | itemName                |
      | Tablets  | N/A         | Samsung Galaxy Tab 10.1 |
    When the user clicks the "Add to Wish List" link for the item
    Then the user should see a successful item added to the Wish List message
    Then the user should see the selected item in the Wish List

