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
    public void insert() {

    }

    @Override
    public List<Firm> select() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ParksAndRecDAO firmDAO = daoFactory.getDAO("firm");
        return firmDAO.select();
    }

    @Override
    public void delete() {

    }
}
