package by.barbarossa.service;

import java.sql.ResultSet;
import java.util.List;

public interface ParksAndRecService {
    void insert(Object object);
    List select();
    void delete(int id);
    void updateTable(String columnName, Object value, Object row);
}
