package com.ekalips.instastudy.main.navigation;

/**
 * Created by Ekalips on 11/6/17.
 */

public interface MainLocalNavigator {

    void navigateToGroupChat(String groupId);

    void navigateToSchedule();

    void navigateToFiles(String groupId, String directory);

    void navigateToNotifications(String groupId);

    void showUserPage(String userId);
}
