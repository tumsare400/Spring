package com.capgemini.main;

import org.springframework.context.ApplicationContext;
 import com.capgemini.spring.calculation.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main{

  public static void main(String[] args){
     ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
     Calculation cal= (Calculation)context.getBean("calculator");
     cal.calculate(12,12);
     cal.substraction(10,5);
   }
}