package com.test.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
public class MySecurityConfig2 extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;

    // 注入持久化Token对象
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

    // 1) 创建配置类，注入UserDetailsService实现类
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    // 注入密码加密器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 自定义登录界面
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 自定义登录界面
        http.formLogin()
                // 登录界面设置
                .loginPage("/login.html")
                // 登录访问路径
                .loginProcessingUrl("/login")
                // 登录成功跳转界面
                .defaultSuccessUrl("/index.html").permitAll();
        // 注销
        http.logout().logoutUrl("/logout")
                .logoutSuccessUrl("/index").permitAll();

        // url拦截认证
        http.authorizeRequests()
                // 设置那些可以直接访问，无需认证
                .antMatchers("/","/hello", "/login").permitAll()
                // 对部分路径设置权限，只有test角色有访问 /index2 的权限,
                // 1、hasAuthority设置单角色，对应角色名 test
//                .antMatchers("/index2").hasAuthority("test")
                // 2、hasAnyAuthority 设置多角色，对应角色名 test
//                .antMatchers("/index2").hasAnyAuthority("test", "test2", "test3")
//                .antMatchers("/index2").hasAnyAuthority("test,test2,test3")
//                .antMatchers("/index2").hasAnyAuthority(new String[]{"test", "test2"})
                // 3、hasRole 设置角色，对应角色名 ROLE_test
//                .antMatchers("/index2").hasRole("test")
                // 4、hasAnyRole 设置多角色，对应角色名 ROLE_test
                .antMatchers("/index2").hasAnyRole("test", "test2")
                // 所有其他请求必须认证
                .anyRequest().authenticated();

        // 设置记住我服务
        http.rememberMe().tokenRepository(persistentTokenRepository())
        // 设置时长 60s
        .tokenValiditySeconds(60)
        // 设置用户服务类
        .userDetailsService(userDetailsService);

        // 关闭csrf保护
        http.csrf().disable();

        // 配置没有权限访问跳转自定义界面
        http.exceptionHandling().accessDeniedPage("/unauth.html");

    }
}
