Feature: Summary of Weather forecast

  Scenario: Display current Or Dominant condition
    Given User navigates to weather forecast "https://weather-acceptance.herokuapp.com/"
    When Enter city "Edinburgh"
    And press Enter
    And User clicks on  "23"
    Then Max and min temperatures are displayed
    And Windspeed and directions are displayed
    And Total rainfall and Pressure are displayed

    Scenario: Format of values
      Then All numeric values should be rounded down