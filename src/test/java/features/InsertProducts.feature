Feature: Insert products using POST API

  Scenario Outline: Validate post product api works correctly
    Given I hit the url of POST products api endpoint
    When I pass the url in the products in the request
    And I pass the request body of product title <ProductsTitle>
    Then I receive the response code as 200
#    Then I receive the response bady with id as <ID>

    Examples:
      | ProductsTitle              | ID |
      | Sold Gold Petite Micropave | 21 |