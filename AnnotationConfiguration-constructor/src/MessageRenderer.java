package com.capgemini.spring.renderer;

import org.springframework.stereotype.*;

import org.springframework.beans.factory.annotation.*;
import com.capgemini.spring.provider.*;

@Component
public class MessageRenderer{

    private MessageProvider provider;

    @Autowired
    public MessageRenderer(MessageProvider provider){
    this.provider = provider;
    }
     public void render(){
     System.out.println(provider.getMessage());
    }

}