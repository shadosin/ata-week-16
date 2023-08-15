
package com.kenzie.inmemorycaching.kenziegaming.activity;

import com.kenzie.inmemorycaching.kenziegaming.dao.GroupMembershipCachingDao;
import com.kenzie.inmemorycaching.kenziegaming.dao.GroupMembershipDao;
import com.kenzie.inmemorycaching.kenziegaming.dao.models.GroupMembershipCacheKey;

import javax.inject.Inject;

/**
 * Handles requests to check if a user is in a group.
 */
public class CheckUserInGroupActivity {

    private final GroupMembershipCachingDao groupMembershipCachDao;

    /**
     * Constructs an Activity with the given DAOs.
     * @param groupMembershipDao The GroupMembershipDao to use for checking the user's membership
     */
    @Inject
    public CheckUserInGroupActivity(final GroupMembershipCachingDao groupMembershipDao) {
        this.groupMembershipCachDao = groupMembershipDao;
    }

    /**
     * Returns true if the userId is a member in the group with the provided groupId.
     * @param userId the userId to check for membership
     * @param groupId - the id of the group to check that the userId is a member of
     * @return true if the userId has a membership in the group, false otherwise
     */
    public boolean handleRequest(final String userId, final String groupId) {
        GroupMembershipCacheKey key = new GroupMembershipCacheKey(userId, groupId);
        return groupMembershipCachDao.isUserInGroup(key);
    }
}
