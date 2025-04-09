@login_opencart
Feature: User Login to Your Store
  As a registered user,
  I want to log in to the Your Store application,
  So that I can access my account.

  Background:
    Given the user is on the login page of Your Store

  @YS-5
  Scenario: Verify the login functionality for a user with valid credentials
    Given the user enters valid credentials with username "email12343565@gmail.com" and password "clave1"
    When the user submits the login form
    Then the user should see a successful login message
