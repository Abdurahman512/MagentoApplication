@RegressionTest   @ReportingModuleTests
Feature:  Reporting Manager should be able to see Reports

  @TotalOrderReportTest
  Scenario Outline: reporting manager can can see sales-total ordered report
    Given reporting manager is on the admin page
    When reporting manager fills out report date "<dateFrom>" and"<dateTo>"
    Then total ordered report should display
    Examples:
      |dateFrom  |dateTo    |
      |2/26/2023|3/26/2023|

  @SeeSales-TotalInvoicedVsPaidReport
  Scenario Outline: Reporting Manager should be able to see sales- Total Invoiced Vs Paid Report
    Given Reporting manager is on the dashboard page and clicks on Invoiced Option
    When Reporting Manager Navigate to Total Invoiced vs Paid Report page and select period and date "<fromDate>" "<toDate>" and click show Report button
    Then Total Invoiced Vs Paid report view successfully

    Examples:
      |fromDate  |toDate    |
      |01/01/2013|03/30/2022|
  @CustomerByOrdersTotal
  Scenario Outline: Reporting manager can see customer by orders total
    Given Reporting manager is on the dashboard page and clicks on customer by order total link
    When  Reporting manager enter "<from data>""<to data>" and click refresh button
    Then verifymanager can see customers by orders total
    Examples:
      |from data |to data|
      |02/01/2023|02/20/2023|
