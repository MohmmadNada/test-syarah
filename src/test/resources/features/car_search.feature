Feature: Car Search Functionality

  Scenario: Search Toyota cars by year range
    Given the user is on the Syarah Home page
    When the user selects Toyota as the brand
    Then the user redirected to Search Results page
    When the user selects year range 2022 to 2025
    Then validate that the applied filter tags include the brand "تويوتا" and year range 2022 to 2025
    And validate that all car cards in the results contain "تويوتا" in their title