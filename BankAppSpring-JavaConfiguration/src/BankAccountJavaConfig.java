package com.capgemini.bankapp.config;



import org.springframework.context.annotation.*;

import org.springframework.context.annotation.Configuration;

import com.capgemini.bankapp.dao.impl.BankAccountDaoImpl;

import com.capgemini.bankapp.dao.BankAccountDao;

import com.capgemini.bankapp.service.impl.BankAccountServiceImpl;

import com.capgemini.bankapp.service.BankAccountService;

import com.capgemini.bankapp.util.DbUtil;

import java.sql.Connection;



@Configuration

@PropertySource("classpath:dbConfig.properties")

public class BankAccountJavaConfig {

	
	@Bean
	
	public Connection connection(){
	
		return DbUtil.getConnection();
	
	}

	
	@Bean
	
	public BankAccountDao bankAccountDao()
	{
	
		BankAccountDaoImpl bankAccountDao = new BankAccountDaoImpl();
	
		bankAccountDao.setConnection(connection());
	
		return bankAccountDao;	
	
	}
	
	
	@Bean
	
	public BankAccountService bankAccountService()
	{
	
		BankAccountServiceImpl bankAccountService = new BankAccountServiceImpl();

		bankAccountService.setBankAccountDao(bankAccountDao());	
	
		return bankAccountService;
	
	}


}