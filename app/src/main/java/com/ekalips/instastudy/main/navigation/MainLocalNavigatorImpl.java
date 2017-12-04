package com.ekalips.instastudy.main.navigation;

import android.support.v4.app.FragmentManager;

import com.ekalips.instastudy.R;
import com.ekalips.instastudy.main.mvvm.view.MainActivity;
import com.ekalips.instastudy.main.mvvm.view.files.FilesFragment;
import com.ekalips.instastudy.main.mvvm.view.group_chat.ChatFragment;
import com.ekalips.instastudy.main.mvvm.view.notifications.NotificationsScreenFragment;
import com.ekalips.instastudy.main.mvvm.view.schedule.ScheduleFragment;
import com.ekalips.instastudy.profile.mvvm.view.ProfileDialog;

import javax.inject.Inject;

/**
 * Created by Ekalips on 11/6/17.
 */

public class MainLocalNavigatorImpl implements MainLocalNavigator {


    private final FragmentManager fragmentManager;

    @Inject
    public MainLocalNavigatorImpl(MainActivity mainActivity) {
        this.fragmentManager = mainActivity.getSupportFragmentManager();
    }


    @Override
    public void navigateToGroupChat(String groupId) {
        popBackStack();

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ChatFragment.newInstance(groupId))
                .commit();
    }

    @Override
    public void navigateToSchedule() {
        popBackStack();

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ScheduleFragment.newInstance())
                .commit();
    }

    @Override
    public void navigateToFiles(String groupId, String directory) {
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FilesFragment.newInstance(groupId, directory))
                .addToBackStack(FilesFragment.class.getSimpleName() + directory)
                .commit();
    }

    @Override
    public void navigateToNotifications(String groupId) {
        popBackStack();

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, NotificationsScreenFragment.newInstance(groupId), NotificationsScreenFragment.class.getSimpleName())
                .commit();
    }

    @Override
    public void showUserPage(String userId) {
        ProfileDialog.newInstance(userId).show(fragmentManager, ProfileDialog.class.getSimpleName());
    }

    private void popBackStack() {
        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
            fragmentManager.popBackStack();
        }
    }
}
