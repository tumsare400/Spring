package com.capgemini.spring.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.capgemini.spring.Organization;

public class OrganizationMain{

     public static void main(String[] args){
     ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
     Organization Org = (Organization )context.getBean("Org");

     /*Org.getorgId();
     Org.getorgName();
     Org.getmarketPrice();
     Org.getaddress();

     Org.getdirectors();
     Org.getbranches();
     Org.getbranchWiseHead();
     Org. getipAddresses();
     Org.getdatabaseDetails();*/

      System.out.println(Org);
      System.out.println(Org.getaddress());
    }
}


