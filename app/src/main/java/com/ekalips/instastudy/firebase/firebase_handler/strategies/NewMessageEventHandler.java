package com.ekalips.instastudy.firebase.firebase_handler.strategies;

import com.ekalips.instastudy.data.messages.sources.remote.RemoteMessage;
import com.ekalips.instastudy.data.user.UserDataProvider;
import com.ekalips.instastudy.data.user.source.network.model.RemoteUserData;
import com.ekalips.instastudy.di.source_qualifier.DataProvider;
import com.ekalips.instastudy.firebase.firebase_handler.FirebaseEventType;
import com.ekalips.instastudy.firebase.firebase_handler.events.NewMessageEvent;
import com.ekalips.instastudy.stuff.Const;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Created by Ekalips on 11/14/17.
 */

@Singleton
public class NewMessageEventHandler implements FirebaseEventStrategy {

    private final Gson gson;
    private final EventBus bus;
    private final UserDataProvider userDataProvider;

    private static final String TAG = NewMessageEventHandler.class.getSimpleName();

    @Inject
    public NewMessageEventHandler(Gson gson, @Named(Const.NAME_FIREBASE_EVENT_BUS) EventBus bus, @DataProvider UserDataProvider userDataProvider) {
        this.gson = gson;
        this.bus = bus;
        this.userDataProvider = userDataProvider;
    }

    @Override
    public void handleEvent(Map<String, String> data) {
        RemoteMessage message = gson.fromJson(gson.toJson(data), RemoteMessage.class);
        RemoteUserData user = gson.fromJson(gson.toJson(data), RemoteUserData.class);
        message.setSender(user);
        if (bus.hasSubscriberForEvent(NewMessageEvent.class)) {
            bus.post(new NewMessageEvent(message));
        }
    }

    @Override
    public int getEventType() {
        return FirebaseEventType.NEW_MESSAGE.getType();
    }
}
