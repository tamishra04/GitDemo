
@tag
Feature: Error validation
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    GivenI landed on Ecommerce page
    When I logged in with username <name> and password <password>
    Then "Incorrect email  password." message is displayed
    Examples: 
      | username                    | password      | 
      | senapatitamishra1@gmail.com | A@123456 | 
