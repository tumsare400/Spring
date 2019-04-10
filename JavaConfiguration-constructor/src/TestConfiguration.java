package com.capgemini.spring.annoted;

import com.capgemini.spring.provider.*;
import com.capgemini.spring.renderer.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration{
  
  @Lazy
  @Bean
  public MessageProvider provider(){
    return  new HWMessageProvider();
  }

  @Bean
  public MessageRenderer renderer(){
      MessageRenderer renderer = new  MessageRenderer(provider());
    
      return renderer;
  }
}