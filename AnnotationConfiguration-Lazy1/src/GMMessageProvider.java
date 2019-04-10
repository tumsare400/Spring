

package com.capgemini.spring.provider;
import org.springframework.stereotype.*;


public class GMMessageProvider implements MessageProvider{

   public String getMessage(){
    return "Good Morning";
   }
}