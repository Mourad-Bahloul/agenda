Feature: Main Controller Feature

  Scenario: GET /index returns the correct JSON file
    Given the main controller
    When I make a GET request to "/index"
    Then the response status code should be 200
    And the response body should contain a valid JSON file




