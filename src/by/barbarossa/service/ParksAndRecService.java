package by.barbarossa.service;

import java.sql.ResultSet;
import java.util.List;

public interface ParksAndRecService {
    void insert();
    List select();
    void delete();
    void updateTable(String columnName, Object value, int row);
}
