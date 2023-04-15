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

    Scenario : Sales Manager Can View Invoice Comment History
      Given sales manager is on the admin page
      When the sales manager view the invoice history
      Then  sales manager should be able to view comment history

      Scenario Outline: Sales Manager Can Add Invoice Comment In The History Page
        Given sales manager is on the admin page
        When  the sales manager fills out "<commentHistory>" field and click on submit comment button
        Then  the comment should be display on the frontend page
        Examples:
          |commentHistory  |
          |   HelloWorldTeam1 |