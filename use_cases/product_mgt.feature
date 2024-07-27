Feature: Product Management for both owner and supplier
  Scenario: Add new products
    Given logged in to the system as owner or supplier
    When choosing product management from the list
    And choosing Add new product
    And the owner should add valid product information
    Then the product should be added successfully

  Scenario: Update existing products
    Given logged in to the system as owner or supplier
    When choosing product management from the list
    And choosing update product
    And list of available products will appear
    Then the owner or supplier will update product
    And updated product will be saved successfully

  Scenario: Remove existing products
    Given logged in to the system as owner or supplier
    When choosing product management from the list
    And choosing Remove product
    And list of available products will appear
    Then the owner or supplier will remove product
    And the product will be removed successfully

  Scenario: Monitor sales and profits.
    Given logged in to the system as owner or supplier
    When choosing Monitor sales and profits from the list
    Then list of sales and profits will appear

  Scenario: Identify best-selling products.
    Given logged in to the system as owner or supplier
    When choosing Identify best-selling products from the list
    Then list of best-selling products will appear

  Scenario: Implement dynamic discount features.
    Given logged in to the system as owner or supplier
    When the owner or supplier enters discount percentage
    Then the discount will be applied to products with soon expiry date