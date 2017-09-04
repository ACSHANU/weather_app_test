Feature: Hours panel


  Scenario: Hours panel is open on selected date
    Given User navigates to weather forecast "https://weather-acceptance.herokuapp.com/"
    When User clicks on  "23"
    Then Hours panel of  "23" should be displayed

Scenario: All 3 hours summary forecast is displayed for future dates
  Then Summary of all  hours is displayed of "23"
  And Group by 3 hours


  Scenario: Past hours are not displayed
    When User clicks on  "20"
    Then Summary of all passed hours is hidden

  Scenario: Hours panel should be closed when clicked on open date
    When User clicks on  "23"
    Then Hours panel of  "23" should be displayed
    And Hours panel of "20" is closed

