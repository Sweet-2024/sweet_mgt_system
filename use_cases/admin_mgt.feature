Feature: admin Managements

  Scenario: Admin manages users, owners, and suppliers information
    Given login to the system as administrator
    When admin choose managing accounts from the list
    Then list of users, owners, and suppliers information will appear
    And admin can edit, add, delete, and update information
