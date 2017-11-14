package com.ekalips.instastudy.stuff.lists;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Ekalips on 11/14/17.
 */

public class EqualableArrayList<E> extends ArrayList<E> {

    private final Equalator<E> equalator;

    public EqualableArrayList(Equalator<E> equalator) {
        this.equalator = equalator;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size(); i++)
                if (get(i) == null)
                    return i;
        } else {
            for (int i = 0; i < size(); i++)
                if (equalator.equals(get(i), o))
                    return i;
        }
        return -1;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object o :
                c) {
            int index = indexOf(o);
            if (index != -1) {
                remove(index);
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (int index = 0; index < size(); index++)
                if (get(index) == null) {
                    remove(index);
                    return true;
                }
        } else {
            for (int index = 0; index < size(); index++)
                if (equalator.equals(get(index), o)) {
                    remove(index);
                    return true;
                }
        }
        return false;
    }
}
