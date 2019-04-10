package com.capgemini.spring.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.*;
import com.capgemini.spring.renderer.*;
import com.capgemini.spring.annoted.TestConfiguration;

public class Test {

     public static void main(String[] args){

     ApplicationContext context = new AnnotationConfigApplicationContext(TestConfiguration.class);
     MessageRenderer  renderer = context.getBean("renderer", MessageRenderer.class);
     renderer.render();
     }
}