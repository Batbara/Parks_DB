package by.barbarossa.dao.impl;

import by.barbarossa.dao.ParksAndRecDAO;
import by.barbarossa.dao.command.TablesDirector;
import by.barbarossa.entity.Address;
import by.barbarossa.entity.Plant;
import by.barbarossa.entity.PlantWorker;
import by.barbarossa.entity.Schedule;
import by.barbarossa.entity.Watering;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScheduleDAOImpl implements ParksAndRecDAO {
    private static final String url = "jdbc:mysql://localhost:3306/parks?autoReconnect=true&useSSL=false";
    private static final String user = "root";
    private static final String password = "leaf";
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    private TablesDirector tablesDirector = new TablesDirector();
    @Override
    public void insert(Object o) {

    }
    public List<String> getDateInfo(){
        String query = "SELECT DISTINCT parks.schedule.date FROM parks.schedule";
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();

            rs = stmt.executeQuery(query);

            List<String> zoneNames = new ArrayList<>();
            while (rs.next()){

                Date date = rs.getDate(1);
                zoneNames.add(date.toString());
            }
            return zoneNames;

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
        return null;
    }
    public List<Schedule> showWorkers(Object arg){
        Date date = (Date) arg;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String stringDate = dateFormat.format(date);
        String query = "SELECT DISTINCT  parks.plantworker.plantworkername, " +
                "parks.plantworker.phoneNumber, parks.schedule.date" +
                " FROM (parks.schedule INNER JOIN parks.plantworker" +
                " ON (parks.plantWorker.idplantworker = parks.schedule.idplantworker) )" +
                "WHERE parks.schedule.date = '"+stringDate+"' ";

        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();

            rs = stmt.executeQuery(query);

            List<Schedule> schedules = new ArrayList<>();
            while (rs.next()){
                Schedule schedule = new Schedule();
                PlantWorker worker = new PlantWorker();

                worker.setName(rs.getString(1));
                worker.setPhoneNum(rs.getString(2));
                schedule.setDate(rs.getDate(3));
                schedule.setPlantWorker(worker);
                schedules.add(schedule);
            }
            return schedules;

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
        return null;
    }
    public List<Schedule> showPlants(Object arg){
        Date date = (Date) arg;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String stringDate = dateFormat.format(date);
        String query = "SELECT  parks.plant.species," +
                " parks.watering.periodicity, parks.watering.waterNorm,  parks.schedule.date FROM" +
                "(parks.plant INNER JOIN parks.watering ON (parks.plant.idWatering = parks.watering.idwatering))" +
                "INNER join parks.schedule ON (parks.plant.idPlant = parks.schedule.idplant) "
                +"WHERE parks.schedule.date = '"+stringDate+"' ";

        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();

            rs = stmt.executeQuery(query);

            List<Schedule> schedules = new ArrayList<>();
            while (rs.next()){
                Schedule schedule = new Schedule();
                Watering watering = new Watering();
                Plant plant = new Plant();

                plant.setSpecies(rs.getString(1));

                watering.setPeriodicity(rs.getString(2));
                watering.setWaterNorm(rs.getInt(3));

                schedule.setDate(rs.getDate(4));

                plant.setWatering(watering);
                schedule.setPlant(plant);
                schedules.add(schedule);
            }
            return schedules;

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
        return null;
    }
    @Override
    public List<Schedule> select() {
        String query = "SELECT parks.schedule.idschedule, parks.plantworker.plantworkername, " +
                "parks.schedule.date, parks.plant.species" +
                " FROM (parks.schedule INNER JOIN parks.plantworker" +
                " ON (parks.plantWorker.idplantworker = parks.schedule.idplantworker) " +
                "INNER JOIN parks.plant ON (parks.schedule.idplant = parks.plant.idplant))";

        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();

            rs = stmt.executeQuery(query);

            List<Schedule> schedules = new ArrayList<>();
            while (rs.next()){
                Schedule schedule = new Schedule();

                schedule.setId(rs.getInt(1));

                PlantWorker plantWorker = new PlantWorker();
                plantWorker.setName(rs.getString(2));

                schedule.setDate(rs.getDate(3));
                Plant plant =  new Plant();
                plant.setSpecies(rs.getString(4));

                schedule.setPlant(plant);
                schedule.setPlantWorker(plantWorker);
                schedules.add(schedule);
            }
            return schedules;

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void updateTable(String columnName, Object value, Object row) {

    }
}
