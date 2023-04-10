@RegressionTest  @TotalOrderReportTest @ReportingModuleTests
Feature:  Reporting Manager should be able to see Sales - Total Ordered Report

  Scenario: reporting manager can can see sales-total ordered report
    Given reporting manager is on the admin page
    When reporting manager fills out report starting ending dates
    Then total ordered report should display


