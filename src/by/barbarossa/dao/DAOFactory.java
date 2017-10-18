package by.barbarossa.dao;

import by.barbarossa.dao.impl.FirmDAOImpl;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private static final ParksAndRecDAO firmDAO = new FirmDAOImpl();
    private DAOFactory() {}
    public static DAOFactory getInstance() {
        return instance;
    }
    public ParksAndRecDAO getDAO(String name){
        switch (name){
            case "firm":
                return firmDAO;

        }
        return null;
    }
}
