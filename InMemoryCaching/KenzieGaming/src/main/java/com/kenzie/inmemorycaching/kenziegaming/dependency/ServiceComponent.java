package com.kenzie.inmemorycaching.kenziegaming.dependency;

import com.kenzie.inmemorycaching.kenziegaming.activity.AddUserToGroupActivity;
import com.kenzie.inmemorycaching.kenziegaming.activity.CheckUserInGroupActivity;
import com.kenzie.inmemorycaching.kenziegaming.activity.CreateGroupActivity;
import com.kenzie.inmemorycaching.kenziegaming.activity.GetGroupActivity;
import com.kenzie.inmemorycaching.kenziegaming.activity.GetGroupsForUserActivity;
import com.kenzie.inmemorycaching.kenziegaming.activity.GetUsersInGroupActivity;
import com.kenzie.inmemorycaching.kenziegaming.activity.RemoveUserFromGroupActivity;

import dagger.Component;

import javax.inject.Singleton;

/**
 * Declares the dependency roots that Dagger will provide.
 */
@Singleton
@Component(modules = DaoModule.class)
public interface ServiceComponent {
    AddUserToGroupActivity provideAddUserToGroupActivity();

    CheckUserInGroupActivity provideCheckUserInGroupActivity();

    CreateGroupActivity provideCreateGroupActivity();

    GetGroupActivity provideGetGroupActivity();

    GetGroupsForUserActivity provideGetGroupsForUserActivity();

    GetUsersInGroupActivity provideGetUsersInGroupActivity();

    RemoveUserFromGroupActivity provideRemoveUserFromGroupActivity();
}
