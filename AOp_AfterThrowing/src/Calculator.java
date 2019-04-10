package com.capgemini.spring.calculator;

import com.capgemini.spring.calculation.*;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*; 

@Component("calculator")
public class Calculator implements Calculation{

  
  public int calculate(int num1, int num2)
  {
   System.out.println(num1+num2);
   return (num1+num2);
  }
  
  public void divide(int num1, int num2)throws Exception{
    if(num2!=0)
      System.out.println(num1-num2);
   else
     throw new Exception();
  }
}

