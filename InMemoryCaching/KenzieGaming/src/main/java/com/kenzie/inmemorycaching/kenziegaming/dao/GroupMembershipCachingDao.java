package com.kenzie.inmemorycaching.kenziegaming.dao;

import javax.inject.Inject;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.kenzie.inmemorycaching.kenziegaming.dao.models.GroupMembershipCacheKey;

import com.google.common.cache.LoadingCache;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class GroupMembershipCachingDao {
    // Create your cache here
    private final LoadingCache<GroupMembershipCacheKey, Boolean> groupCache;
    @Inject
    public GroupMembershipCachingDao(final GroupMembershipDao delegateDao) {
        // Initialize the cache
        groupCache = CacheBuilder.newBuilder()
                .maximumSize(20000)
                .expireAfterWrite(3, TimeUnit.HOURS)
                .build(CacheLoader.from(delegateDao::isUserInGroup));
    }

    // Implement your method here
    public Boolean isUserInGroup(final GroupMembershipCacheKey key){
        return groupCache.getUnchecked(key);
    }
}
