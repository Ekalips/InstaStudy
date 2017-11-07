package com.ekalips.instastudy.data.lessons.sources.remote.models;

import com.ekalips.instastudy.data.lessons.models.Lesson;
import com.ekalips.instastudy.data.lessons.models.LessonDate;
import com.ekalips.instastudy.data.lessons.models.Subject;
import com.ekalips.instastudy.data.user.User;
import com.ekalips.instastudy.data.user.source.network.model.RemoteUserData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ekalips on 11/7/17.
 */

public class RemoteLesson implements Lesson {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("teacher")
    @Expose
    private RemoteUserData teacher;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("subject")
    @Expose
    private RemoteSubject subject;
    @SerializedName("date")
    @Expose
    private RemoteDate date;


    @Override
    public String getId() {
        return id;
    }

    @Override
    public User getTeacher() {
        return teacher;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public Subject getSubject() {
        return subject;
    }

    @Override
    public LessonDate getDate() {
        return date;
    }
}
