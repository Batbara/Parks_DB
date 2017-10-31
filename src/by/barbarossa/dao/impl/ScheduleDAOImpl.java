package by.barbarossa.dao.impl;

import by.barbarossa.dao.ParksAndRecDAO;
import by.barbarossa.dao.command.TablesDirector;
import by.barbarossa.entity.Address;
import by.barbarossa.entity.Plant;
import by.barbarossa.entity.PlantWorker;
import by.barbarossa.entity.Schedule;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
