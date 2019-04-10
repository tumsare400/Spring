package com.capgemini.spring;

public class Address{
   private String city;
  private String state;
  private String country;
  
       public String getcity(){
           System.out.println("city : "+city);
           return city;
       }
       public void setcity(String city){
           this.city=city;
       }
     
       public String getstate(){
           System.out.println("state : "+state);
           return state;
       }
       public void setstate(String city){
           this.state=state;
       }
 
       public String getcountry(){
           System.out.println(" country : "+ country);
           return  country;
       }
       public void setcountry(String  country){
           this. country= country;
       }
 
}