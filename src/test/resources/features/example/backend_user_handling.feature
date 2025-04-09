
Feature: BackEnd test
  Backend Test using RestAssure library

  Scenario: Create a new user
    Given the user wants to execute a petition
    When a "valid" user is created
    Then check the validation code is 201
    And check the creation response

  @this_test_back
  Scenario: Get an user
    Given the user wants to execute a petition
    When a "valid" user is created
    And the user wants to consult the customer
    Then check the validation code is 200
    And check the client information
