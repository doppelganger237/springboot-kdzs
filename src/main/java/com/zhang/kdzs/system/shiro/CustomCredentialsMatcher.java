package com.zhang.kdzs.system.shiro;

import lombok.Setter;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class CustomCredentialsMatcher extends HashedCredentialsMatcher{


    private Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Setter
    private Integer errorPasswordTimes=5;
    

    private Cache<String, AtomicInteger> passwordRetryCache;
    public CustomCredentialsMatcher(CacheManager cacheManager) {
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }
    
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
    	   String username = (String) token.getPrincipal();

           AtomicInteger retryCount = passwordRetryCache.get(username);
           
           if (retryCount == null) {
               retryCount = new AtomicInteger(0);
               passwordRetryCache.put(username, retryCount);
           }
           if (retryCount.incrementAndGet() > errorPasswordTimes) {
               throw new LockedAccountException();
           }

           boolean matches = super.doCredentialsMatch(token, info);
           if (matches) {
               // clear retry count
               passwordRetryCache.remove(username);
           }else {
        	   
        	   logger.info(username+"登录密码错误次数:"+retryCount);
           }
           
           return matches;
  
    }

}
