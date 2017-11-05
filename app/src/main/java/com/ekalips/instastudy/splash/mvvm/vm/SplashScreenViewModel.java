package com.ekalips.instastudy.splash.mvvm.vm;

import com.ekalips.instastudy.data.user.UserDataProvider;
import com.ekalips.instastudy.di.source_qualifier.DataProvider;
import com.ekalips.instastudy.navigation.NavigateToEnum;
import com.ekalips.instastudy.splash.SplashScreeContract;
import com.ekalips.instastudy.stuff.StringUtils;
import com.wonderslab.base.rx.RxRequests;

import javax.inject.Inject;

/**
 * Created by Ekalips on 11/4/17.
 */

public class SplashScreenViewModel extends SplashScreeContract.ViewModel {

    private final UserDataProvider userDataProvider;
    private final RxRequests rxRequests;

    @Inject
    public SplashScreenViewModel(RxRequests rxRequests, @DataProvider UserDataProvider userDataProvider) {
        this.userDataProvider = userDataProvider;
        this.rxRequests = rxRequests;
        checkUserAndNavigate();
    }

    private void checkUserAndNavigate() {
        addDisposable(rxRequests.subscribe(userDataProvider.getUser(false), user -> {
            if (user.getData() == null || StringUtils.isEmpty(user.getData().getUserName()) || StringUtils.isEmpty(user.getData().getToken())
                    || user.getData().getGroups() == null || user.getData().getGroups().size() <= 0) {
                navigateTo(NavigateToEnum.LOGIN, null);
            } else {
                navigateTo(NavigateToEnum.MAIN, null);
            }
        }));
    }

}
