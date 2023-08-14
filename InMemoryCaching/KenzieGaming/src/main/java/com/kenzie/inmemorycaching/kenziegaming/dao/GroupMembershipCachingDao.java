package com.kenzie.inmemorycaching.kenziegaming.dao;

import javax.inject.Inject;

import com.kenzie.inmemorycaching.kenziegaming.dao.models.GroupMembershipCacheKey;

import com.google.common.cache.LoadingCache;

public class GroupMembershipCachingDao {
    // Create your cache here

    @Inject
    public GroupMembershipCachingDao(final GroupMembershipDao delegateDao) {
        // Initialize the cache
    }

    // Implement your method here

}
