package by.barbarossa.service.impl;

import by.barbarossa.dao.DAOFactory;
import by.barbarossa.dao.ParksAndRecDAO;
import by.barbarossa.dao.impl.ScheduleDAOImpl;
import by.barbarossa.entity.Schedule;
import by.barbarossa.service.ParksAndRecService;
import by.barbarossa.service.ServiceFactory;

import java.util.List;
import java.util.Map;

public class ScheduleService implements ParksAndRecService{
    private ServiceFactory factory = ServiceFactory.getInstance();
    private ScheduleService scheduleService = (ScheduleService) factory.getService("schedule");
    private Map<String, String> columnNameMap;
    @Override
    public void insert(Object object) {

    }

    public List<Schedule> showWorkers(Object arg){
        DAOFactory daoFactory = DAOFactory.getInstance();
        ScheduleDAOImpl scheduleDAO = (ScheduleDAOImpl)daoFactory.getDAO("schedule");
        return scheduleDAO.showWorkers(arg);
    }
    public List<Schedule> showPlants(Object arg){
        DAOFactory daoFactory = DAOFactory.getInstance();
        ScheduleDAOImpl scheduleDAO = (ScheduleDAOImpl)daoFactory.getDAO("schedule");
        return scheduleDAO.showPlants(arg);
    }
    public List<String> getDateInfo(){
        DAOFactory daoFactory = DAOFactory.getInstance();
        ScheduleDAOImpl scheduleDAO = (ScheduleDAOImpl)daoFactory.getDAO("schedule");
        return scheduleDAO.getDateInfo();
    }
    @Override
    public List select() {

        DAOFactory daoFactory = DAOFactory.getInstance();
        ParksAndRecDAO scheduleDAO = daoFactory.getDAO("schedule");
        return scheduleDAO.select();
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void updateTable(String columnName, Object value, Object row) {

    }
}
