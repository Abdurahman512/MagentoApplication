@RegressionTest @SalesModuleTests @CreateOrderTest
Feature: Sales Manager should be able to create a new order

  Scenario: sales manager can create a new order
    Given sales manager is on the admin page
    When the sales manager fills out a new order form
    Then a new order should be created