package com.ekalips.instastudy.data.groups.source.local;

import com.ekalips.instastudy.data.groups.Group;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * Created by Ekalips on 11/5/17.
 */

@Entity
public class LocalGroup implements Group {

    @Id
    private long boxId;

    private String id;
    private String title;
    private String picture;
    private String chatId;

    public LocalGroup() {
    }

    public LocalGroup(Group group) {
        this.id = group.getId();
        this.title = group.getTitle();
        this.picture = group.getPicture();
        this.chatId = group.getChatId();
    }

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
    public String getTitle() {
        return title;
    }

    @Override
    public String getPicture() {
        return picture;
    }

    @Override
    public String getChatId() {
        return chatId;
    }
}
