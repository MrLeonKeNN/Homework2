package com.homework2.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * The {@link LoggingAspect} class is an aspect for logging method calls in the application.
 * <p>
 * This class uses Aspect-Oriented Programming (AOP) to intercept method calls and log various events such as method
 * entry, exit, return values, and exceptions. It utilizes Log4j  for logging purposes.
 * </p>
 */
@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Logs the method call before its execution.
     *
     * @param joinPoint provides reflective access to both the state available at a join point and static
     *                  information about it.
     */
    @Before("execution(* com.homework2..*(..))")
    public void logMethodCall(JoinPoint joinPoint) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] methodArgs = joinPoint.getArgs();
        String methods = Arrays.toString(methodArgs);

        logger.info("Calling method: {}#{} with arguments: {}", className, methodName, methods);
    }

    /**
     * Logs after the method execution.
     *
     * @param joinPoint provides reflective access to both the state available at a join point and static
     *                  information about it.
     */
    @After("execution(* com.homework2..*(..))")
    public void logAfter(JoinPoint joinPoint) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] methodArgs = joinPoint.getArgs();
        String methods = Arrays.toString(methodArgs);

        logger.debug("Executed method: {}#{} with arguments: {}", className, methodName, methods);
    }

    /**
     * Logs the return value of the method after its successful execution.
     *
     * @param joinPoint provides reflective access to both the state available at a join point and static
     *                  information about it.
     * @param result    the result returned by the method.
     */
    @AfterReturning(pointcut = "execution(* com.homework2..*(..))", returning = "result")
    public void logMethodReturn(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Method {} returned with result: {}", methodName, result);
    }

    /**
     * Logs the exception thrown by the method.
     *
     * @param joinPoint provides reflective access to both the state available at a join point and static
     *                  information about it.
     * @param error     the thrown exception.
     */
    @AfterThrowing(pointcut = "execution(* com.homework2..*(..))", throwing = "error")
    public void logMethodException(JoinPoint joinPoint, Exception error) {
        String methodName = joinPoint.getSignature().getName();
        logger.error("Method {} threw exception: {}", methodName, error.getMessage());
    }
}
