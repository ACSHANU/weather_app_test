$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("WeatherForecast_Date_test.feature");
formatter.feature({
  "line": 1,
  "name": "Display weather forecast for a city",
  "description": "",
  "id": "display-weather-forecast-for-a-city",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "Display weather report for a valid city",
  "description": "",
  "id": "display-weather-forecast-for-a-city;display-weather-report-for-a-valid-city",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "User navigates to weather forecast \"https://weather-acceptance.herokuapp.com/\"",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "Enter city \"Edinburgh\"",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "press Enter",
  "keyword": "And "
});
formatter.step({
  "line": 7,
  "name": "Weather report for \"5\" days should be displayed",
  "keyword": "Then "
});
formatter.step({
  "line": 8,
  "name": "Displayed dates are consecutive days",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "Starts from Today",
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "https://weather-acceptance.herokuapp.com/",
      "offset": 36
    }
  ],
  "location": "WeatherForecastSteps.User_navigates_to_weather_forecast(String)"
});
formatter.result({
  "duration": 13151394752,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Edinburgh",
      "offset": 12
    }
  ],
  "location": "WeatherForecastSteps.Enter_city(String)"
});
formatter.result({
  "duration": 3942669806,
  "status": "passed"
});
formatter.match({
  "location": "WeatherForecastSteps.press_Enter()"
});
formatter.result({
  "duration": 2237285410,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "5",
      "offset": 20
    }
  ],
  "location": "WeatherForecastSteps.Weather_report_for_days_should_be_displayed(String)"
});
formatter.result({
  "duration": 195361605,
  "status": "passed"
});
formatter.match({
  "location": "WeatherForecastSteps.Displayed_dates_are_consecutive_days()"
});
formatter.result({
  "duration": 337339979,
  "status": "passed"
});
formatter.match({
  "location": "WeatherForecastSteps.Starts_from_Today()"
});
formatter.result({
  "duration": 150106657,
  "status": "passed"
});
formatter.scenario({
  "line": 12,
  "name": "Display error on entering  an invalid city",
  "description": "",
  "id": "display-weather-forecast-for-a-city;display-error-on-entering--an-invalid-city",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 13,
  "name": "Enter city \"London\"",
  "keyword": "When "
});
formatter.step({
  "line": 14,
  "name": "press Enter",
  "keyword": "And "
});
formatter.step({
  "line": 15,
  "name": "Error must be displayed",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "London",
      "offset": 12
    }
  ],
  "location": "WeatherForecastSteps.Enter_city(String)"
});
formatter.result({
  "duration": 2784726241,
  "status": "passed"
});
formatter.match({
  "location": "WeatherForecastSteps.press_Enter()"
});
formatter.result({
  "duration": 594030631,
  "status": "passed"
});
formatter.match({
  "location": "WeatherForecastSteps.Error_must_be_displayed()"
});
formatter.result({
  "duration": 38364582,
  "status": "passed"
});
formatter.uri("WeatherForecast_Hours_test.feature");
formatter.feature({
  "line": 1,
  "name": "Hours panel",
  "description": "",
  "id": "hours-panel",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 4,
  "name": "Hours panel is open on selected date",
  "description": "",
  "id": "hours-panel;hours-panel-is-open-on-selected-date",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": "User navigates to weather forecast \"https://weather-acceptance.herokuapp.com/\"",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "User clicks on  \"23\"",
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "Hours panel of  \"23\" should be displayed",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "https://weather-acceptance.herokuapp.com/",
      "offset": 36
    }
  ],
  "location": "WeatherForecastSteps.User_navigates_to_weather_forecast(String)"
});
formatter.result({
  "duration": 9967065487,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "23",
      "offset": 17
    }
  ],
  "location": "WeatherForecastSteps.User_clicks_on(String)"
});
formatter.result({
  "duration": 1087241475,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "23",
      "offset": 17
    }
  ],
  "location": "WeatherForecastSteps.Hours_panel_of_should_be_displayed(String)"
});
formatter.result({
  "duration": 281118265,
  "status": "passed"
});
formatter.scenario({
  "line": 9,
  "name": "All 3 hours summary forecast is displayed for future dates",
  "description": "",
  "id": "hours-panel;all-3-hours-summary-forecast-is-displayed-for-future-dates",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 10,
  "name": "Summary of all  hours is displayed of \"23\"",
  "keyword": "Then "
});
formatter.step({
  "line": 11,
  "name": "Group by 3 hours",
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "23",
      "offset": 39
    }
  ],
  "location": "WeatherForecastSteps.summaryOfAllHoursIsDisplayedOf(String)"
});
formatter.result({
  "duration": 1074422681,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "3",
      "offset": 9
    }
  ],
  "location": "WeatherForecastSteps.groupByHours(int)"
});
formatter.result({
  "duration": 2148535,
  "status": "passed"
});
formatter.scenario({
  "line": 14,
  "name": "Past hours are not displayed",
  "description": "",
  "id": "hours-panel;past-hours-are-not-displayed",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 15,
  "name": "User clicks on  \"20\"",
  "keyword": "When "
});
formatter.step({
  "line": 16,
  "name": "Summary of all passed hours is hidden",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "20",
      "offset": 17
    }
  ],
  "location": "WeatherForecastSteps.User_clicks_on(String)"
});
formatter.result({
  "duration": 1117784546,
  "status": "passed"
});
formatter.match({
  "location": "WeatherForecastSteps.summaryOfAllPassedHoursIsHidden()"
});
formatter.result({
  "duration": 857434314,
  "status": "passed"
});
formatter.scenario({
  "line": 18,
  "name": "Hours panel should be closed when clicked on open date",
  "description": "",
  "id": "hours-panel;hours-panel-should-be-closed-when-clicked-on-open-date",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 19,
  "name": "User clicks on  \"23\"",
  "keyword": "When "
});
formatter.step({
  "line": 20,
  "name": "Hours panel of  \"23\" should be displayed",
  "keyword": "Then "
});
formatter.step({
  "line": 21,
  "name": "Hours panel of \"20\" is closed",
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "23",
      "offset": 17
    }
  ],
  "location": "WeatherForecastSteps.User_clicks_on(String)"
});
formatter.result({
  "duration": 909047055,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "23",
      "offset": 17
    }
  ],
  "location": "WeatherForecastSteps.Hours_panel_of_should_be_displayed(String)"
});
formatter.result({
  "duration": 314705092,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "20",
      "offset": 16
    }
  ],
  "location": "WeatherForecastSteps.Hours_panel_of_is_closed(String)"
});
formatter.result({
  "duration": 268864543,
  "status": "passed"
});
formatter.uri("WeatherForecast_Summary_test.feature");
formatter.feature({
  "line": 1,
  "name": "Summary of Weather forecast",
  "description": "",
  "id": "summary-of-weather-forecast",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "Display current Or Dominant condition",
  "description": "",
  "id": "summary-of-weather-forecast;display-current-or-dominant-condition",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "User navigates to weather forecast \"https://weather-acceptance.herokuapp.com/\"",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "Enter city \"Edinburgh\"",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "press Enter",
  "keyword": "And "
});
formatter.step({
  "line": 7,
  "name": "User clicks on  \"23\"",
  "keyword": "And "
});
formatter.step({
  "line": 8,
  "name": "Max and min temperatures are displayed",
  "keyword": "Then "
});
formatter.step({
  "line": 9,
  "name": "Windspeed and directions are displayed",
  "keyword": "And "
});
formatter.step({
  "line": 10,
  "name": "Total rainfall and Pressure are displayed",
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "https://weather-acceptance.herokuapp.com/",
      "offset": 36
    }
  ],
  "location": "WeatherForecastSteps.User_navigates_to_weather_forecast(String)"
});
formatter.result({
  "duration": 9280123256,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Edinburgh",
      "offset": 12
    }
  ],
  "location": "WeatherForecastSteps.Enter_city(String)"
});
formatter.result({
  "duration": 4109755479,
  "status": "passed"
});
formatter.match({
  "location": "WeatherForecastSteps.press_Enter()"
});
formatter.result({
  "duration": 2565913216,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "23",
      "offset": 17
    }
  ],
  "location": "WeatherForecastSteps.User_clicks_on(String)"
});
formatter.result({
  "duration": 1137889429,
  "status": "passed"
});
formatter.match({
  "location": "WeatherForecastSteps.Max_and_min_temperatures_are_displayed()"
});
formatter.result({
  "duration": 198634016,
  "status": "passed"
});
formatter.match({
  "location": "WeatherForecastSteps.Windspeed_and_directions_are_displayed()"
});
formatter.result({
  "duration": 166850748,
  "status": "passed"
});
formatter.match({
  "location": "WeatherForecastSteps.Total_rainfall_and_Pressure_are_displayed()"
});
formatter.result({
  "duration": 84954951,
  "status": "passed"
});
formatter.scenario({
  "line": 12,
  "name": "Format of values",
  "description": "",
  "id": "summary-of-weather-forecast;format-of-values",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 13,
  "name": "All numeric values should be rounded down",
  "keyword": "Then "
});
formatter.match({
  "location": "WeatherForecastSteps.All_numeric_values_should_be_rounded_down()"
});
formatter.result({
  "duration": 2685508735,
  "status": "passed"
});
});