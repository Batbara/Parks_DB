package by.barbarossa.dao;

import java.util.List;

public interface ParksAndRecDAO {
    void insert(Object o);
    List select();
    void delete(int id);
    void updateTable(String columnName, Object value, Object row);
}
