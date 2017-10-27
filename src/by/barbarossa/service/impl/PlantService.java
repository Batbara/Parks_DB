package by.barbarossa.service.impl;

import by.barbarossa.dao.DAOFactory;
import by.barbarossa.dao.ParksAndRecDAO;
import by.barbarossa.dao.impl.PlantDAOImpl;
import by.barbarossa.entity.Plant;
import by.barbarossa.service.ParksAndRecService;

import java.util.List;

public class PlantService implements ParksAndRecService {
    @Override
    public void insert(Object object) {
        if(object instanceof Plant) {
            Plant plant = (Plant)object;
            DAOFactory daoFactory = DAOFactory.getInstance();
            ParksAndRecDAO plantDAO = daoFactory.getDAO("plant");
            plantDAO.insert(plant);
        }

    }
    public List<String> getZonesInfo(){
        DAOFactory daoFactory = DAOFactory.getInstance();
        PlantDAOImpl plantDAO = (PlantDAOImpl) daoFactory.getDAO("plant");
       return plantDAO.getZonesInfo();
    }
    @Override
    public List<Plant> select() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ParksAndRecDAO plantDAO = daoFactory.getDAO("plant");
        return plantDAO.select();
    }

    @Override
    public void delete(int id) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ParksAndRecDAO plantDAO = daoFactory.getDAO("plant");
        plantDAO.delete(id);
    }

    @Override
    public void updateTable(String columnName, Object value, Object row) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ParksAndRecDAO plantDAO = daoFactory.getDAO("plant");
        plantDAO.updateTable(columnName,value,row);
    }
}
