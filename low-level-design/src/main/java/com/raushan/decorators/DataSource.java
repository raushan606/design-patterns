package com.raushan.decorators;

public interface DataSource {
    void writeData(String data);
    String readData();
}
