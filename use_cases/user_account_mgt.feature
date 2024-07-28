Feature: managing accounts

  Scenario: user updates their account details
    Given logged in to the system as regular user
    When choosing account management from the list
    And entering data to be updated in selected format
    Then the account info will be updated in the system

  Scenario: user updates account with incorrect format
    Given logged in to the system as regular user
    When choosing account management from the list
    And entering data with incorrect format
    Then warning msg will be appeared
    And updating account will be canceled

