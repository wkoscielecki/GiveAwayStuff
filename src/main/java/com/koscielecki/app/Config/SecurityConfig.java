package com.koscielecki.app.Config;
import com.koscielecki.app.Service.DataUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableTransactionManagement
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public DataUserDetailService customUserDetailsService() {
        return new DataUserDetailService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http)throws Exception{

        http.authorizeRequests()
                .antMatchers("/").hasAnyRole("USER","ADMIN")
                .antMatchers("/userList").hasAnyRole("ADMIN")
                .antMatchers("/login").permitAll()
                .antMatchers("/register").permitAll()

                .and().csrf().disable()
                .formLogin().loginPage("/login")
                .defaultSuccessUrl("/",true).usernameParameter("email").passwordParameter("password")

                .permitAll();
    }

    public void configure(WebSecurity webSec) throws Exception {
        webSec.ignoring()
                .antMatchers("/resources/**","/templates/**", "/static/**", "/css/**", "/js/**", "/images/**", "/incl/**");
    }
}
