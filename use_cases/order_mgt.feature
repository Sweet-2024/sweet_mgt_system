Feature: managing orders

  Scenario: owners create a new order
    Given logged in to the system as owner
    When choosing order management from the list
    And choosing create an order from suppliers from the list
    And choosing the supplier to buy from
    And listing the required product
    Then the order will be saved
    And a msg will be sent to the selected supplier to notify him

  Scenario: owners received a new order
    Given logged in to the system as owner
    When choosing order management from the list
    And choosing order messages from the list
    Then list of orders' status and info about each order will appear

