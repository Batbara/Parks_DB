package by.barbarossa.service.impl;

import by.barbarossa.dao.DAOFactory;
import by.barbarossa.dao.ParksAndRecDAO;
import by.barbarossa.entity.PlantWorker;
import by.barbarossa.service.ParksAndRecService;

import java.util.List;

public class PlantWorkerService implements ParksAndRecService {
    @Override
    public void insert(Object object) {

    }

    @Override
    public List<PlantWorker> select() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ParksAndRecDAO pWorkerDAO = daoFactory.getDAO("plantworker");
        return pWorkerDAO.select();
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void updateTable(String columnName, Object value, Object row) {

    }
}
