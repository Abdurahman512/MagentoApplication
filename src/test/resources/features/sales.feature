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

  @ViewInvoiceCommentHistory
  Scenario: Sales Manager Can View Invoice Comment History
    Given Sales Manager is on the Invoice Dashboard Page
    When Sales Manager View the Invoice Comment Field
    Then Sales Manager Can See The Invoice Comment History

   @AddInvoiceComment
   Scenario Outline: Sales Manager Can Add Invoice Comment In History Page
     Given Sales Manager is on the Invoice Dashboard Page
     When Sales Manager fills out "<commentField>"
     Then The Comment List Should Be Updated
     Examples:
       | commentField |
       | HelloWorldIstanbulTeam|


    @viewRefundsInReports
    Scenario Outline: Sales Manager can view Refunds in Reports
      Given  sales manager is on the admin page
      When Sales manager Click on Reports move to reports and move to Sales and select refunds fill in "<from>" and "<to>" field
      Then Total Refunded Report page should display with information

      Examples:
      | from | to |
      | 3/15/2023 | 4/15/2023 |

      @viewCouponsReports
      Scenario Outline:Sales Manager should be able to view coupons in the Reports
        Given sales manager is on the admin page
        When Sales manager Click on Reports move to reports and move to Sales and select coupons fill in "<fromname>"and"<toname>"field
        Then Total coupons Report page should display with information

        Examples:
        | fromname | toname  |
        | 3/20/2023 | 4/15/2023 |


