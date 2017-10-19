package by.barbarossa.dao;

import java.util.List;

public interface ParksAndRecDAO {
    void insert();
    List select();
    void delete();
    void updateTable(String columnName, Object value, int row);
}
