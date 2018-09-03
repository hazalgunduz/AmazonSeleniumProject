Feature: Amazon Selenium Search

   Scenario: Amazon Search
    When I have opened "https://www.amazon.com/" in a browser
    And I sign in with hazalgunduz@yahoo.com/123456
    And I see text "Hi, Hazal"
    And I fill search as "Samsung"
    And I navigate to "Page 2"
    And I select item for WishList
    And I click delete
    Then I quit browser






