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

  @YS-9 @regression_test
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
    Then the user is on the Wish List page
    Then the user should see the selected item in the Wish List

  @YS-11 @regression_test
  Scenario Outline: Validate the removal of a favorite product
    And the user searches for an item in the navigation bar
    # If the option doesn't have a subcategory, you must add "N/A" (default value) text or remove the subcategory column
      | category   | subcategory   | itemName   |
      | <category> | <subcategory> | <itemName> |
    And the user clicks the "<page>" link for the item
    And the user should see a successful item added to the Wish List message
    Given the user is on the Wish List page
    When the user clicks the "<action>" link for the item called "<itemName>" in Wish List page
    Then the user should see a message confirming the successful removal from the Wish List

    Examples:
      | category | subcategory | itemName                | page             | action |
      | Tablets  | N/A         | Samsung Galaxy Tab 10.1 | Add to Wish List | Remove |

  @YS-12
  Scenario: Validate product load into the shopping cart
    Given the user searches for an item in the navigation bar
      # If the option doesn't have subcategory you must add N/A (default value) text or delete subcategory column
      | category | subcategory | itemName |
      | Desktops | Mac         | iMac     |
    When the user clicks the "Add to Cart" link for the item
    Then the user should see a successful Add to Cart item message
    Then the user is on the add to Cart Page
    Then the user should see the selected item in the Add to Cart

  @YS-13 @regression_test
  Scenario Outline: Verify the product was removed from the shopping cart
    Given the user searches for an item in the navigation bar
      # If the option doesn't have subcategory you must add N/A (default value) text or delete subcategory column
      | category   | subcategory   | itemName   |
      | <category> | <subcategory> | <itemName> |
    When the user clicks the "<page>" link for the item
    Then the user should see a successful Add to Cart item message
    Given the user is on the add to Cart Page
    When the user clicks the "<action>" link for the item called "<itemName>" in Add to Cart page
    Then the user should see a message confirming the successful removal from the Add to Cart

    Examples:
      | category | subcategory | itemName | page        | action |
      | Desktops | Mac         | iMac     | Add to Cart | Remove |

  @YS-16
  Scenario: Validate error message when select wrong option
    When the user searches for an item in the navigation bar
    # If the option doesn't have a subcategory, you must add "N/A" (default value) text or remove the subcategory column
      | category | subcategory | itemName                |
      | Tablets  | N/A         | Samsung Galaxy Tab 10.1 |
    Then the user clicks the "Wrong Option" link for the item and should see exception option

  @YS-16
  Scenario Outline: Validate error message when select wrong option
    Given the user searches for an item in the navigation bar
      # If the option doesn't have subcategory you must add N/A (default value) text or delete subcategory column
      | category   | subcategory   | itemName   |
      | <category> | <subcategory> | <itemName> |
    When the user clicks the "<page>" link for the item
    Then the user should see a successful Add to Cart item message
    Given the user is on the add to Cart Page
    Then the user clicks the "<action>" link for the item called "<itemName>" in Add to Cart page and should message error

    Examples:
      | category | subcategory | itemName | page        | action       |
      | Desktops | Mac         | iMac     | Add to Cart | Wrong Option |