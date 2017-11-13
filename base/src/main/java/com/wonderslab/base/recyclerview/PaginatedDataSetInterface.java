package com.wonderslab.base.recyclerview;

/**
 * Created by Ekalips on 11/9/17.
 */

public interface PaginatedDataSetInterface<DataType> extends DataSetInterface<DataType> {

    void setTotalCount(int count);

}
