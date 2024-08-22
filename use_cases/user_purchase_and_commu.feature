Feature: Purchase, communication and feedback from regular users

  Scenario: Purchase desserts directly from store owners.
    Given logged in to the system as regular user
    When choose purchase desserts from the list
    And choosing owner name to buy from the list
    And choosing product name to buy from the selected owner
    Then the order will be created successfully
    And a msg will be sent to the owner to notify him

  Scenario: Purchase desserts directly from store owners.
    Given logged in to the system as regular user
    When choose communication and feedback from the list
    And choosing owner name to buy from the list
    And writing msg to the owner
    And sending the msg
    Then the owner will receive the msg successfully

  Scenario: giving feedback to a selected product
    Given logged in to the system as regular user
    When choose communication and feedback from the list
    And choosing order and product id
    And entering the evaluation
    Then feedback will be sent

