@this_test
Feature: Basic Login
  This is a web login test

  Scenario: Successful Login
    Given the username "tomsmith" and password "SuperSecretPassword!"
    When the user do a login
    Then validate the success message and home page

  Scenario Outline: Unsuccessful Login
    Given the username "<user>" and password "<password>"
    When the user do a login
    Then validate the unsuccessfull with "<invalidData>" message

    Examples:
      | invalidData | user        | password             |
      | username    | invalidUser | SuperSecretPassword! |
      | password    | tomsmith    | invalidPassword!     |