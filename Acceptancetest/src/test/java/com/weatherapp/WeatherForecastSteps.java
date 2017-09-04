package com.weatherapp;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.weatherapp.CommonObjectRepository;

import java.util.ArrayList;

public class WeatherForecastSteps {

	static WebDriver driver = null;
	static WebDriverWait wait = null;
	static int SMALL_WAIT = 30;
	static String currentDaySelected = "";

	@Given("^User navigates to weather forecast \"([^\"]*)\"$")
	public void User_navigates_to_weather_forecast(String url) throws Throwable {

		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, SMALL_WAIT);
		driver.navigate().to(url);
	}

	@When("^User clicks on  \"([^\"]*)\"$")
	public void User_clicks_on(String day) throws Throwable {

		// Get the row index of day passed
		String index = CommonObjectRepository.getRowIndexOfADay(day);
		currentDaySelected = day;

		String locator = "//span[@data-test='date-%1$s']";
		locator = String.format(locator, index);

		// wait for the element to be visible before click
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath(locator)));
		driver.findElement(By.xpath(locator)).click();

	}

	@Then("^Summary of all  hours is displayed of \"([^\"]*)\"$")
	public void summaryOfAllHoursIsDisplayedOf(String selectedDay)
			throws Throwable {

		// get all expected hours values for a day
		String[] arrValues = CommonObjectRepository
				.getExpectedHourValues(selectedDay);
		currentDaySelected = selectedDay;

		// get all actual hour values for a day by iterating through web
		// elements
		for (int i = 1; i <= arrValues.length; i++) {
			String actualHourValue = CommonObjectRepository
					.getHourValueByRowIndex(selectedDay, String.valueOf(i));
			String expeccteHourValue = arrValues[i - 1];
			// Throw error in case values don't match
			if (!actualHourValue.equals(expeccteHourValue))
				throw new Exception("Expected Hour : " + expeccteHourValue
						+ " Actual Hour Displayed :" + actualHourValue);

		}

	}

	@Then("^Summary of all passed hours is hidden$")
	public void summaryOfAllPassedHoursIsHidden() throws Throwable {

		String[] arrValues = CommonObjectRepository
				.getExpectedHourValues(currentDaySelected);

		for (int i = 1; i <= arrValues.length; i++) {
			String actualHourValue = CommonObjectRepository
					.getHourValueByRowIndex(currentDaySelected,
							String.valueOf(i));
			String expeccteHourValue = arrValues[i - 1];
			if (!actualHourValue.equals(expeccteHourValue))
				throw new Exception("Expected Hour : " + expeccteHourValue
						+ " Actual Hour Displayed :" + actualHourValue);

		}
	}

	@And("^Group by (\\d+) hours$")
	public void groupByHours(int groupby) throws Throwable {

		String[] arrValues = CommonObjectRepository
				.getExpectedHourValues(currentDaySelected);
		for (int i = 1; i <= arrValues.length - 1; i++) {
			// check the hours displayed are three hours apart
			if (Integer.parseInt(arrValues[i]) % 3 != 1)
				throw new Exception("Not difference of 3 hours");
		}
	}

	@When("^Enter city \"([^\"]*)\"$")
	public void Enter_city(String cityName) throws Throwable {

		String locator = "//input[@id='city']";

		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath(locator)));
		driver.findElement(By.xpath(locator)).clear();
		driver.findElement(By.xpath(locator)).sendKeys(cityName);

	}

	@When("^press Enter$")
	public void press_Enter() throws Throwable {

		driver.findElement(By.xpath("//input[@id='city']"))
				.sendKeys(Keys.ENTER);
	}

	@Then("^Weather report for \"([^\"]*)\" days should be displayed$")
	public void Weather_report_for_days_should_be_displayed(String intDays)
			throws Throwable {

		String locator = "//span[contains(@data-test,'date-')]";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath(locator)));
		// check number of dates displayed
		if (Integer.parseInt(intDays) != driver.findElements(By.xpath(locator))
				.size())
			throw new Exception("Number of Days displayed is :"
					+ driver.findElements(By.xpath(locator)).size());
	}

	@Then("^Displayed dates are consecutive days$")
	public void Displayed_dates_are_consecutive_days() throws Throwable {

		String locator = "//span[contains(@data-test,'date-')]";
		ArrayList<String> vals = new ArrayList<String>();
		// Store all dates displayed in an array from web elements
		for (WebElement ele : driver.findElements(By.xpath(locator))) {
			vals.add(ele.getText());
		}
		// check if the number of dates displayed is 5
		if (vals.size() != 5)
			throw new Exception("Number of dates displayed is not 5");

		// Check the dates displayed in order
		for (int i = 20; i < 25; i++) {

			if (Integer.parseInt(vals.get(i - 20)) != i)
				throw new Exception("Dates are not Sequential");
		}

	}

	@Then("^Starts from Today$")
	public void Starts_from_Today() throws Throwable {

		String locator = "//span[contains(@data-test,'date-1')]";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath(locator)));

		if (Integer.parseInt(driver.findElement(By.xpath(locator)).getText()) != 20)
			throw new Exception("First days is not Today :");
	}

	@Then("^Error must be displayed$")
	public void Error_must_be_displayed() throws Throwable {

		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath("//div[text()='Error retrieving the forecast']")));
		Close_Browser();
	}

	@Then("^Hours panel of  \"([^\"]*)\" should be displayed$")
	public void Hours_panel_of_should_be_displayed(String day) throws Throwable {

		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath(CommonObjectRepository.getHoursPanelLocator(day, true))));

	}

	@Then("^Hours panel of \"([^\"]*)\" is closed$")
	public void Hours_panel_of_is_closed(String day) throws Throwable {

		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath(CommonObjectRepository.getHoursPanelLocator(day, false))));
		Close_Browser();
	}

	@Then("^Max and min temperatures are displayed$")
	public void Max_and_min_temperatures_are_displayed() throws Throwable {

		String selectedRowIndex = CommonObjectRepository
				.getRowIndexOfADay(currentDaySelected);
		int expectedMaxTemmp = CommonObjectRepository
				.getMaxTempValuesDisplayed(currentDaySelected);
		int expectedMinTemp = CommonObjectRepository
				.getMinTempValuesDisplayed(currentDaySelected);

		String locator = "//span[@data-test='maximum-%1$s']";

		int actualMaxTemp = Integer
				.parseInt(driver
						.findElement(
								By.xpath(String.format(locator,
										selectedRowIndex))).getText()
						.replace("°", ""));
		locator = "//span[@data-test='minimum-%1$s']";
		int actualMinTemp = Integer
				.parseInt(driver
						.findElement(
								By.xpath(String.format(locator,
										selectedRowIndex))).getText()
						.replace("°", ""));

		System.out.println("expectedMaxTemmp :" + expectedMaxTemmp);
		System.out.println("expectedMinTemp :" + expectedMinTemp);
		System.out.println("actualMaxTemp :" + actualMaxTemp);
		System.out.println("actualMinTemp :" + actualMinTemp);

		if (expectedMaxTemmp != actualMaxTemp)
			throw new Exception("Expected Max tempertires are not matching");
		if (expectedMinTemp != actualMinTemp)
			throw new Exception("Expected Min tempertires are not matching");

	}

	@Then("^Windspeed and directions are displayed$")
	public void Windspeed_and_directions_are_displayed() throws Throwable {

		String[] arrValues = CommonObjectRepository
				.getExpectedHourValues(currentDaySelected);
		int actualCount = CommonObjectRepository
				.getNumberOfItemsWithWindSpeedAndDirection(currentDaySelected);
		if (actualCount != arrValues.length) {
			throw new Exception(
					"Number of items (Both Windspeed & Direction) displayed not matching with  3 hourly values , Expected : "
							+ arrValues.length + " Actual : " + actualCount);
		}
	}

	@Then("^Aggregate rainfall is displayed$")
	public void Total_rainfall_and_Pressure_are_displayed() throws Throwable {
		String selectedRowIndex = CommonObjectRepository
				.getRowIndexOfADay(currentDaySelected);
		int expectedAgrRainfall = CommonObjectRepository
				.getAggregateRainfallValuesDisplayed(currentDaySelected);
		String locator = "//span[@data-test='rainfall-%1$s']";
		int actualAgrRainfall = Integer
				.parseInt(driver
						.findElement(
								By.xpath(String.format(locator,
										selectedRowIndex))).getText()
						.replace("mm", ""));
		System.out.println("expectedAgrRainfall : " + expectedAgrRainfall);
		System.out.println("actualAgrRainfall : " + actualAgrRainfall);
		if (expectedAgrRainfall != actualAgrRainfall)
			throw new Exception("Rainfalls are not matching");

	}

	@Then("^All numeric values should be rounded down$")
	public void All_numeric_values_should_be_rounded_down() throws Throwable {

		String[] maxAndMinTemp = CommonObjectRepository
				.getMaxAndMinTempValues(currentDaySelected);

		for (int i = 0; i < maxAndMinTemp.length; i++) {
			try {
				Integer.parseInt(maxAndMinTemp[i]);
			} catch (NumberFormatException e) {

				throw new Exception(maxAndMinTemp[i] + " is not whole number");
			}
		}

		Close_Browser();

	}

	@Then("^Close Browser$")
	public void Close_Browser() throws Throwable {

		driver.close();
	}

	public static WebDriver getDriverInstance() {
		return driver;
	}

}
