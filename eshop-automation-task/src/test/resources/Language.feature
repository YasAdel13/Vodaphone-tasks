Feature: :Buying process


  Scenario: user add iphone to the cart and fill the shipping data
    Given the user in the home page of vodafone shop website
    When the user select English as a language
    And the user select the brand from brand sections
    And the user selected one of the returned products
    And the user add the selected item to the basket
    And the user proceed to checkout
    And the user fill delivery option fields
    And the user add address details
    | Berm Eltonsy | 20 | 6 | 6 | south cairo court | Downtown |
    And the user fill personal info fields but leave full name field empty
    | yasmineadel@gmail.com | 01146673519 | 0223682168 |
    Then error message should be appear when the user click continue without fill the full name field empty


