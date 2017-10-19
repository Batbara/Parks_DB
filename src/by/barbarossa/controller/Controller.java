package by.barbarossa.controller;

import java.util.Map;
import java.util.Observer;

public interface Controller extends Observer{
     void repaintTable();
    void updateData(Object data);
    void deleteRow(int id);
    void addRecord(Map<String, String> record);
}
