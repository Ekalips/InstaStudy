package com.ekalips.instastudy.data.stuff;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * Created by Ekalips on 11/5/17.
 */

@Entity
public class DummyEntity {

    @Id
    private long boxId;

    public long getBoxId() {
        return boxId;
    }

    public void setBoxId(long boxId) {
        this.boxId = boxId;
    }
}
