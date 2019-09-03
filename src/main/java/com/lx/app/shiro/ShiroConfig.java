package com.lx.app.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Author: lx
 * @Date: 2019/9/2 20:52
 */
@Configuration
public class ShiroConfig {
    /**
     *  开启shiro aop注解支持.
     *  使用代理方式;所以需要开启代码支持;
     * @param securityManager
     * @return
     */

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


    @Bean
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        //当项目访问其他没有通过认证的URL时，会默认跳转到/login，如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("user/login");
        shiroFilterFactoryBean.setUnauthorizedUrl("/notRole");
        //shiroFilterFactoryBean.setSuccessUrl("/index");
        //设置拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 以下过滤器按顺序判断
        // 配置不会被拦截的链接，一般是排除前端文件（anon:指定的url可以匿名访问）
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");;
        filterChainDefinitionMap.put("/durid/**","anon");
        filterChainDefinitionMap.put("/css/**","anon");
        filterChainDefinitionMap.put("email_templates","anon");
        filterChainDefinitionMap.put("font-awesome","anon");
        filterChainDefinitionMap.put("fonts","anon");
        filterChainDefinitionMap.put("img","anon");
        filterChainDefinitionMap.put("js","anon");
        filterChainDefinitionMap.put("LESS","anon");

        filterChainDefinitionMap.put("/user/login", "anon");
        filterChainDefinitionMap.put("/user/register","anon");
        //当用户访问没有权限的URL时，跳转到未授权界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        //authc:所有url都必须认证通过才可以访问;
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public MyRealm myShiroRealm() {
        MyRealm myShiroRealm = new MyRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }



    /**
     * 当没有访问权限时，会抛出异常，需要自定义异常处理，将没有权限的异常重定向到403页面
     *
     * @return
     */
    @Bean
    public SimpleMappingExceptionResolver
    createSimpleMappingExceptionResolver() {
        System.out.println("自定义异常处理");
        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        mappings.setProperty("UnauthorizedException", "403");//授权异常处理
        resolver.setExceptionMappings(mappings);  // None by default
        resolver.setDefaultErrorView("error");    // No default
        resolver.setExceptionAttribute("ex");     // Default is "exception"
        return resolver;
    }

    /**
     * 凭证匹配器
     * 让SimpleAuthorizationInfo知道如何验证密码
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }
}
