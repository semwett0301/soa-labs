package com.example.studygroup.configuration;

import org.springframework.context.annotation.Bean;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class JndiConfiguration {
    @Bean
    public Context context() throws NamingException {
        Properties jndiProps = new Properties();
        jndiProps.put("java.naming.factory.initial",
                "org.jboss.naming.remote.client.InitialContextFactory");
        jndiProps.put("jboss.naming.client.ejb.context", true);
        jndiProps.put("java.naming.provider.url",
                "http-remoting://localhost:8080");
        return new InitialContext(jndiProps);
    }
}
