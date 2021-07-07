package com.app.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"json:target/cucumber.json",
                "html:target/default-html-reports",
                "rerun: "},
        features = "src/test/resources/features",
        glue = "com/app/stepdefinitions",
        strict = true,
        dryRun = false,
        tags = "@M3-6540"
)
public class CukesRunner {
}
