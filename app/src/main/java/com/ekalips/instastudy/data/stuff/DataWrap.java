package com.ekalips.instastudy.data.stuff;

/**
 * Created by Ekalips on 10/2/17.
 */

public class DataWrap<Data> {

    private Data data;

    private int responseCode;

    public DataWrap(Data data, int responseCode) {
        this.data = data;
        this.responseCode = responseCode;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
}
