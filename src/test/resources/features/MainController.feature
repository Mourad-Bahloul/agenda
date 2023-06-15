Feature: Main Controller Feature

  Scenario:
    Given a registered user with email "test@gmail.com" and password "1234"
    When the user authenticates with email "test@gmail.com" and password "1234"
    Then the response status code should bee 200
    Then the response body should contain a JSON token


