package com.zhang.kdzs.common.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.zhang.kdzs.system.shiro.AuthRealm;
import com.zhang.kdzs.system.shiro.CustomCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /*	@Bean
        public ServletContextInitializer servletContextInitializer1() {
            return new ServletContextInitializer() {
                @Override
                public void onStartup(ServletContext servletContext) throws ServletException {
                    servletContext.setSessionTrackingModes(Collections.singleton(SessionTrackingMode.COOKIE) );
                }
            };
        }*/
    @Bean
    public Realm authRealm(CustomCredentialsMatcher retryLimitCredentialsMatcher) {

        AuthRealm realm = new AuthRealm();
        realm.setCredentialsMatcher(retryLimitCredentialsMatcher);
        return realm;
    }

    @Bean
    public SecurityManager securityManager(CustomCredentialsMatcher retryLimitCredentialsMatcher) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(authRealm(retryLimitCredentialsMatcher));
        securityManager.setRememberMeManager(rememberMeManager());
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }
    
    /**
    * @Description:  生成有效期为7天的RememberMeCookie
    * @Param: []
    * @return: org.apache.shiro.web.servlet.SimpleCookie
    * @date: 2019/8/8
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(7 * 24 * 60 * 60);//7天
        return simpleCookie;
    }

    @Bean
    public CookieRememberMeManager rememberMeManager() {
		/*CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		cookieRememberMeManager.setCookie(rememberCookie());
		
		
		Base64CipherKey = StringUtils.isEmpty(Base64CipherKey) ? "f/SY5TIve5WWzT4aQlABJA==" : Base64CipherKey;
		byte[] cipherKey = Base64.decode(Base64CipherKey);
		
		
		cookieRememberMeManager.setCipherService(new AesCipherService());
		cookieRememberMeManager.setCipherKey(cipherKey);
		
		return cookieRememberMeManager;*/
		/*		  KeyGenerator keygen = null;
				try {
					keygen = KeyGenerator.getInstance("AES");
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		SecretKey deskey = keygen.generateKey();
		System.out.println(Base64.encodeToString(deskey.getEncoded()));
		
				*/
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey(Base64.decode("YystomRZLMUjiK0Q1+LFdw=="));
        return cookieRememberMeManager;
    }

    /**
    * @Description:  开启Shiro的注解支持
    * @Param: [securityManager]
    * @return: org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor
    * @date: 2019/8/8
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/login");

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/layui/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/lib/**", "anon");

        filterChainDefinitionMap.put("/**", "user");
        filterChainDefinitionMap.put("/**/**", "user");

        shiroFilterFactoryBean.setUnauthorizedUrl("/error/403");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        defaultWebSessionManager.setSessionIdUrlRewritingEnabled(false);
        defaultWebSessionManager.setGlobalSessionTimeout(1800000);
        defaultWebSessionManager.setSessionDAO(sessionDAO());
        return defaultWebSessionManager;
    }

    @Bean
    public SessionDAO sessionDAO() {
		/*		EnterpriseCacheSessionDAO sessionDAO = new EnterpriseCacheSessionDAO();
				sessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
				sessionDAO.setSessionIdGenerator(new JavaUuidSessionIdGenerator());
				return sessionDAO;*/
        return new EnterpriseCacheSessionDAO();
    }

    @Bean
    public SimpleCookie simpleCookie() {
        SimpleCookie simpleCookie = new SimpleCookie();
        //simpleCookie.setName("com.zhang.kdzs.id");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(180000);
        return simpleCookie;
    }

    /**
     * 解决CacheManager的热部署问题
     *
     * @return
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        logger.info("ShiroConfiguration--lifecycleBeanPostProcessor");
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public CacheManager cacheManager() {

        EhCacheManager cacheManager = new EhCacheManager();

        cacheManager.setCacheManagerConfigFile("classpath:config/shiro-ehcache.xml");
        return new EhCacheManager();

    }

    @Bean
    public CustomCredentialsMatcher getCustomCredentialsMatcher() {
        CustomCredentialsMatcher CustomCredentialsMatcher = new CustomCredentialsMatcher(cacheManager());
        CustomCredentialsMatcher.setHashAlgorithmName("MD5");
        CustomCredentialsMatcher.setHashIterations(10);
        return CustomCredentialsMatcher;
    }



}
