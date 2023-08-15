package com.kenzie.inmemorycaching.kenziegaming.dao.models;

import java.util.Objects;

public final class GroupMembershipCacheKey {

    // Implement your cache key class here
    // Remember to include the equals() and hashCode() methods!
    private final String userId;
    private final String groupId;
    //private final GroupType type;

    public GroupMembershipCacheKey(String userId, String groupId) {
        this.userId = userId;
        this.groupId = groupId;
    }

    public String getUserId() {
        return userId;
    }

    public String getGroupId() {
        return groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupMembershipCacheKey that)) return false;
        return Objects.equals(getUserId(), that.getUserId()) && Objects.equals(getGroupId(), that.getGroupId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getGroupId());
    }
}