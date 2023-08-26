package com.hc.agenda.config;


import org.springframework.context.annotation.Bean;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;

public class CacheConfig {

    @Bean(name = "cacheManager")
    public CacheManager cacheManager() {
        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager cacheManager = cachingProvider.getCacheManager();
        return cacheManager;
    }

}
