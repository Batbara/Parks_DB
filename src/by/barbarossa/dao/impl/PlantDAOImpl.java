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
        Plant plant = (Plant) o;
        Watering watering = plant.getWatering();
        ParkZone parkZone = plant.getZone();

        try {
            con = DriverManager.getConnection(url, user, password);

            String query = "INSERT INTO parks.watering (periodicity, waterNorm) VALUES (?,?) " +
                    " ON DUPLICATE KEY UPDATE idWatering=LAST_INSERT_ID()+1";
            PreparedStatement statement = con.prepareStatement(query);
            // statement.setInt(1,address.getId());
            statement.setString(1,watering.getPeriodicity());
            statement.setInt(2,watering.getWaterNorm());
            statement.executeUpdate();

            ResultSet rs = statement.executeQuery("SELECT LAST_INSERT_ID()");
            int wateringID = -1;
            if (rs.next()) {
                wateringID = rs.getInt(1);
            }

           int zoneID = getZoneID(parkZone);
            String firmQuery = "INSERT INTO parks.plant (idZone, species,age,idWatering,plantingDate) VALUES (?,?,?,?,?)";
            statement = con.prepareStatement(firmQuery);
            //statement.setInt(1,firm.getId());
            statement.setInt(1,zoneID);
            statement.setString(2,plant.getSpecies());
            statement.setInt(3,plant.getAge());
            statement.setInt(4,wateringID);
            statement.setDate(5,new java.sql.Date(plant.getPlantDate().getTime()));
            statement.executeUpdate();

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try { con.close(); } catch(SQLException se) { }
            try { stmt.close(); } catch(SQLException se) {  }
            try { rs.close(); } catch(SQLException se) {  }
        }
    }

    private int getZoneID(ParkZone parkZone) {
        try {
            con = DriverManager.getConnection(url, user, password);
            String selectZoneIDStatement = "SELECT parks.zone.idZone FROM parks.zone WHERE zoneName = \""
                    + parkZone.getZoneName() + "\"";
            ResultSet rs = con.createStatement().executeQuery(selectZoneIDStatement);
            int zoneID = -1;
            while (rs.next()) {
                zoneID = rs.getInt(1);
            }
            return zoneID;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public List<String> getZonesInfo(){
        String query = "SELECT DISTINCT parks.zone.zoneName FROM" +
                "(parks.plant INNER JOIN parks.zone ON (parks.plant.idZone = parks.zone.idzone)) ";
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();

            rs = stmt.executeQuery(query);

            List<String> zoneNames = new ArrayList<>();
            while (rs.next()){

                String zoneName = rs.getString(1);
                zoneNames.add(zoneName);
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
        Command command = tablesDirector.getCommand("plant");

        String query = command.formDeleteStatement(Integer.toString(id));
        try {
            con = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = con.prepareStatement(query);

            statement.executeUpdate();

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
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
