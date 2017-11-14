package com.ekalips.instastudy.data.lessons.sources.local.models;

import com.ekalips.instastudy.data.lessons.models.Subject;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * Created by Ekalips on 11/7/17.
 */

@Entity
public class LocalSubject implements Subject {

    @Id
    private long boxId;

    private String id;
    private String title;

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

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
