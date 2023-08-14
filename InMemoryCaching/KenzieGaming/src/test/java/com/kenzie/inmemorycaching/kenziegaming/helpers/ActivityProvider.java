package com.kenzie.inmemorycaching.kenziegaming.helpers;

import com.kenzie.inmemorycaching.kenziegaming.activity.*;
import com.kenzie.inmemorycaching.kenziegaming.dependency.DaggerServiceComponent;
import com.kenzie.inmemorycaching.kenziegaming.dependency.ServiceComponent;

public final class ActivityProvider {
    private static final ServiceComponent DAGGER = DaggerServiceComponent.create();

    private ActivityProvider() {
    }

    public static CheckUserInGroupActivity provideCheckUserInGroupActivity() {
        return DAGGER.provideCheckUserInGroupActivity();
    }

    public static RemoveUserFromGroupActivity provideRemoveUserFromGroupActivity() {
        return DAGGER.provideRemoveUserFromGroupActivity();
    }

    public static CreateGroupActivity provideCreateGroupActivity() {
        return DAGGER.provideCreateGroupActivity();
    }

    public static AddUserToGroupActivity provideAddUserToGroupActivity() {
        return DAGGER.provideAddUserToGroupActivity();
    }

    public static GetGroupActivity provideGetGroupActivity() {
        return DAGGER.provideGetGroupActivity();
    }

    public static GetGroupsForUserActivity provideGetGroupsForUserActivity(){
        return DAGGER.provideGetGroupsForUserActivity();
    }

    public static GetUsersInGroupActivity provideGetUsersInGroupActivity(){
        return DAGGER.provideGetUsersInGroupActivity();
    }
}
