package com.capgemini.spring.annoted;

import com.capgemini.spring.provider.*;
import com.capgemini.spring.renderer.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration{

  
  @Bean
  public MessageProvider provider(){
    return  new GMMessageProvider();
  }

  @Bean
  public MessageRenderer renderer(){
      MessageRenderer renderer = new  MessageRenderer();
      renderer.setMessageProvider(provider());
      return renderer;
  }
}