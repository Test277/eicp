package test.com.ivollo.eicp.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.test.web.reactive.server.EntityExchangeResult;

/**
 * log
 */
@Component
@Aspect
public class HttpListener {
    private Logger logger = LoggerFactory.getLogger(HttpListener.class);

    @AfterReturning(value = "execution(* test.com.ivollo.eicp.http.WebTestClientBuilder.returnResult(..))", returning = "result")
    public void afterRequest(EntityExchangeResult<String> result){
        logger.info("Request Info:" + result.toString());
    }

    @Around(value = "@annotation(org.testng.annotations.Test)")
    public void beforeTest(ProceedingJoinPoint pj) throws Throwable {
        StringBuilder caseName = new StringBuilder(pj.getKind());
        caseName.append(".");
        caseName.append(pj.getSignature().getName());
        if(pj.getArgs() != null){
            caseName.append(".");
            caseName.append(pj.getArgs()[0]);
        }
        logger.info("Testcase name = %s", caseName);
        pj.proceed();
    }

    @After(value = "@annotation(org.testng.annotations.Test)")
    public void afterTestSuccess(){
        logger.info("Testcase Passed");
    }

    @AfterThrowing(value = "@annotation(org.testng.annotations.Test)")
    public void afterTestFailed(){
        logger.info("Testcase Failed");
    }

}
