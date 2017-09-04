Feature: Daily forecast should summarise the 3 hour data

  Scenario: Display summary of 3 hour data
    Given User navigates to weather forecast "https://weather-acceptance.herokuapp.com/"
    When Enter city "Edinburgh"
    And press Enter
    And User clicks on  "23"
    Then Max and min temperatures are displayed
    And Windspeed and directions are displayed
    And Aggregate rainfall is displayed

    Scenario: Format of values
      Then All numeric values should be rounded down