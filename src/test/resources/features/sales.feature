@RegressionTest @SalesModuleTests

Feature: Sales Module Functions
  @CreateOrderTest
  Scenario Outline: sales manager can create a new order
    Given sales manager is on the admin page
    When the sales manager fills out a new order form "<customerName>"
    Then a new order should be created
    Examples:
      | customerName    |
      | Holly Adams|

  @UpdateShipments
  Scenario Outline: Sales Manager can update shipments
    Given Sales manager is on the dashboard page and clicks on shipmentsOption
    When Sales Manager click view icon and fill out "<commentHistory>" information and click on submit comment button
    And Sales Manager edit shipping and tracking information and fill out "<number>" and click on add button
    Then the shipments update successfully

    Examples:
      | commentHistory       | number   |
      | Shipped completed | 04082023 |
