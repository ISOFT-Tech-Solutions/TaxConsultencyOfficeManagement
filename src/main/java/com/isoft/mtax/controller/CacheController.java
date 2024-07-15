package com.isoft.mtax.controller;

import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.cache.CacheManager;
import javax.persistence.EntityManagerFactory;

@RestController
@RequestMapping("/api/v1/caches")
public class CacheController {
   @Autowired
   private SessionFactory sessionFactory;
    @Autowired
    private CacheManager cacheManager;
    @GetMapping("/stats")
    public String cacheStatus(){

        Statistics stats = sessionFactory.getStatistics();
        long hitCount = stats.getSecondLevelCacheHitCount();
        long missCount = stats.getSecondLevelCacheMissCount();
        long putCount = stats.getSecondLevelCachePutCount();
        return String.format("Cache Hits: %d, Cache Misses: %d, Cache Puts: %d", hitCount, missCount, putCount);
    }

}
