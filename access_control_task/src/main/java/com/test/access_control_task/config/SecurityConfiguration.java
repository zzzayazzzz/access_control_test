package com.test.access_control_task.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
 
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
 
    @Bean(name = "pwdEncoder")
    public PasswordEncoder getPasswordEncoder() {
        DelegatingPasswordEncoder delPasswordEncoder = (DelegatingPasswordEncoder) PasswordEncoderFactories
                .createDelegatingPasswordEncoder();
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        delPasswordEncoder.setDefaultPasswordEncoderForMatches(bcryptPasswordEncoder);
        return delPasswordEncoder;
    }
    
    
  
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.httpBasic()
        	.and()
        	.antMatcher("/api/v1/**")
        	.authorizeRequests()
//            	.anyRequest().hasRole("ADMIN")
        		.anyRequest().permitAll()
            	.and()
            	.csrf().disable();

    }

}
