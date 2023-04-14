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

    @MarketingManagerCanAddNewsletterTemplate
    Scenario: Marketing manager can add newsletter template
      Given Marketing manager on the dashboard page and marketing manager click on Newsletter template
      When click on Add new template
      And Fill in Template information fields
      Then Save the new template

      @MarketingManagerCanUpdateExistingTemplate
      Scenario: Marketing manager can update existing template
        Given Marketing manager on the newsletter templates page
        When click on template name field
        And search the existing template and click
        And change the subject name and save the template
        Then The template successfully updated

        @MarketingManagerCanDeleteExistingTemplate
        Scenario: Marketing manager can delete existing template
          Given search the existing template click the template
          When click on the delete template button
          Then the template should be successfully deleted



