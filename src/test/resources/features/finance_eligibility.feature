Feature: Finance Eligibility

  Scenario: User submits finance eligibility form with valid data
    Given the user is on the Finance Eligibility page
    When the user selects the "New car" option
    And the user selects the "Government sector" option
    And the user fills "What is your total monthly income?" with "2000"
    And the user clicks the Continue button
    And the user selects the "No commitments" option
    And the user clicks the Verify button
    Then the authentication form should be displayed
