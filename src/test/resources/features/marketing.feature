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


    @MarketingManagerViewAllReviews
    Scenario: Marketing manger can view All Reviews
      Given Marketing manager should be on the dashboard page and click on the CatalogLink
      When select Reviews and Rating and move to Customer Reviews and Select All Views
      Then All Reviews page should display with the Reviews InformationMa


      @MarketingManagerViewPendingReviews
      Scenario: Marketing manager can view Pending Reviews
        Given Marketing manager on the dashboard page and marketing manager click on the CatalogLink
        When select Reviews and Rating and move to Customer Review and select Pending Reviews
        Then Pending Reviews page should display with the Review information
