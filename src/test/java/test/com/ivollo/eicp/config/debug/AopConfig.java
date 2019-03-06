package test.com.ivollo.eicp.config.debug;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopConfig {
    private Logger logger = LoggerFactory.getLogger(AopConfig.class);

    //@AfterReturning(value = "target(test.com.ivollo.eicp.http.WebTestConfig)", returning = "response")
    public void afterRequest(String response){
        System.out.println(222);
        logger.info(String.format("----Sending request: %s----------------"), response);
    }


    @AfterReturning(value = "execution(* test.com.ivollo.eicp.testcase.AopTestImpl.add(..))", returning = "str")
    public void afterAopTest(String str){
        System.out.println("after returnning");
    }
}
