package by.barbarossa.service.impl;

import by.barbarossa.dao.DAOFactory;
import by.barbarossa.dao.ParksAndRecDAO;
import by.barbarossa.entity.Plant;
import by.barbarossa.service.ParksAndRecService;

import java.util.List;

public class PlantService implements ParksAndRecService {
    @Override
    public void insert(Object object) {

    }

    @Override
    public List<Plant> select() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ParksAndRecDAO plantDAO = daoFactory.getDAO("plant");
        return plantDAO.select();
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void updateTable(String columnName, Object value, Object row) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ParksAndRecDAO plantDAO = daoFactory.getDAO("plant");
        plantDAO.updateTable(columnName,value,row);
    }
}
