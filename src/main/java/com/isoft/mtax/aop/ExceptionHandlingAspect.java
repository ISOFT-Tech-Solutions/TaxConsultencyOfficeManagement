package com.isoft.mtax.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class ExceptionHandlingAspect {
    @AfterThrowing(pointcut = "execution(* com.isoft.mtax.service.*.*(..))", throwing = "error")
    public void logException(JoinPoint joinPoint,Throwable error){
        log.info("Exception in "+joinPoint.getSignature().getName()+"with cause "+error.getCause());
    }
}
