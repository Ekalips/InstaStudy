package com.ekalips.instastudy.data.lessons.sources.local.models;

import com.ekalips.instastudy.data.lessons.models.Lesson;
import com.ekalips.instastudy.data.lessons.models.LessonDate;
import com.ekalips.instastudy.data.lessons.models.Subject;
import com.ekalips.instastudy.data.user.User;
import com.ekalips.instastudy.data.user.source.local.model.BoxLocalUser;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;

/**
 * Created by Ekalips on 11/7/17.
 */

@Entity
public class LocalLesson implements Lesson {

    @Id(assignable = true)
    private long boxId;

    private String id;
    private ToOne<BoxLocalUser> boxLocalUserToOne;
    private String location;
    private ToOne<LocalSubject> subjectToOne;
    private ToOne<LocalLessonDate> localLessonDateToOne;

    public long getBoxId() {
        return boxId;
    }

    public void setBoxId(long boxId) {
        this.boxId = boxId;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public User getTeacher() {
        return boxLocalUserToOne.getTarget();
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public Subject getSubject() {
        return subjectToOne.getTarget();
    }

    @Override
    public LessonDate getDate() {
        return localLessonDateToOne.getTarget();
    }

    public ToOne<BoxLocalUser> getBoxLocalUserToOne() {
        return boxLocalUserToOne;
    }

    public void setBoxLocalUserToOne(ToOne<BoxLocalUser> boxLocalUserToOne) {
        this.boxLocalUserToOne = boxLocalUserToOne;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ToOne<LocalSubject> getSubjectToOne() {
        return subjectToOne;
    }

    public void setSubjectToOne(ToOne<LocalSubject> subjectToOne) {
        this.subjectToOne = subjectToOne;
    }

    public ToOne<LocalLessonDate> getLocalLessonDateToOne() {
        return localLessonDateToOne;
    }

    public void setLocalLessonDateToOne(ToOne<LocalLessonDate> localLessonDateToOne) {
        this.localLessonDateToOne = localLessonDateToOne;
    }
}
