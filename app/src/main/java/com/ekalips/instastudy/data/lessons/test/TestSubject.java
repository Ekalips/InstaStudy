package com.ekalips.instastudy.data.lessons.test;

import com.ekalips.instastudy.data.lessons.models.Subject;

/**
 * Created by Ekalips on 11/8/17.
 */

public class TestSubject implements Subject {

    private String title;

    public TestSubject(String title) {
        this.title = title;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getTitle() {
        return title;
    }
}
