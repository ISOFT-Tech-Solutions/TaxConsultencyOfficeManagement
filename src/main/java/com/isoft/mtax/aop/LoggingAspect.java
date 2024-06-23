package com.isoft.mtax.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class LoggingAspect {
    @Before("execution(* com.isoft.mtax.service.*.save(..))")
    public void logBeforeSave(JoinPoint joinPoint){
        log.info("Before Saving "+joinPoint.getSignature().getName());
    }
    @AfterReturning(value = "execution(* com.isoft.mtax.service.*.save(..))",returning = "result")
    public void logAfterSave(JoinPoint joinPoint,Object result){
        log.info("After Saving "+joinPoint.getSignature().getName());
        log.info("Saved Object"+result);
    }

}
