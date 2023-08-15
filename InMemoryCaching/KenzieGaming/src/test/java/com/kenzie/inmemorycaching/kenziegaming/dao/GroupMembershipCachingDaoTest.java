package com.kenzie.inmemorycaching.kenziegaming.dao;

import com.kenzie.inmemorycaching.kenziegaming.dao.models.GroupMembershipCacheKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class GroupMembershipCachingDaoTest {
    @Mock
    private GroupMembershipDao membershipDao;

    // The unit under test
    @InjectMocks
    private GroupMembershipCachingDao cachingMembershipDao;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }

    // Rename this method
    @Test
    public void test1() {
        // Implement your test here
        String userId = "user123";
        String groupId = "group456";

        GroupMembershipCacheKey cacheKey = new GroupMembershipCacheKey(userId, groupId);


        // Mock the behavior of the delegateDao
        when(membershipDao.isUserInGroup(userId, groupId)).thenReturn(true);

        // WHEN
        boolean result1 = cachingMembershipDao.isUserInGroup(cacheKey);
        boolean result2 = cachingMembershipDao.isUserInGroup(cacheKey);


        // Verify that the delegateDao was called only once
        verify(membershipDao, times(1)).isUserInGroup(cacheKey);
    }

    // Rename this method
    @Test
    public void test2() {
        // Implement your test here
        // GIVEN
        String userId = "user789";
        String groupId = "group101";
        GroupMembershipCacheKey cacheKey = new GroupMembershipCacheKey(userId, groupId);

        // Mock the behavior of the delegateDao
        when(membershipDao.isUserInGroup(cacheKey)).thenReturn(false);
        boolean result = membershipDao.isUserInGroup(cacheKey);
        // WHEN

        // Verify that the delegateDao was called only once
        verify(membershipDao, times(1)).isUserInGroup(cacheKey);

    }
}



