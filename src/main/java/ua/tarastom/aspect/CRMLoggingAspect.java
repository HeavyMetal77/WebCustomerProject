package ua.tarastom.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Order(1)
@Component
public class CRMLoggingAspect {
    // setup logger
    private Logger logger = Logger.getLogger(getClass().getName());

    // setup pointcut declarations
    @Pointcut("execution(* ua.tarastom.controller.*.*(..))")
    private void forControllerPackage() {
    }

    @Pointcut("execution(* ua.tarastom.service.*.*(..))")
    private void forServicePackage() {
    }

    @Pointcut("execution(* ua.tarastom.dao.*.*(..))")
    private void forDaoPackage() {
    }

    //mix combo
    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow() {
    }

    //add before
    @Before("forAppFlow()")
    private void before(JoinPoint joinPoint) {
        //display method we are calling
        String method = joinPoint.getSignature().toShortString();
        logger.info("==========> in @Before: calling method: " + method);

        //display the arguments to the method
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            logger.info("===> argument: " + arg.toString());
        }
    }

    // add @AfterReturning advice
    @AfterReturning(pointcut="forAppFlow()", returning="theResult")
    public void afterReturning(JoinPoint theJoinPoint, Object theResult) {
        // display method we are returning from
        String theMethod = theJoinPoint.getSignature().toShortString();
        logger.info("=====>> in @AfterReturning: from method: " + theMethod);

        // display data returned
        logger.info("=====>> result: " + theResult);
    }
}
