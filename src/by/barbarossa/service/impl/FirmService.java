package by.barbarossa.service.impl;

import by.barbarossa.dao.DAOFactory;
import by.barbarossa.dao.ParksAndRecDAO;
import by.barbarossa.entity.Address;
import by.barbarossa.entity.Firm;
import by.barbarossa.service.ParksAndRecService;

import java.sql.ResultSet;
import java.util.List;

public class FirmService implements ParksAndRecService{
    @Override
    public void insert(Object o) {
        if(o instanceof Firm){
            Firm firm = (Firm)o;
            DAOFactory daoFactory = DAOFactory.getInstance();
            ParksAndRecDAO firmDAO = daoFactory.getDAO("firm");
            firmDAO.insert(firm);
        }
    }

    @Override
    public List<Firm> select() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ParksAndRecDAO firmDAO = daoFactory.getDAO("firm");
        return firmDAO.select();
    }

    @Override
    public void delete(int id) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ParksAndRecDAO firmDAO = daoFactory.getDAO("firm");
        firmDAO.delete(id);
    }

    @Override
    public void updateTable(String columnName, Object value, Object row) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ParksAndRecDAO firmDAO = daoFactory.getDAO("firm");
        firmDAO.updateTable(columnName,value,row);
    }
}
