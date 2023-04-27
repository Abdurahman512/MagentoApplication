@MarketingModuleTest @RegressionTest
Feature:Marketing Manager can manage market

  @MarketingManagerCanAddNewCartPriceRule
  Scenario Outline: Marketing Manager can add new shopping cart price rule
    Given Marketing manager on the dashboard page and marketing manager click on Promotions link
    When  click on Shopping Cart Price Rules link to fill out "<RuleName>" "<description>" "<Priority>" and other information information
    Then a new shopping cart price rule should be added successfully
    Examples:
      | RuleName                | description         | Priority |
      | 30% Sales(istanbulTeam) | 30% off any product | Medium   |


  @MarketingManagerUpdateCartPriceRule
  Scenario Outline: MarketingManagerUpdateCartPriceRule
    Given Marketing manager on the dashboard page and marketing manager click on Promotions link
    When select the "<RuleName>" and change the "<description>"
    Then cart price rule should be updated successfully
    Examples:
      | RuleName                | description                         |
      | 30% Sales(istanbulTeam) | validate until the end of this year |


  @CatalogPriceRule
  Scenario: Marketing Manager can add new Catalog Price Rule

    Given Marketing manager on the dashboard page and marketing manager click on Promotions link
    When  click Catalog Price Rules in Promotions,click Add New Rule button and enters information in the fields
    Then  catalog price rule should be added successfully

  @updateexistingCatalogPriceRule
  Scenario: Marketing Manager can update existing Catalog Price Rule

    Given Marketing manager on the dashboard page and marketing manager click on Promotions link
    When  click Catalog Price Rules in Promotions,finde rule name and edit it and click Save button
    Then  existing Catalog Price Rule should be updated successfully


  @searchByIdAndRule
  Scenario: Marketing Manager can search Catalog Pricing Rule By Id and Rule

    Given Marketing manager on the dashboard page and marketing manager click on Promotions link
    When  click Catalog Price Rules in Promotions, enters ID "19" and  Rule Name "B" and clicks search button
    Then  the ID and Rule Name should be display


