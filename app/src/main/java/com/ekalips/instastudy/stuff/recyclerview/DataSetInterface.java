package com.ekalips.instastudy.stuff.recyclerview;

import java.util.List;

/**
 * Created by Ekalips on 9/27/17.
 */
public interface DataSetInterface<DataType> {
    void setData(List<DataType> data);
}