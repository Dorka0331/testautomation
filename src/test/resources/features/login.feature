@important
Feature: The user must login to order from TescoShop

  Rule: Login with an existing user

    Background:
      Given open main page
      And accept cookies

    @TC_Login
    Scenario Outline: Login
      When click on the login menu item
        Then header has the word "Bejelentkezés"
      When fill the email field with "<emailAddress>"
      And fill the password field with "<password>"
      And click on the login button
      Then headerline contains the word "<name>"
      When click on the logout button
      Then login button is present

      Examples:
        | emailAddress                | password      | name      |
        | dorka0331@gmail.com         | 439c763fgG    |   Dorina  |
        | dorina.asztalos@telekom.com | JoMeRu230123# |   Dorka   |
