package com.hc.agenda;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features/",
        plugin = {"pretty"},
        glue = { "com.hc.train.cucumber.cucumberglue" })
public class CucumberIntegrationTest {
}
