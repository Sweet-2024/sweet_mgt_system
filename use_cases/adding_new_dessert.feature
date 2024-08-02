Feature: managing accounts

  Scenario: Post and share personal dessert creations
    Given logged in to the system as regular user
    When choose adding new dessert creations from the list
    And entering acceptable dessert name
    And entering acceptable dessert category
    Then new recipe will be added to the system successfully

  Scenario: inserting dessert with incorrect format
    Given logged in to the system as regular user
    When choose adding new dessert creations from the list
    And entering dessert data with incorrect format
    Then warning msg will be appeared
