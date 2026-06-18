package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utils.ExtentManager;

public class ExtentTestListener implements ITestListener {
    private static ExtentReports extent = ExtentManager.getInstance();

    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest extentTest =
                extent.createTest(
                        result.getTestClass().getName()
                        + " - "
                        + result.getMethod().getMethodName());

        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        ExtentTest extentTest = test.get();

        if (extentTest != null) {
            extentTest.pass("Test Passed");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {

        ExtentTest extentTest = test.get();

        if (extentTest != null) {
            extentTest.fail(result.getThrowable());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        ExtentTest extentTest = test.get();

        if (extentTest != null) {
            extentTest.skip("Test Skipped");
        }
    }

    @Override
    public void onFinish(ITestContext context) {

        extent.flush();
    }
}
