package com.capgemini.spring.provider;
import org.springframework.stereotype.*;


public class HWMessageProvider implements MessageProvider{

   public String getMessage(){
      return "Hello World!!!!";
    }
  
}