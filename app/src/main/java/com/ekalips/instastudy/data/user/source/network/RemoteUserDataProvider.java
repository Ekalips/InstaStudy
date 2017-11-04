package com.ekalips.instastudy.data.user.source.network;


import com.ekalips.instastudy.data.stuff.DataWrap;
import com.ekalips.instastudy.data.user.User;

import io.reactivex.Observable;

/**
 * Created by Ekalips on 10/2/17.
 */

public interface RemoteUserDataProvider {

    Observable<DataWrap<? extends User>> getUser(String token);

}
