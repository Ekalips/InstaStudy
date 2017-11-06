package com.ekalips.instastudy.data.other_user;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * Created by Ekalips on 11/6/17.
 */

@Entity
public class LocalOtherUser implements OtherUser {

    @Id
    private long boxId;

    private String id;
    private String name;
    private String avatar;

    public long getBoxId() {
        return boxId;
    }

    public void setBoxId(long boxId) {
        this.boxId = boxId;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAvatar() {
        return avatar;
    }
}
