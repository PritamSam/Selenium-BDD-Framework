package hooks;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.io.File;
import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import driver.BrowserManager;
import driver.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import utils.ConfigReader;
import utils.ExtentManager;
import utils.ExtentTestManager;
import utils.LoggerUtils;
import utils.ScreenshotUtils;

public class Hooks {

    private static final Logger log =
            LoggerUtils.getLogger(Hooks.class);

    private static final ExtentReports extent =
            ExtentManager.getInstance();

    @BeforeAll
    public static void beforeSuite() {

        new File("logs").mkdirs();
        new File("reports").mkdirs();
        new File("screenshots").mkdirs();
        new File("test-output").mkdirs();

        String browser =
                System.getProperty("browser", "chrome");

        String url =
                ConfigReader.getProperty("baseUrl");

        long waitTime =
                Long.parseLong(
                        ConfigReader.getProperty("implicitWait"));

        BrowserManager browserManager =
                new BrowserManager();

        WebDriver driver =
                browserManager.createDriver(browser);

        DriverFactory.setDriver(driver);

        driver.manage().window().maximize();

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(waitTime));

        driver.get(url);

        log.info("Browser launched successfully");
    }

    @Before
    public void beforeScenario(Scenario scenario) {

        ExtentTest test =
                extent.createTest(scenario.getName());

        ExtentTestManager.setTest(test);
    }

    @After
    public void afterScenario(Scenario scenario) {

        try {

            ScreenshotUtils.captureScreenshot(
                    DriverFactory.getDriver(),
                    scenario.getName());

            if (scenario.isFailed()) {

                ExtentTestManager.getTest()
                        .fail("Scenario Failed");

            } else {

                ExtentTestManager.getTest()
                        .pass("Scenario Passed");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        ExtentTestManager.unload();
    }

    @AfterAll
    public static void afterSuite() {

        DriverFactory.quitDriver();

        extent.flush();

        System.out.println(
                "Extent Report Generated");
    }
}
