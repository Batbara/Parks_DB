package by.barbarossa.dao;

import by.barbarossa.dao.impl.FirmDAOImpl;
import by.barbarossa.dao.impl.ParkDecoratorDAOImpl;
import by.barbarossa.dao.impl.ParksDAOImpl;
import by.barbarossa.dao.impl.PlantDAOImpl;
import by.barbarossa.dao.impl.PlantWorkerDAOImpl;
import by.barbarossa.dao.impl.ScheduleDAOImpl;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private static final ParksAndRecDAO firmDAO = new FirmDAOImpl();
    private static final ParksAndRecDAO plantDAO = new PlantDAOImpl();
    private static final ParksAndRecDAO parksDAO = new ParksDAOImpl();
    private static final ParksAndRecDAO parkDecoratorDAO = new ParkDecoratorDAOImpl();
    private static final ParksAndRecDAO pWorkerDAO = new PlantWorkerDAOImpl();
    private static final ParksAndRecDAO scheduleDAO = new ScheduleDAOImpl();
    private DAOFactory() {}
    public static DAOFactory getInstance() {
        return instance;
    }
    public ParksAndRecDAO getDAO(String name){
        switch (name){
            case "firm":
                return firmDAO;
            case "plant":
                return plantDAO;
            case "parks":
                return parksDAO;
            case "decorator":
                return parkDecoratorDAO;
            case "plantworker":
                return pWorkerDAO;
            case "schedule":
                return scheduleDAO;

        }
        return null;
    }
}
