package by.barbarossa.dao.impl;

import by.barbarossa.dao.ParksAndRecDAO;
import by.barbarossa.dao.command.Command;
import by.barbarossa.dao.command.PlantTable;
import by.barbarossa.dao.command.TablesDirector;
import by.barbarossa.entity.Address;
import by.barbarossa.entity.Firm;
import by.barbarossa.entity.ParkZone;
import by.barbarossa.entity.Plant;
import by.barbarossa.entity.Watering;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlantDAOImpl implements ParksAndRecDAO{

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
    public List select() {
        String query = "SELECT parks.plant.idplant, parks.zone.zoneName, parks.plant.species," +
                "parks.plant.age, parks.plant.plantingDate, parks.watering.periodicity, parks.watering.waterNorm FROM" +
                "(parks.plant INNER JOIN parks.watering ON (parks.plant.idWatering = parks.watering.idwatering))" +
                "INNER join parks.zone ON (parks.plant.idZone = parks.zone.idzone) order by parks.plant.idplant";

        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();

            rs = stmt.executeQuery(query);

            List<Plant> plants = new ArrayList<>();
            while (rs.next()){
                Plant plant = new Plant();
                ParkZone parkZone = new ParkZone();
                Watering watering = new Watering();

                plant.setId(rs.getInt(1));
                parkZone.setZoneName(rs.getString(2));
                plant.setSpecies(rs.getString(3));
                plant.setAge(rs.getInt(4));
                plant.setPlantDate(rs.getDate(5));
                watering.setPeriodicity(rs.getString(6));
                watering.setWaterNorm(rs.getInt(7));

                plant.setWatering(watering);
                plant.setZone(parkZone);
                plants.add(plant);
            }
            return plants;

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
    public void delete(int id) {

    }

    @Override
    public void updateTable(String columnName, Object value, Object row) {
        Command command = tablesDirector.getCommand("plant");

        String query = command.formUpdateStatement(columnName);

        try {
            con = DriverManager.getConnection(url, user, password);

            PlantTable plantTable = (PlantTable)command;
            String idDefiningQuery = plantTable.getIdQuery(columnName,row);
            Object id;
            if(idDefiningQuery!=null){
                Statement s = con.createStatement();
                ResultSet rs = s.executeQuery(idDefiningQuery);
                rs.next();
                id = rs.getInt(1);
            } else{
                id = row;
            }
            PreparedStatement statement = con.prepareStatement(query);

            statement.setObject(1,value);
            statement.setObject(2,id);

            statement.executeUpdate();

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }
}
