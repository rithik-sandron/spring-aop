package io.dev;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
// REFER NOTES APP FOR DOCS - by Rithik
public class LogAspect {

    // @Before annotation indicates this advice method runds before the actual method is run
    // argument inside @Before annotation are Pointcuts
    // JoinPoint provides info about methods that are executed
    @Before("withinApplication()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("LOG: " + joinPoint.toString());
    }

    //@After runs after every method irrespective of success of failure
    @After("withinApplication()")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("LOG: " + joinPoint.toString());
    }

    // @AfterReturning runs after method successfuly runs
    // getArgs() shows the argument
    @AfterReturning("withinApplication()")
    public void logAfterSuccess(JoinPoint joinPoint) {
        System.out.println("LOG: after success " + joinPoint.toShortString());
        // get all args
        Object[] args = joinPoint.getArgs();
    }

    // returning returns return object of method
    @AfterReturning(pointcut = "withinApplication()", returning= "a")
    public void logAfterCatchReturn(String a) {
        System.out.println(a);
    }

    // @AfterThrowing runs after a mthod returns error or exception
    @AfterThrowing("withinApplication()")
    public void logAfterException(JoinPoint joinPoint) {
        System.out.println("LOG: after error " + joinPoint.toLongString());
    }   

    // returning returns return object of method
    @AfterThrowing(pointcut = "withinApplication()", throwing = "a")
    public void logAfterCatchThrow(String a) {
        System.out.println(a);
    }

    @Before("beforeHello()")
    public void log2() {
        System.out.println("specific hello method log!");
    }

    // @Around runs around methods -> both before and after at once
    // @annotation uses custom annotation that we create around methods that are annotated with the annotaion ex: Log annotaiton
    @Around("@annotation(Log)")
    public void around(ProceedingJoinPoint pjp) {
        try {
            System.out.println("annotated");
            pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        
    }  

    // pointcuts are place holders to reduxwe redundency and provides fexibilty. pointcuts can be combined with && 
    // execution takes in methods 
    // .. double dot is methods with or without args
    @Pointcut("execution(* hello(..))")
    public void beforeHello() { }

    // within point cut takes in classes or packages
    @Pointcut("within(io.dev.*)")
    public void withinApplication() { }

}
