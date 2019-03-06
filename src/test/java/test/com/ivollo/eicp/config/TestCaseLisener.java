package test.com.ivollo.eicp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.*;


public class TestCaseLisener implements IInvokedMethodListener, ITestListener {
    private Logger logger = LoggerFactory.getLogger(TestCaseLisener.class);

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {
    }

    @Override
    public void onTestFailure(ITestResult result) {

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.info("Testcase Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
        logger.info("-------------Test Suite Started--------------------");
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("-------------Test Suite Finished--------------------");
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if(method.isTestMethod()) {
            StringBuilder caseName = new StringBuilder(method.getTestMethod().getTestClass().getName());
            caseName.append(".");
            caseName.append(method.getTestMethod().getMethodName());
            if(testResult.getParameters().length != 0) {
                caseName.append("_");
                caseName.append(testResult.getParameters()[0]);
            }
            logger.info(String.format("TestCase Name = %s", caseName.toString()));
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if(method.isTestMethod()){
            if(testResult.isSuccess())
                logger.info("Test Passed");
            else
                logger.info("Test Failed");
        }
    }
}
