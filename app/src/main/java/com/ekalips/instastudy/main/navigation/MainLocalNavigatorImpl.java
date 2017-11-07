package com.ekalips.instastudy.main.navigation;

import android.support.v4.app.FragmentManager;

import com.ekalips.instastudy.R;
import com.ekalips.instastudy.main.mvvm.view.MainActivity;
import com.ekalips.instastudy.main.mvvm.view.group_chat.ChatFragment;
import com.ekalips.instastudy.main.mvvm.view.schedule.ScheduleFragment;

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
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ChatFragment.newInstance(groupId))
                .commit();
    }

    @Override
    public void navigateToSchedule() {
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ScheduleFragment.newInstance())
                .commit();
    }
}
