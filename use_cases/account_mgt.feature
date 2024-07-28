Feature: managing accounts and update business info

  Scenario: owners or suppliers updates their account details
    Given logged in to the system as owner or supplier
    When choosing account management from the list
    And entering data to be updated in selected format
    Then the account info will be updated in the system

  Scenario: owners or suppliers updates their account details
    Given logged in to the system as owner or supplier
    When choosing business management from the list
    And entering data to be updated in selected format
    Then the business info will be updated in the system