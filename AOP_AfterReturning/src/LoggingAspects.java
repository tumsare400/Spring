package com.capgemini.spring.aspect;

import org.springframework.stereotype.*;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.AfterReturning;

@Component
@Aspect
public class LoggingAspects{

   // private Logger logger = Logger.getLogger(LoggingAspects.class);

    @Before("execution(* com.capgemini.spring.calculator.Calculator.calculate(..))")
    public void calculate(){
        System.out.println("Before is running");
    }
   @After("execution(* com.capgemini.spring.calculator.Calculator.substraction(..))")
   public void substraction(){
      System.out.println("After is running");
    }

   @Around("execution(* com.capgemini.spring.calculator.Calculator.substraction(..))")
   public void logAround(ProceedingJoinPoint jp)throws Throwable
   {
      Object[] argument = jp.getArgs();

      Integer firstArg  =  Integer.parseInt(argument[0].toString());
      Integer secondArg = Integer.parseInt(argument[1].toString());
        if(secondArg!=0){
           jp.proceed();
            System.out.println("division done");
        }
      else
       {
            System.out.println("No. should not be zero");
       }
   }

 @AfterReturning(pointcut="execution(* com.capgemini.spring.calculator.Calculator.substraction(..))", returning="returnValue")
 public void log(Object returnValue){
          System.out.println(Integer.parseInt(returnValue.toString()));
    }
}