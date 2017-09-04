Feature: Display weather forecast for a city

  Scenario: Display weather report for a valid city
    Given User navigates to weather forecast "https://weather-acceptance.herokuapp.com/"
    When Enter city "Edinburgh"
    And press Enter
    Then Weather report for "5" days should be displayed
    And Displayed dates are consecutive days
    And Starts from Today


  Scenario: Display error on entering  an invalid city
    When Enter city "London"
    And press Enter
    Then Error must be displayed

