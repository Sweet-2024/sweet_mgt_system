Feature: Exploration management

  Scenario: Browse for dessert recipes.
    Given logged in to the system as regular user
    When choose Explore recipes from the list
    Then list of dessert recipes exist in the system

  Scenario: search for dessert recipes.
    Given logged in to the system as regular user
    When choose search for recipes from the list
    And enter recipe name to search for
    Then recipe will appear

  Scenario: search for non existing recipe
    Given logged in to the system as regular user
    When choose search for recipes from the list
    And enter recipe name to search for
    And recipe name doesn't exist
    Then warning msg will be appeared

  Scenario: filter recipes based on dietary needs
    Given logged in to the system as regular user
    When choose recipes for dietary needs from the list
    Then list of dietary needs recipes will appear with description

  Scenario: filter recipes based on food allergies
    Given logged in to the system as regular user
    When choose recipes for food allergies from the list
    Then list of food allergies recipes will appear with description
