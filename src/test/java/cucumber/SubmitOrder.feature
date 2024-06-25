
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file
	Background:
	Given:I landed on Ecommerce page
	
  @Regression
  Scenario Outline:Psitive test of Submitting the order
    Given I logged in with username <name> and password <password>
    When I add product <productName> to cart
    And  Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confimationPage

    Examples: 
      | username                    | password      | productName    |
      | senapatitamishra1@gmail.com |     Aa@123456 | ADIDAS ORIGINAL|
     