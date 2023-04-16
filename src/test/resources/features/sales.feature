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

    @viewRefundsInReports
    Scenario Outline: Sales Manager can view Refunds in Reports
      Given  Sales manager is on the dashboard page and clicks on shipmentsOption
      When Sales manager Click on Reports move to reports and move to Sales and select refunds
      And fill in "<FROM>" and "<To>" field
      Then Total Refunded Report page should display with information

      Examples:
      | from | to |
      | 3/15/2023 | 4/15/2023 |
