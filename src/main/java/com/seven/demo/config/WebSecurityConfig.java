package com.seven.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.seven.demo.service.impl.UserDetailsServiceImpl;


@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启security注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {
	
	/*@Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }*/


    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.csrf().disable();
        //允许所有用户访问"/index"
        http.authorizeRequests()
                .antMatchers("/index","/register","/home").permitAll()
                //其他地址的访问均需验证权限
                .anyRequest().authenticated()
                .and()
            .formLogin()
                //指定登录页是"/login"
                .loginPage("/login")
                .defaultSuccessUrl("/seven/note")//登录成功后默认跳转到"/home"
                .permitAll()
                .and()
            .logout()
                .logoutSuccessUrl("/home")//退出登录后的默认url是"/login"
                .permitAll();

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/js/**","/css/**","/img/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth
            .userDetailsService(userDetailsService())
            .passwordEncoder(passwordEncoder());

    }

    /**
     * 设置用户密码的加密方式为MD5加密
     * @return
     */
    /*@Bean
    public Md5PasswordEncoder passwordEncoder() {
        return new Md5PasswordEncoder();

    }*/
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }

    /**
     * 自定义UserDetailsService，从数据库中读取用户信息
     * @return
     */
    @Bean
    public UserDetailsServiceImpl userDetailsService(){
        return new UserDetailsServiceImpl();
    }

}
