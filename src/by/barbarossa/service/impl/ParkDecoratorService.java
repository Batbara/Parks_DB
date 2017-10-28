package by.barbarossa.service.impl;

import by.barbarossa.dao.DAOFactory;
import by.barbarossa.dao.ParksAndRecDAO;
import by.barbarossa.entity.ParkDecorator;
import by.barbarossa.service.ParksAndRecService;

import java.util.List;

public class ParkDecoratorService implements ParksAndRecService{
    @Override
    public void insert(Object object) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ParksAndRecDAO decoratorDAO = daoFactory.getDAO("decorator");
        decoratorDAO.insert(object);
    }

    @Override
    public List<ParkDecorator> select() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ParksAndRecDAO decoratorDAO = daoFactory.getDAO("decorator");
        return decoratorDAO.select();
    }

    @Override
    public void delete(int id) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ParksAndRecDAO decoratorDAO = daoFactory.getDAO("decorator");
        decoratorDAO.delete(id);
    }

    @Override
    public void updateTable(String columnName, Object value, Object row) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ParksAndRecDAO decoratorDAO = daoFactory.getDAO("decorator");
        decoratorDAO.updateTable(columnName,value,row);
    }
}
