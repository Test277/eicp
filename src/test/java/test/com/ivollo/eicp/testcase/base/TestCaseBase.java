package test.com.ivollo.eicp.testcase.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import test.EicpApplication;

import java.lang.reflect.InvocationTargetException;

/**
 * 初始化测试环境
 */
@ContextConfiguration(classes = EicpApplication.class)
@Listeners(test.com.ivollo.eicp.config.TestCaseLisener.class)
public abstract class TestCaseBase extends AbstractTestNGSpringContextTests {

    @Autowired
    public WebTestClient webTestClient;

    /**
     * 发送请求
     * @return 返回response
     */
    public abstract EntityExchangeResult<String> sendRequest();

    @BeforeSuite
    public void beforeSuite(){ }

    @BeforeClass
    public void beforeClass() throws IllegalAccessException, InstantiationException, InvocationTargetException {}

    @AfterSuite
    public void afterSuite(){ }
}