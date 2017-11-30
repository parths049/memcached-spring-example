package com.movie.project;

import net.spy.memcached.ConnectionFactoryBuilder.Protocol;
import net.spy.memcached.spring.MemcachedClientFactoryBean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@ComponentScan("com.movie.project")
public class SpringConfig {
	
	@Value("${mc.servers}")
	private String SERVERS;
	
    @Bean
    public ServletRegistrationBean dispatcherServlet(ApplicationContext applicationContext) {
        DispatcherServlet servlet = new DispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        return new ServletRegistrationBean(servlet, "/*");
    }
    
    @Bean
    public MemcachedClientFactoryBean memcachedClientFactoryBean(){
    	MemcachedClientFactoryBean bean = new MemcachedClientFactoryBean();
    	bean.setServers(SERVERS);
    	bean.setProtocol(Protocol.BINARY);
    	return bean;
    }
}
