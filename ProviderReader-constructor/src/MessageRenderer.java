package com.capgemini.spring.renderer;

import com.capgemini.spring.provider.*;


public class MessageRenderer{

    private MessageProvider provider;

    public void render(){
     System.out.println(provider.getMessage());
    }

    public  MessageRenderer(MessageProvider provider){
    this.provider = provider;

   }
}