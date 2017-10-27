package by.barbarossa.dao.impl;

import by.barbarossa.dao.ParksAndRecDAO;
import by.barbarossa.dao.command.Command;
import by.barbarossa.dao.command.ParksTable;
import by.barbarossa.dao.command.TablesDirector;
import by.barbarossa.entity.Park;
import by.barbarossa.entity.ParkZone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ParksDAOImpl implements ParksAndRecDAO {
    private static final String url = "jdbc:mysql://localhost:3306/parks?autoReconnect=true&useSSL=false";
    private static final String user = "root";
    private static final String password = "leaf";
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    private TablesDirector tablesDirector = new TablesDirector();

    @Override
    public void insert(Object o) {
        ParkZone parkZone = (ParkZone) o;
        String parkName = parkZone.getParkName();
        String zoneName = parkZone.getZoneName();
        try {
            con = DriverManager.getConnection(url, user, password);

            String checkParkQuery = "SELECT parks.park.idPark FROM parks.park WHERE parkName = \""+parkName+"\"";
            ResultSet resultSet = con.createStatement().executeQuery(checkParkQuery);

            int parkID = -1;
            if(!resultSet.next()){
                String query = "INSERT INTO parks.park (parkName) VALUES (?)";
                PreparedStatement statement = con.prepareStatement(query);
                // statement.setInt(1,address.getId());
                statement.setString(1,parkName);
                statement.executeUpdate();

                ResultSet rs = statement.executeQuery("SELECT LAST_INSERT_ID()");
                if (rs.next()) {
                    parkID = rs.getInt(1);
                }
            } else {
                parkID = resultSet.getInt(1);
            }

            String insertQuery = "INSERT INTO parks.zone (idPark, zoneName) VALUES (?,?)";
            PreparedStatement statement = con.prepareStatement(insertQuery);
            //statement.setInt(1,firm.getId());

            statement.setInt(1,parkID);
            statement.setString(2,zoneName);
            statement.executeUpdate();

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }

    @Override
    public List<Park> select() {
        String query = "SELECT parks.zone.idZone,  parks.zone.zoneName, parks.park.parkName" +
                " FROM parks.zone INNER JOIN parks.park" +
                " ON parks.zone.idPark = parks.park.idPark";

        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();

            rs = stmt.executeQuery(query);

            List<Park> parks = new ArrayList<>();
            while (rs.next()){
                Park park = new Park();
                ParkZone parkZone =  new ParkZone();

                parkZone.setId(rs.getInt(1));
                parkZone.setZoneName(rs.getString(2));

                park.setParkName(rs.getString(3));

                park.addZone(parkZone);
                parks.add(park);
            }
            return parks;

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try { con.close(); } catch(SQLException se) {  }
            try { stmt.close(); } catch(SQLException se) {  }
            try { rs.close(); } catch(SQLException se) {  }
        }
        return null;
    }

    @Override
    public void delete(int id) {
        Command command = tablesDirector.getCommand("parks");

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

        Command command = tablesDirector.getCommand("parks");

        String query = command.formUpdateStatement(columnName);

        try {
            con = DriverManager.getConnection(url, user, password);
            ParksTable parksTable = (ParksTable) command;
            String parkIDQuery = parksTable.getParkIDQuery(columnName,row);
            Object id;
            if(parkIDQuery!=null){
                Statement s = con.createStatement();
                ResultSet rs = s.executeQuery(parkIDQuery);
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
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }

    }

    public List<String> getParksInfo(){
        String query = "SELECT DISTINCT parks.park.parkName FROM" +
                "(parks.park INNER JOIN parks.zone ON (parks.park.idPark = parks.zone.idPark)) ";
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();

            rs = stmt.executeQuery(query);

            List<String> parkNames = new ArrayList<>();
            while (rs.next()){

                String parkName = rs.getString(1);
                parkNames.add(parkName);
            }
            return parkNames;

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
        return null;
    }
}
