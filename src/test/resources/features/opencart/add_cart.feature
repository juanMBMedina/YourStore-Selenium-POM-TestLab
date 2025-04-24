@your_store_add_to_cart_feature
Feature: User Add Items to Cart in Your Store Page
  As a registered user,
  I want to log in to the Your Store application,
  So that to interact with shopping cart in the page.

  Background:
    Given the user is on the login page of Your Store

  @YS-9
  Scenario: Verify the login functionality for a user with valid credentials
    Given the user enters credentials with test file
    When the user submits the login form
    Then the user should see a successful login message
