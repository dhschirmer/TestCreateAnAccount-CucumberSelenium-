Feature: Create an account

  Scenario: Successful account creation
    Given I use "Google Chrome"
    And I browse to Basketball England membership
    And I have entered "01/04/1990" as valid date of birth
    And I have entered "Peter" as first name
    And I have entered "Parker" as last name
    And I have entered "spider.man@gmail.com" as email address
    And I have entered "spider.man@gmail.com" as confirm email address
    And I have entered "123asd" as password
    And I have entered "123asd" as retype your password
    And I have checked Terms and Conditions
    And I have checked age control and code of conduct
    When I click Confirm and Join
    Then The membership is created

  Scenario Outline: Unsuccessful account creation
    Given I use "<browser>"
    And I browse to Basketball England membership
    And I have entered "<date>" as valid date of birth
    And I have entered "<firstName>" as first name
    And I have entered "<lastName>" as last name
    And I have entered "<email>" as email address
    And I have entered "<confirmEmail>" as confirm email address
    And I have entered "<password>" as password
    And I have entered "<passwordCheck>" as retype your password
    And I have <checked> Terms and Conditions
    And I have checked age control and code of conduct
    When I click Confirm and Join
    Then I will get a "<errorMessage>"

    Examples:
      | browser        | date       | firstName | lastName | email                  | confirmEmail           | password | passwordCheck | checked     | errorMessage                                                              |
      | Google Chrome  | 08/03/1985 | Diana     |          | wonder.woman@gmail.com | wonder.woman@gmail.com | 123asd   | 123asd        | checked     | Last Name is required                                                     |
      | Microsoft Edge | 01/10/1980 | Bruce     | Wayne    | batman@gmail.com       | batman@gmail.com       | 123asd   | asd123        | checked     | Password did not match                                                    |
      | Google Chrome  | 10/09/1975 | Tony      | Stark    | iron.man@gmail.com     | iron.man@gmail.com     | 123asd   | 123asd        | not checked | You must confirm that you have read and accepted our Terms and Conditions |
    