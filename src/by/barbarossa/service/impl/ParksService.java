package by.barbarossa.service.impl;

import by.barbarossa.dao.DAOFactory;
import by.barbarossa.dao.ParksAndRecDAO;
import by.barbarossa.dao.impl.ParksDAOImpl;
import by.barbarossa.dao.impl.PlantDAOImpl;
import by.barbarossa.service.ParksAndRecService;

import java.util.List;

public class ParksService implements ParksAndRecService {
    @Override
    public void insert(Object object) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ParksAndRecDAO parksDAO = daoFactory.getDAO("parks");
        parksDAO.insert(object);
    }
    public List<String> getParksInfo(){
        DAOFactory daoFactory = DAOFactory.getInstance();
        ParksDAOImpl plantDAO = (ParksDAOImpl) daoFactory.getDAO("parks");
        return plantDAO.getParksInfo();
    }

    @Override
    public List select() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ParksAndRecDAO parksDAO = daoFactory.getDAO("parks");
        return parksDAO.select();
    }

    @Override
    public void delete(int id) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ParksAndRecDAO parksDAO = daoFactory.getDAO("parks");
        parksDAO.delete(id);
    }

    @Override
    public void updateTable(String columnName, Object value, Object row) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ParksAndRecDAO parksDAO = daoFactory.getDAO("parks");
        parksDAO.updateTable(columnName,value,row);
    }
}
