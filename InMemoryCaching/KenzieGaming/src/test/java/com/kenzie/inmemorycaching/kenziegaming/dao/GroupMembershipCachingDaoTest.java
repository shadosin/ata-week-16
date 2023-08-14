package com.kenzie.inmemorycaching.kenziegaming.dao;

import com.kenzie.inmemorycaching.kenziegaming.dao.models.GroupMembershipCacheKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

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
        // GIVEN

        // WHEN

        // THEN
        assertTrue(false);
    }

    // Rename this method
    @Test
    public void test2() {
        // Implement your test here
        // GIVEN

        // WHEN

        // THEN
        assertTrue(false);
    }
}
