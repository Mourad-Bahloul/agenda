package com.hc.agenda.config;

//import com.fasterxml.jackson.databind.ser.impl.LookupCache;
import com.fasterxml.jackson.databind.util.LookupCache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;
import java.util.Arrays;
import java.util.List;

//@Configuration
//@EnableCaching
public class CacheConfig {

    @Bean(name = "cacheManager")
    public CacheManager cacheManager() {
        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager cacheManager = cachingProvider.getCacheManager();
        /*for (LookupCache lookupCache : LookupCache.values()) {
            String cacheName = lookupCache.getName();
            MutableConfiguration<Object, Object> config = new MutableConfiguration<>();
            config.setTypes(Object.class, Object.class);

            Cache<Object, Object> cache = cacheManager.createCache(cacheName, config);
            lookupCache.setCache(cache);
        }*/

        return cacheManager;
    }

}
