package org.bamboo.security;


import org.bamboo.security.filter.CheckTokenFilter;
import org.bamboo.security.handler.AnonymousAuthenticationHandler;
import org.bamboo.security.handler.CustomerAccessDeniedHandler;
import org.bamboo.security.handler.LoginFailureHandler;
import org.bamboo.security.handler.LoginSuccessHandler;
import org.bamboo.security.service.CustomerUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends SecurityConfigurerAdapter {

    @Resource
    private LoginSuccessHandler loginSuccessHandler;
    @Resource
    private LoginFailureHandler loginFailureHandler;
    @Resource
    private AnonymousAuthenticationHandler anonymousAuthenticationHandler;
    @Resource
    private CustomerAccessDeniedHandler customerAccessDeniedHandler;
    @Resource
    private CustomerUserDetailsService customerUserDetailsService;

    @Resource
    private CheckTokenFilter checkTokenFilter;

    /**
     * 注入加密类
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 处理登录认证
     * @param http
     * @throws Exception
     */

    protected void configure(HttpSecurity http) throws Exception {
        //登录前进行过滤
        http.addFilterBefore(checkTokenFilter, UsernamePasswordAuthenticationFilter.class);
        //登录过程处理
        http.formLogin()            //表单登录
                .loginProcessingUrl("/api/user/login")  //登录请求url地址，自定义即可
                .successHandler(loginSuccessHandler)   //认证成功处理器
                .failureHandler(loginFailureHandler)   //认证失败处理器
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //不创建Session
                .and()
                .authorizeRequests()    //设置需要拦截的请求
//                .dispatcherTypeMatchers("/api/user/login").permitAll() //登录请求放行(不拦截)
                .anyRequest().authenticated()   //其它一律请求都需要进行身份认证
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(anonymousAuthenticationHandler)     //匿名无权限访问
                .accessDeniedHandler(customerAccessDeniedHandler)          //认证用户无权限访问
                .and()
                .cors();    //支持跨域请求
    }

    /**
     * 配置认证处理器
     * @param auth
     * @throws Exception
     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(customerUserDetailsService).passwordEncoder(passwordEncoder());
//    }

//    @Override
//    public void init(SecurityBuilder builder) throws Exception {
//
//    }
//
//    @Override
//    public void configure(SecurityBuilder builder) throws Exception {
//    }
}
