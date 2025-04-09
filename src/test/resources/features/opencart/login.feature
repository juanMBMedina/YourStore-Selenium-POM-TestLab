@your_store_login_feature
Feature: User Login to Your Store
  As a registered user,
  I want to log in to the Your Store application,
  So that I can access my account.

  Background:
    Given the user is on the login page of Your Store

  @YS-5
  Scenario: Verify the login functionality for a user with valid credentials
    Given the user enters credentials with username "email122321@gmail.com" and password "clave1"
    When the user submits the login form
    Then the user should see a successful login message

  @YS-8
  Scenario: Validate the logout functionality for a user.
    Given the user enters credentials with username "email122321@gmail.com" and password "clave1"
    And the user submits the login form
    And the user should see a successful login message
    When the user can do logout by Top Bar option
    Then the user should see a successful logout message

  @YS-8
  Scenario: Validate the logout functionality for a user.
    Given the user enters credentials with username "email122321@gmail.com" and password "clave1"
    And the user submits the login form
    And the user should see a successful login message
    When the user can do logout by Right Bar option
    Then the user should see a successful logout message

  @YS-6
  Scenario: Verify the login functionality for a user with invalid credentials.
    Given the user enters credentials with username "email_invalid@gmail.com" and password "wrongpass"
    When the user submits the login form
    Then the user should see a unsuccessful login message

  @YS-7
  Scenario Outline: Verify the error message displayed when no data is entered in the login form.
    Given the user enters credentials with username "<user>" and password "<password>"
    When the user submits the login form
    Then the user should see a unsuccessful login message

    Examples:
      | user                     | password |
      | emailParamsOff@gmail.com |          |
      |                          | clave1   |

  @YS-13
  Scenario: Validate the error message when the maximum number of login attempts has been reached.
    When the user sends credentials with username "email_attemps@gmail.com" and password "wrongpass" for 10 attempts
    Then the user should see an error message indicating that the maximum number of login attempts has been reached
