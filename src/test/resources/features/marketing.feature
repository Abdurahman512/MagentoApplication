@MarketingModuleTest @RegressionTest
Feature:Marketing Manager can manage market
  @MarketingManagerCanAddNewCartPriceRule
  Scenario Outline: Marketing Manager can add new shopping cart price rule
    Given Marketing manager on the dashboard page and marketing manager click on Promotions link
    When  click on Shopping Cart Price Rules link to fill out "<RuleName>" "<description>" "<Priority>" and other information information
    Then a new shopping cart price rule should be added successfully
    Examples:
      |RuleName        | description         | Priority |
      |30% Sales(istanbulTeam) |30% off any product  | Medium |



  @MarketingManagerUpdateCartPriceRule
  Scenario Outline: MarketingManagerUpdateCartPriceRule
    Given Marketing manager on the dashboard page and marketing manager click on Promotions link
    When select the "<RuleName>" and change the "<description>"
    Then cart price rule should be updated successfully
    Examples:
      |RuleName          |description                               |
      |30% Sales(istanbulTeam)  | validate until the end of this year      |