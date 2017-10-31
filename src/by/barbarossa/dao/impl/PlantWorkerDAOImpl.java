package by.barbarossa.dao.impl;

import by.barbarossa.dao.ParksAndRecDAO;
import by.barbarossa.dao.command.TablesDirector;
import by.barbarossa.entity.Address;
import by.barbarossa.entity.ParkDecorator;
import by.barbarossa.entity.PlantWorker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlantWorkerDAOImpl implements ParksAndRecDAO {
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
    public List<PlantWorker> select() {
        String query = "SELECT parks.plantworker.idplantworker, parks.plantworker.plantworkername, " +
                "parks.address.city, parks.address.street, parks.address.buildingNum, parks.plantworker.phoneNumber" +
                " FROM parks.plantWorker INNER JOIN parks.address" +
                " ON parks.plantWorker.idAddress = parks.address.idaddress";

        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();

            rs = stmt.executeQuery(query);

            List<PlantWorker> plantWorkers = new ArrayList<>();
            while (rs.next()){
                PlantWorker plantWorker = new PlantWorker();

                plantWorker.setId(rs.getInt(1));
                plantWorker.setName(rs.getString(2));

                Address address =  new Address();
                address.setCity(rs.getString(3));
                address.setStreet(rs.getString(4));
                address.setBuildingNum(rs.getInt(5));

                plantWorker.setPhoneNum(rs.getString(6));

                plantWorker.setAddress(address);
                plantWorkers.add(plantWorker);
            }
            return plantWorkers;

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
