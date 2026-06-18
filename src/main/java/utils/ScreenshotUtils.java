package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;



public class ScreenshotUtils {

    private static final String SCREENSHOT_DIR = "screenshots";

    public static String captureScreenshot(WebDriver driver, String testName) {

        try {
            File directory = new File(SCREENSHOT_DIR);

            if (!directory.exists()) {
                directory.mkdirs();
            }

            String timestamp =
                    new SimpleDateFormat("yyyyMMdd_HHmmss")
                            .format(new Date());

            String filePath = SCREENSHOT_DIR
                    + File.separator
                    + testName
                    + "_"
                    + timestamp
                    + ".png";

            File sourceFile =
                    ((TakesScreenshot) driver)
                            .getScreenshotAs(OutputType.FILE);

            Files.copy(
                    sourceFile.toPath(),
                    new File(filePath).toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );

            return filePath;

        } catch (IOException e) {
            throw new RuntimeException("Unable to capture screenshot", e);
        }
    }

}
