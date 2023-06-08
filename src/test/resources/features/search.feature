@important
Feature: The user shall be able to search for any products in TescoShop

  Rule: Search for an item

    Background:
      Given open main page
      And accept cookies

    @TC_SearchProduct
    Scenario Outline: Search for an item that exists
      When fill the searchBar with "<productName>"
      And click on the search icon
      Then header contains the word "<searchWord>"
      And a product's name contains the "<searchWord>"

      Examples:
        | productName  | searchWord  |
        | alma      | alma        |
        | körte     | körte       |
