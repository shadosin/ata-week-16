package com.kenzie.inmemorycaching.kenziegaming;

import com.kenzie.inmemorycaching.kenziegaming.activity.CheckUserInGroupActivity;
import com.kenzie.inmemorycaching.kenziegaming.dao.GroupMembershipCachingDao;
import com.kenzie.inmemorycaching.kenziegaming.dao.GroupMembershipDao;
import com.kenzie.inmemorycaching.kenziegaming.dao.models.GroupMembership;
import com.kenzie.test.infrastructure.reflect.ConstructorQuery;
import com.kenzie.test.infrastructure.reflect.MethodInvoker;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class Phase2Test {

    private CheckUserInGroupActivity checkUserInGroupActivity;

    private DynamoDBMapper mapper = mock(DynamoDBMapper.class);

    @BeforeEach
    private void setup() throws Exception {
        Class clazz = CheckUserInGroupActivity.class;

        GroupMembershipDao groupMembershipDao = new GroupMembershipDao(mapper);

        try {
            Constructor constructor = ConstructorQuery.inClass(clazz).withExactArgTypes(Arrays.asList(GroupMembershipCachingDao.class)).findConstructor();
            checkUserInGroupActivity = (CheckUserInGroupActivity) MethodInvoker.invokeConstructor(constructor, new GroupMembershipCachingDao(groupMembershipDao));
        } catch (Exception e1) {
            try {
                Constructor constructor = ConstructorQuery.inClass(clazz).withExactArgTypes(Arrays.asList(GroupMembershipDao.class)).findConstructor();
                checkUserInGroupActivity = (CheckUserInGroupActivity) MethodInvoker.invokeConstructor(constructor, groupMembershipDao);
            } catch (Exception e2) {
                throw new NoSuchMethodException("Could not locate the CheckUserInGroupActivityConstructor");
            }
        }
    }

    @Test
    public void checkUserInGroupActivity_userNotInGroup_returnsFalse() {
        // GIVEN
        String userId = UUID.randomUUID().toString();
        String groupId = UUID.randomUUID().toString();

        when(mapper.load(any(GroupMembership.class))).thenReturn(null);
        // WHEN
        boolean result = checkUserInGroupActivity.handleRequest(userId, groupId);

        // THEN
        assertFalse(result, "Expected the check for a user not in the group to return false.");
        verify(mapper, times(1)
                        .description("Dynamodb should only be called once, the second call should hit the cache."))
                .load(any(GroupMembership.class));
    }

    @Test
    public void checkUserInGroupActivity_userAddedToGroup_returnsCachedFalse() {
        // GIVEN
        String userId = UUID.randomUUID().toString();
        String groupId = UUID.randomUUID().toString();
        when(mapper.load(any(GroupMembership.class))).thenReturn(null);
        // Add to cache
        boolean result1 = checkUserInGroupActivity.handleRequest(userId, groupId);
        assertFalse(result1, "Expected the check for a user not in the group to return false.");
        // WHEN
        boolean result = checkUserInGroupActivity.handleRequest(userId, groupId);

        // THEN
        assertFalse(result, "Expected the check for a user in a group to be cached. Cache should return false" +
                "within the TTL for a user not in a group even if a call to AddUserToGroup occurs.");
        verify(mapper, times(1)
                        .description("Dynamodb should only be called once, the second call should hit the cache."))
                .load(any(GroupMembership.class));
    }

    @Test
    public void checkUserInGroupActivity_userInGroup_returnsTrue() {
        // GIVEN
        String userId = UUID.randomUUID().toString();
        String groupId = UUID.randomUUID().toString();

        when(mapper.load(any(GroupMembership.class))).thenAnswer(i -> i.getArguments()[0]);

        // WHEN
        boolean result = checkUserInGroupActivity.handleRequest(userId, groupId);

        // THEN
        assertTrue(result, "Expected the check for a user in the group to return true.");
        verify(mapper, times(1)
                        .description("Dynamodb should only be called once, the second call should hit the cache."))
                .load(any(GroupMembership.class));
    }

    @Test
    public void checkUserInGroupActivity_userRemovedFromGroup_returnsCachedTrue() {
        // GIVEN
        String userId = UUID.randomUUID().toString();
        String groupId = UUID.randomUUID().toString();

        when(mapper.load(any(GroupMembership.class))).thenAnswer(i -> i.getArguments()[0]);

        // Add to cache
        boolean result1 = checkUserInGroupActivity.handleRequest(userId, groupId);
        assertTrue(result1, "Expected the check for a user not in the group to return false.");

        // WHEN
        boolean result2 = checkUserInGroupActivity.handleRequest(userId, groupId);

        // THEN
        assertTrue(result2, "Expected the check for a user in a group to be cached. Cache should return true" +
                "within the TTL for a user in a group even if a call to RemoveUserFromGroup occurs.");
        verify(mapper, times(1)
                        .description("Dynamodb should only be called once, the second call should hit the cache."))
                .load(any(GroupMembership.class));
    }
}
