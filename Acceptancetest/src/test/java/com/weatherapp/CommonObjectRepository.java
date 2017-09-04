package com.weatherapp;

/**
 * Created by Duvvuri on 03/09/2017.
 */
import org.openqa.selenium.By;
import com.weatherapp.WeatherForecastSteps;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class CommonObjectRepository {

	public static String[] getExpectedHourValues(String selectedDay) {
		// TODO : use Calendar Object and get All valid Values of Hours in
		// 24 hour format by considering the start time , if today was selected
		ArrayList<String> listHours = new ArrayList<String>();
		if (Integer.parseInt(selectedDay) != 20) {
			listHours.add("0100");
			listHours.add("0400");
			listHours.add("0700");
			listHours.add("1000");
		}

		listHours.add("1300");
		listHours.add("1600");
		listHours.add("1900");
		listHours.add("2200");

		String[] arrHours = listHours.toArray(new String[listHours.size()]);
		return arrHours;
	}

	public static String[] getMaxAndMinTempValues(String day) {

		String locator = "//span[@data-test='maximum-%1$s-%2$s' or @data-test='minimum-%1$s-%2$s' or @data-test='speed-%1$s-%2$s' or @data-test='rainfall-%1$s-%2$s' or @data-test='pressure-%1$s-%2$s']";
		String dayIndex = getRowIndexOfADay(day);
		String[] arrHours = CommonObjectRepository.getExpectedHourValues(day);
		ArrayList<String> tempValues = new ArrayList<String>();

		for (int i = 1; i <= arrHours.length; i++) {
			String loc = String.format(locator, dayIndex, String.valueOf(i));
			int size = WeatherForecastSteps.getDriverInstance()
					.findElements(By.xpath(loc)).size();

			for (int j = 1; j <= size; j++) {
				String value = WeatherForecastSteps.getDriverInstance()
						.findElement(By.xpath("(" + loc + ")[" + j + "]"))
						.getText();
				value = value.replace("°", "");
				value = value.replace("kph", "");
				value = value.replace("mm", "");
				value = value.replace("mb", "");

				tempValues.add(value);
			}

		}
		return tempValues.toArray(new String[tempValues.size()]);
	}

	public static String getRowIndexOfADay(String day) {

		String rowIndex = WeatherForecastSteps
				.getDriverInstance()
				.findElement(
						By.xpath(String.format(
								"//span[text()='%1$s' and @data-test]", day)))
				.getAttribute("data-test");
		rowIndex = rowIndex.split("-")[1];
		return rowIndex;
	}

	public static String getHoursPanelLocator(String day, boolean isVisible) {
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
		return WeatherForecastSteps.getDriverInstance()
				.findElement(By.xpath(locator)).getText();
	}

	public static int getNumberOfItemsWithWindSpeedAndDirection(String day) {

		String index = getRowIndexOfADay(day);
		String locator = "//span[@data-test='date-%1$s']/../../span[2]/../../div[2]/div/span[4]/span[contains(@data-test,'speed-%1$s-')]/../span[contains(@data-test,'direction-%1$s-')]";
		locator = String.format(locator, index);

		return WeatherForecastSteps.getDriverInstance()
				.findElements(By.xpath(locator)).size();

	}

	public static int getNumberOfItemsWithMaxAdnMinTempsDisplayed(String day) {

		String index = getRowIndexOfADay(day);
		String locator = "//span[@data-test='date-%1$s']/../../span[2]/../../div[2]/div/span[3]/span[contains(@data-test,'maximum-%1$s-')]/../span[contains(@data-test,'minimum-%1$s-')]";
		locator = String.format(locator, index);

		return WeatherForecastSteps.getDriverInstance()
				.findElements(By.xpath(locator)).size();

	}

	public static int getMaxTempValuesDisplayed(String day) {

		ArrayList<Integer> arMaxTemp = new ArrayList<Integer>();
		String index = getRowIndexOfADay(day);
		String locator = "//span[@data-test='date-%1$s']/../../span[2]/../../div[2]/div/span[3]/span[contains(@data-test,'maximum-%1$s-')]/../span[contains(@data-test,'minimum-%1$s-')]";
		locator = String.format(locator, index);
		for (WebElement ele : WeatherForecastSteps.getDriverInstance()
				.findElements(By.xpath(locator))) {
			arMaxTemp.add(Integer.parseInt(ele.getText().replace("°", "")));
		}

		Integer[] tempArr = new Integer[arMaxTemp.size()];
		tempArr = arMaxTemp.toArray(tempArr);
		int maxvalue = tempArr[0];
		for (int i = 0; i < tempArr.length; i++) {

			// System.out.println("Temp value :" + tempArr[i]);
			if (tempArr[i] > maxvalue)
				maxvalue = tempArr[i];
		}
		// System.out.println("Max value :" + maxvalue);
		return maxvalue;

	}

	public static int getMinTempValuesDisplayed(String day) {

		ArrayList<Integer> arMaxTemp = new ArrayList<Integer>();
		String index = getRowIndexOfADay(day);
		String locator = "//span[@data-test='date-%1$s']/../../span[2]/../../div[2]/div/span[3]/span[contains(@data-test,'minimum-%1$s-')]/../span[contains(@data-test,'minimum-%1$s-')]";
		locator = String.format(locator, index);

		for (WebElement ele : WeatherForecastSteps.getDriverInstance()
				.findElements(By.xpath(locator))) {
			arMaxTemp.add(Integer.parseInt(ele.getText().replace("°", "")));
		}

		Integer[] tempArr = new Integer[arMaxTemp.size()];
		tempArr = arMaxTemp.toArray(tempArr);
		int minvalue = tempArr[0];
		for (int i = 0; i < tempArr.length; i++) {

			// System.out.println("Temp value :" + tempArr[i]);
			if (tempArr[i] < minvalue)
				minvalue = tempArr[i];
		}
		// System.out.println("min value :" + minvalue);
		return minvalue;

	}

	public static int getAggregateRainfallValuesDisplayed(String day) {

		ArrayList<Integer> arrRainfall = new ArrayList<Integer>();
		String index = getRowIndexOfADay(day);
		String locator = "//span[contains(@data-test,'rainfall-%1$s-')]";
		locator = String.format(locator, index);

		int agrRainfall = 0;
		for (WebElement ele : WeatherForecastSteps.getDriverInstance()
				.findElements(By.xpath(locator))) {
			agrRainfall = agrRainfall
					+ Integer.parseInt(ele.getText().replace("mm", ""));

		}

		return agrRainfall;

	}

	public static int getNumberOfItemsWithRainfallAndPressure(String day) {

		String index = getRowIndexOfADay(day);
		String locator = "//span[@data-test='date-%1$s']/../../span[2]/../../div[2]/div/span[5]/span[contains(@data-test,'rainfall-%1$s-')]/../span[contains(@data-test,'pressure-%1$s-')]";
		locator = String.format(locator, index);

		return WeatherForecastSteps.getDriverInstance()
				.findElements(By.xpath(locator)).size();

	}
}
