@RegressionTest   @ReportingModuleTests
Feature:  Reporting Manager should be able to see Reports

  @TotalOrderReportTest
  Scenario Outline: Reporting manager can see sales-total ordered report
    Given reporting manager is on the admin page
    When reporting manager fills out report date "<dateFrom>" and"<dateTo>"
    Then total ordered report should display
    Examples:
      | dateFrom  | dateTo    |
      | 2/26/2023 | 3/26/2023 |

  @SeeSales-TotalInvoicedVsPaidReport
  Scenario Outline: Reporting Manager should be able to see sales- Total Invoiced Vs Paid Report
    Given Reporting manager is on the dashboard page and clicks on Invoiced Option
    When Reporting Manager Navigate to Total Invoiced vs Paid Report page and select period and date "<fromDate>" "<toDate>" and click show Report button
    Then Total Invoiced Vs Paid report view successfully
    Examples:
      | fromDate   | toDate     |
      | 01/01/2013 | 03/30/2022 |

  @CustomerByOrdersTotal
  Scenario Outline: Reporting manager can see customer by orders total
    Given Reporting manager is on the dashboard page and clicks on customer by order total link
    When  Reporting manager enter "<from data>""<to data>" and click refresh button
    Then verifymanager can see customers by orders total
    Examples:
      | from data  | to data    |
      | 02/01/2023 | 02/20/2023 |

  @SeeSales-TotalInvoicedVsPaidReport
  Scenario Outline: Reporting Manager should be able to see sales- Total Invoiced Vs Paid Report
    Given Reporting manager is on the dashboard page and clicks on Invoiced Option
    When Reporting Manager Navigate to Total Invoiced vs Paid Report page and select period and date "<fromDate>" "<toDate>" and click show Report button
    Then Total Invoiced Vs Paid report view successfully

    Examples:
      | fromDate   | toDate     |
      | 01/01/2013 | 03/30/2022 |

  @CustomerByOrdersTotal
  Scenario Outline: Reporting manager can see customer by orders total
    Given Reporting manager is on the dashboard page and clicks on customer by order total link
    When  Reporting manager enter "<from data>""<to data>" and click refresh button
    Then verifymanager can see customers by orders total
    Examples:
      | from data  | to data    |
      | 02/01/2023 | 02/20/2023 |

  @SeeDownloadsProducts
  Scenario: Reporting Manager should be able to see Products - Products Downloads Report
    Given Reporting manager is on the dashboard page click on the reports
    When  click on downloadsLink
    Then downloadsProductPage should display

  @SeeReviewProducts
  Scenario: Reporting Manager should be able to see Reviews - Product Reviews Report
    Given Reporting manager is on the dashboard page click on the reports
    When click on the products review link
    Then review products page should display

  @TotalShippedOrdersReport
  Scenario Outline: Reporting Manager should be able to see total shipped orders report
    Given reporting manager is on the admin page and clicks shipping report
    When reporting manager fills out report date for the shipped orders "<dateFrom>" and"<dateTo>"
    Then total shipped report should appear
    Examples:
      | dateFrom   | dateTo     |
      | 03/21/2023 | 03/25/2023 |

  @SeeSalesTotalRefundsReport
  Scenario Outline: Reporting Manager should be able to see Total Refunds Report
    Given reporting manager is on the admin page
    When reporting manager fills out the report date "<dateFrom>" and"<dateTo>"
    Then total Refunds Report should display
    Examples:
      | dateFrom  | dateTo    |
      | 2/26/2023 | 3/26/2023 |

  @AbandonedCartsReportTest
  Scenario: Reporting manager should be able to see Shopping Cart - Abandoned carts report
    Given Reporting manager is on the admin page and clicks on Abandoned carts page
    When Reporting manager choose shopping website for report
    Then Abandoned carts report should display
