package com.isoft.mtax.config;

import com.isoft.mtax.constants.CsvConstants;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class MessageSourceConfig {
    public MessageSource messageSource(){
        ResourceBundleMessageSource messageSource=new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding(CsvConstants.DEFUALTENCODING);
        return messageSource;
    }
}
