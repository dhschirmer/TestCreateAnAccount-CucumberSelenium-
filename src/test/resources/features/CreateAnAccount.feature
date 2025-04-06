Feature: Create an account

  Scenario: Successful account creation
    Given I use "Google Chrome"
    And I browse to Basketball England membership
    And I have entered "01/04/1990" as valid date of birth
    And I have entered "Peter" as first name
    And I have entered "Parker" as last name
    And I have entered "peterparker@gmail.1.com" as email address
    And I have entered "peterparker@gmail.1.com" as confirm email address
    And I have entered "123asd" as password
    And I have entered "123asd" as retype your password
    And I have checked Terms and Conditions
    And I have checked age control and code of conduct
    When I click Confirm and Join
    Then The membership is created