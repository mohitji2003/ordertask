package com.silver.bars.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LogModule {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Around("@annotation(com.silver.bars.aspect.TrackTime)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
    	Object returnObj = new Object();
        long startTime = System.currentTimeMillis();
        returnObj = joinPoint.proceed();
        long timeTaken = System.currentTimeMillis() - startTime;
        logger.info("Time Taken by {} is {}", joinPoint, timeTaken);
        
        return returnObj;
    }

    @Before("execution(* com.silver.bars.controller.*.*(..))")
    public void before(JoinPoint joinPoint) {
        //Advice
        logger.info("Logging:::: Intercepted Before {}", joinPoint);
    }
    
    @After(value = "execution(* com.silver.bars.controller.*.*(..))")
    public void after(JoinPoint joinPoint) {
        logger.info("Logging:::: Intercepted After {}", joinPoint);
    }
}



