package com.capgemini.spring;

import java.util.List;
import java.util.Map;
import java.util.Set;
import  java.util.Properties;

import com.capgemini.spring.Address;
import org.springframework.beans.factory.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.*;
 import javax.annotation.Resource;

@Component("organization")
public class Organization{
       
        @Value("11")
        private int orgId;
        
        @Value("CG")
	private String orgName;

        
        @Value("234566")
	private double marketPrice;

        @Autowired
        @Qualifier("address")
	private Address address;
 
        
        @Resource(name="list")
        private List<String> directors;
 
        @Resource(name="set")
	private Set<String> branches;

        @Resource(name="map")
	private Map<String, String> branchWiseHead;

        @Resource(name="ipAddresses")
	private Properties ipAddresses;

        
        @Resource(name="databaseDetails")
	private Properties databaseDetails;

      public Organization(){
      }

       @Autowired
       @Value
       public Organization(int orgId,
                           String orgName, 
                           double marketPrice, 
                           Address address,  

        List<String> directors, 
        Set<String> branches,
       Map<String, String> branchWiseHead, 
       Properties ipAddresses,
       Properties databaseDetails){

       this.orgId=orgId;
       this.orgName= orgName;
       this.marketPrice = marketPrice;
       this.address=address;
       this.directors = directors;
       this.branches = branches;
       this.branchWiseHead = branchWiseHead;
       this.ipAddresses = ipAddresses;
       this. databaseDetails =  databaseDetails;
      }
       public int getOrgId(){
           //System.out.println("orgId : "+orgId);
           return orgId;
       }
       public void setOrgId(int orgId){
           this.orgId=orgId;
       }
       public String getOrgName(){
            // System.out.println("orgName :"+orgName);
           return orgName;
       }
       public void setOrgName(String orgName){
           this.orgName= orgName;
       }
        public double getMarketPrice(){
            // System.out.println("market price :"+marketPrice);
           return marketPrice;
       }
       public void setMarketPrice(double  marketPrice){
            this.marketPrice = marketPrice;
       }

       public Address getAddress(){
            // System.out.println("address :"+address);
           return address;
       }
       public void setAddress(Address address){
         this.address=address;
       }

      public List<String> getDirectors(){
          //System.out.println("director :"+directors);
           return directors;
       }
       public void setDirectors(List<String> directors){
         this.directors=directors;
       }

       public Set<String> getBranches(){
            //System.out.println("branches :"+branches);
           return branches;
       }
       public  void setBranches(Set<String> branches){
         this.branches=branches;
       }

       public Map<String, String> getBranchWiseHead(){
             //System.out.println(" branchWiseHead :"+ branchWiseHead);
           return branchWiseHead;
       }
       public void  setBranchWiseHead( Map<String, String> branchWiseHead){
         this.branchWiseHead=branchWiseHead;
       }

      
       public Properties  getIpAddresses(){
           //System.out.println("ipAddresses :"+ipAddresses);
           return ipAddresses;
       }
       public  void  setipAddresses(Properties ipAddresses){
         this.ipAddresses=ipAddresses;
       }
    
       
       public Properties getDatabaseDetails(){
           //System.out.println("databaseDetails :"+databaseDetails);
           return databaseDetails;
       }

       public void setDatabaseDetails(Properties databaseDetails){

         this.databaseDetails = databaseDetails;

       }
       @Override
        public String toString(){
        return (orgId+"\n"+orgName+"\n"+marketPrice+"\n"+address+"\n"+directors+"\n"+branches+"\n"+ branchWiseHead+"\n"+ipAddresses+"\n"+databaseDetails+"\n");
    }
}
