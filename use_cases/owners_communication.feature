Feature: Communication and Notification
  Scenario: communication with users
    Given logged in to the system as owner
    When choosing Communication and Notification from the list
    And choosing communication with users
    And list of users will appear
    Then the owner will communicate with selected user

  Scenario: communication with suppliers
    Given logged in to the system as owner
    When choosing Communication and Notification from the list
    And choosing communication with suppliers
    And list of suppliers will appear
    Then the owner will communicate with selected supplier

  Scenario: communication with owners
    Given logged in to the system as supplier
    When choosing Communication and Notification from the list
    And choosing communication with owners
    And list of owners will appear
    Then the supplier will communicate with selected owner
