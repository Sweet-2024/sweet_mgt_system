Feature: Raw material Management for suppliers
  Scenario: Add new raw material
    Given logged in to the system as supplier
    When choosing raw material management from the list
    And choosing Add new raw material
    And the owner should add valid raw material information
    Then the raw material should be added successfully

  Scenario: Update existing raw materials
    Given logged in to the system as supplier
    When choosing raw material management from the list
    And choosing update raw material
    And list of available raw materials will appear
    Then the supplier will update raw material
    And updated raw material will be saved successfully

  Scenario: Remove existing raw materials
    Given logged in to the system as supplier
    When choosing raw material management from the list
    And choosing Remove raw material
    And list of available raw materials will appear
    Then the supplier will remove raw material
    And the raw material will be removed successfully