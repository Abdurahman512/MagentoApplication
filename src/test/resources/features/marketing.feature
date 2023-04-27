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

      @MarketingManagerCanViewNewsletterSubscribersLink
      Scenario: Marketing manager can view newsletter subscribers
        Given marketing manager on the dashboard page and can click on Newsletter link
        When marketing manager can click on Newsletter Subscribers link
        Then marketing manager can view newsletter subscribers successfully

       # 1.Marketing Manager can view All Reviews

    @MarketingManagerViewAllReviews
  Scenario: Marketing manger can view All Reviews
    Given Marketing manager on the dashboard page and marketing manager click on Promotions link
    When click on the CatalogLink select Reviews and Rating and move to Customer Reviews and Select All Views
    Then All Reviews page should display with the Reviews InformationMa

  #2.Marketing Manager can edit Existing Reviews
  @MarketingManagerUpdateExistingReviews
  Scenario: MarketingManagerUpdateExistingReviews
    Given Marketing manager on the dashboard page and marketing manager click on Promotions link
    When select the existing reviews and edit the nickname field
    Then existing reviews should  be updated successfully


    #3.Marketing Manager can view Pending Reviews
  @MarketingManagerViewPendingReviews
  Scenario: Marketing manager can view Pending Reviews
    Given Marketing manager on the dashboard page and marketing manager click on Promotions link
    When select Reviews and Rating and move to Customer Review and select Pending Reviews
    Then Pending Reviews page should display with the Review information


  @MarektingManagerUpdateExistingPendingReviews
  Scenario: Marketing manager can edit existing Pending Reviews
    Given Marketing manager on the dashboard page and marketing manager click on Promotions link
    When select the existing pending reviews and edit the summary of reviews filed
    Then existing pending reviews should be updated successfully




