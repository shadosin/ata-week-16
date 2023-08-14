package com.kenzie.inmemorycaching.kenziegaming.helpers;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.kenzie.inmemorycaching.kenziegaming.activity.AddUserToGroupActivity;
import com.kenzie.inmemorycaching.kenziegaming.activity.CreateGroupActivity;
import com.kenzie.inmemorycaching.kenziegaming.activity.RemoveUserFromGroupActivity;
import com.kenzie.inmemorycaching.kenziegaming.dao.GroupDao;
import com.kenzie.inmemorycaching.kenziegaming.dao.GroupMembershipDao;
import com.kenzie.inmemorycaching.kenziegaming.dao.models.Group;
import com.kenzie.inmemorycaching.kenziegaming.dao.models.GroupType;
import org.junit.jupiter.api.BeforeEach;

import java.util.UUID;

public class TestDataProvider {

    private CreateGroupActivity createGroupActivity;
    private AddUserToGroupActivity addUserToGroupActivity;
    private RemoveUserFromGroupActivity removeUserFromGroupActivity;

    public TestDataProvider(DynamoDBMapper mapper) {
        GroupDao groupDao = new GroupDao(mapper);
        GroupMembershipDao groupMembershipDao = new GroupMembershipDao(mapper);
        createGroupActivity = new CreateGroupActivity(groupDao);
        addUserToGroupActivity = new AddUserToGroupActivity(groupMembershipDao, groupDao);
        removeUserFromGroupActivity = new RemoveUserFromGroupActivity(groupMembershipDao);
    }

    public Group createGroup(String groupName, GroupType groupType) {
        return createGroupActivity.handleRequest(groupName, groupType);
    }

    public void addMembership(String userId, String groupId) {
        addUserToGroupActivity.handleRequest(userId, groupId);
    }

    public void removeMembership(String userId, String groupId) {
        removeUserFromGroupActivity.handleRequest(userId, groupId);
    }

    public String generateGroupName() {
        return "ATA Group - " + UUID.randomUUID().toString();
    }
}
