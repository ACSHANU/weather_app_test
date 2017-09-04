package com.weatherapp;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@Cucumber.Options(
        features = {"src/test/resources"},
        glue = {"com.weatherapp"},
        format = {"pretty", "html:target/Destination"}

)
public class runTests {}