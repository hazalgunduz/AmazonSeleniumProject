Feature: Amazon Selenium Search

   Scenario: Sign in
    When I have opened "Firefox" as a browser
    And I sign in with "hazalgunduz@yahoo.com/123456"
    And I see text "Hi, Hazal"
    And I fill "Search" as "Samsung"
    And I navigate to "Page 2"
    And I select item for WishList
    And I click delete







