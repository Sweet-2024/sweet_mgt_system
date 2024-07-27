Feature: login to sweet system
  description: login should be consistance and quick

  Scenario: valid credential
    Given trying to login to sys
    When login with correct email and password
    Then user is now in the system
    And given a welcome msg will appear
    And go to corresponding page according to user type

  Scenario: invalid password
    Given trying to login to sys
    When login with correct email and incorrect password
    Then given wrong password msg will appear

  Scenario: invalid email
    Given trying to login to sys
    When login with incorrect email and correct password
    Then given user is not in the system msg will appear