Feature: monitoring all users including owners and suppliers

  Scenario:
    Given login to the system as administrator
    When choose monitoring and reporting from the list
    Then generate financial reports for both owners and suppliers
    And identify the best-selling product in each store
    And statistics on regular users, gathered by city