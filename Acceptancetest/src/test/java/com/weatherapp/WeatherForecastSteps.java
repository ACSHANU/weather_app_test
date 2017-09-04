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

import java.util.ArrayList;

public class WeatherForecastSteps extends CommonObjectRepository {

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

		String index = getRowIndexOfADay(day);
		currentDaySelected = day;

		String locator = "//span[@data-test='date-%1$s']";
		locator = String.format(locator, index);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath(locator)));
		driver.findElement(By.xpath(locator)).click();

	}

	@Then("^Summary of all  hours is displayed of \"([^\"]*)\"$")
	public void summaryOfAllHoursIsDisplayedOf(String selectedDay)
			throws Throwable {

		String[] arrValues = getExpectedHourValues(selectedDay);
		currentDaySelected = selectedDay;
		for (int i = 1; i <= arrValues.length; i++) {
			String actualHourValue = getHourValueByRowIndex(selectedDay,
					String.valueOf(i));
			String expeccteHourValue = arrValues[i - 1];
			if (!actualHourValue.equals(expeccteHourValue)) {
				throw new Exception("Expected Hour : " + expeccteHourValue
						+ " Actual Hour Displayed :" + actualHourValue);
			} else
				System.out.println("Expected Hour : " + expeccteHourValue
						+ " Actual Hour Displayed :" + actualHourValue);

		}

	}

	@Then("^Summary of all passed hours is hidden$")
	public void summaryOfAllPassedHoursIsHidden() throws Throwable {

		String[] arrValues = getExpectedHourValues(currentDaySelected);

		for (int i = 1; i <= arrValues.length; i++) {
			String actualHourValue = getHourValueByRowIndex(currentDaySelected,
					String.valueOf(i));
			String expeccteHourValue = arrValues[i - 1];
			if (!actualHourValue.equals(expeccteHourValue)) {
				throw new Exception("Expected Hour : " + expeccteHourValue
						+ " Actual Hour Displayed :" + actualHourValue);
			} else
				System.out.println("Expected Hour : " + expeccteHourValue
						+ " Actual Hour Displayed :" + actualHourValue);

		}
	}

	@And("^Group by (\\d+) hours$")
	public void groupByHours(int groupby) throws Throwable {

		String[] arrValues = getExpectedHourValues(currentDaySelected);
		for (int i = 1; i <= arrValues.length - 1; i++) {

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

		if (Integer.parseInt(intDays) != driver.findElements(By.xpath(locator))
				.size())
			throw new Exception("Number of Days displayed is :"
					+ driver.findElements(By.xpath(locator)).size());
	}

	@Then("^Displayed dates are consecutive days$")
	public void Displayed_dates_are_consecutive_days() throws Throwable {

		String locator = "//span[contains(@data-test,'date-')]";
		ArrayList<String> vals = new ArrayList<String>();
		for (WebElement ele : driver.findElements(By.xpath(locator))) {
			vals.add(ele.getText());
		}
		if (vals.size() != 5)
			throw new Exception("Number of dates displayed is not 5");

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
				.xpath(getHoursPanelLocator(day, true))));

	}

	@Then("^Hours panel of \"([^\"]*)\" is closed$")
	public void Hours_panel_of_is_closed(String day) throws Throwable {

		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath(getHoursPanelLocator(day, false))));
		Close_Browser();
	}

	@Then("^Max and min temperatures are displayed$")
	public void Max_and_min_temperatures_are_displayed() throws Throwable {

		String[] arrValues = getExpectedHourValues(currentDaySelected);
		int actualCount = getNumberOfItemsWithMaxAdnMinTempsDisplayed(currentDaySelected);
		if (actualCount != arrValues.length) {
			throw new Exception(
					"Number of items (Both Maximum & Mimimum Tempratures) displayed not matching with  3 hourly values , Expected : ");
		}
	}

	@Then("^Windspeed and directions are displayed$")
	public void Windspeed_and_directions_are_displayed() throws Throwable {

		String[] arrValues = getExpectedHourValues(currentDaySelected);
		int actualCount = getNumberOfItemsWithWindSpeedAndDirection(currentDaySelected);
		if (actualCount != arrValues.length) {
			throw new Exception(
					"Number of items (Both Windspeed & Direction) displayed not matching with  3 hourly values , Expected : "
							+ arrValues.length + " Actual : " + actualCount);
		}
	}

	@Then("^Total rainfall and Pressure are displayed$")
	public void Total_rainfall_and_Pressure_are_displayed() throws Throwable {

		String[] arrValues = getExpectedHourValues(currentDaySelected);
		int actualCount = getNumberOfItemsWithRainfallAndPressure(currentDaySelected);
		if (actualCount != arrValues.length) {
			throw new Exception(
					"Number of items (Both Rainfall & Pressure) displayed not matching with  3 hourly values , Expected : "
							+ arrValues.length + " Actual : " + actualCount);
		}
	}

	@Then("^All numeric values should be rounded down$")
	public void All_numeric_values_should_be_rounded_down() throws Throwable {

		String[] arrValues = getExpectedHourValues(currentDaySelected);
		String[] maxAndMinTemp = getMaxAndMinTempValues(currentDaySelected);
		for (int i = 0; i < maxAndMinTemp.length; i++) {
			try {
				Integer.parseInt(maxAndMinTemp[i]);
			} catch (NumberFormatException e) {

				throw new Exception(maxAndMinTemp[i] + " is not whole number");
			}
		}

		Close_Browser();

	}

	public static String[] getMaxAndMinTempValues(String day) {

		String locator = "//span[@data-test='maximum-%1$s-%2$s' or @data-test='minimum-%1$s-%2$s' or @data-test='speed-%1$s-%2$s' or @data-test='rainfall-%1$s-%2$s' or @data-test='pressure-%1$s-%2$s']";
		String dayIndex = getRowIndexOfADay(day);
		String[] arrHours = getExpectedHourValues(day);
		ArrayList<String> tempValues = new ArrayList<String>();

		for (int i = 1; i <= arrHours.length; i++) {
			String loc = String.format(locator, dayIndex, String.valueOf(i));
			int size = driver.findElements(By.xpath(loc)).size();

			for (int j = 1; j <= size; j++) {
				String value = driver.findElement(
						By.xpath("(" + loc + ")[" + j + "]")).getText();
				value = value.replace("°", "");
				value = value.replace("kph", "");
				value = value.replace("mm", "");
				value = value.replace("mb", "");

				tempValues.add(value);
			}

		}
		return tempValues.toArray(new String[tempValues.size()]);
	}

	@Then("^Close Browser$")
	public void Close_Browser() throws Throwable {

		driver.close();
	}

	public static String getRowIndexOfADay(String day) {

		String rowIndex = driver.findElement(
				By.xpath(String.format("//span[text()='%1$s' and @data-test]",
						day))).getAttribute("data-test");
		rowIndex = rowIndex.split("-")[1];
		return rowIndex;
	}

	private static String getHoursPanelLocator(String day, boolean isVisible) {
		String dayRowIndex = getRowIndexOfADay(day);
		String panelSize = "max-height: 2000px";
		if (!isVisible)
			panelSize = "max-height: 0px";

		String locator = String
				.format("//span[@data-test='day-%1$s']/../../../div[@class='details' and contains(@style,'%2$s')]",
						dayRowIndex, panelSize);

		return locator;
	}

	public static String getHourValueByRowIndex(String day, String hourRowIndex) {

		String dayRowIndex = getRowIndexOfADay(day);
		String locator = "//span[@data-test='hour-%1$s-%2$s']";
		locator = String.format(locator, dayRowIndex, hourRowIndex);
		return driver.findElement(By.xpath(locator)).getText();
	}

	public static int getNumberOfItemsWithWindSpeedAndDirection(String day) {

		String index = getRowIndexOfADay(day);
		String locator = "//span[@data-test='date-%1$s']/../../span[2]/../../div[2]/div/span[4]/span[contains(@data-test,'speed-%1$s-')]/../span[contains(@data-test,'direction-%1$s-')]";
		locator = String.format(locator, index);

		return driver.findElements(By.xpath(locator)).size();

	}

	public static int getNumberOfItemsWithMaxAdnMinTempsDisplayed(String day) {

		String index = getRowIndexOfADay(day);
		String locator = "//span[@data-test='date-%1$s']/../../span[2]/../../div[2]/div/span[3]/span[contains(@data-test,'maximum-%1$s-')]/../span[contains(@data-test,'minimum-%1$s-')]";
		locator = String.format(locator, index);

		return driver.findElements(By.xpath(locator)).size();

	}

	public static int getNumberOfItemsWithRainfallAndPressure(String day) {

		String index = getRowIndexOfADay(day);
		String locator = "//span[@data-test='date-%1$s']/../../span[2]/../../div[2]/div/span[5]/span[contains(@data-test,'rainfall-%1$s-')]/../span[contains(@data-test,'pressure-%1$s-')]";
		locator = String.format(locator, index);

		return driver.findElements(By.xpath(locator)).size();

	}
}
