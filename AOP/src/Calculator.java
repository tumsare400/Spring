package com.capgemini.spring.calculator;

import com.capgemini.spring.calculation.*;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*; 

@Component("calculator")
public class Calculator implements Calculation{

  @Override
  public int calculate(int num1, int num2)
  {
   System.out.println(num1+num2);
   
  }
  @Override
  public void substraction(int num1, int num2){
    System.out.println(num1-num2);
  }
}