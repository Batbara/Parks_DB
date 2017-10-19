package by.barbarossa.dao.impl;

import by.barbarossa.dao.ParksAndRecDAO;
import by.barbarossa.dao.command.Command;
import by.barbarossa.dao.command.TablesDirector;
import by.barbarossa.entity.Address;
import by.barbarossa.entity.Firm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FirmDAOImpl implements ParksAndRecDAO {
    private static final String url = "jdbc:mysql://localhost:3306/parks?autoReconnect=true&useSSL=false";
    private static final String user = "root";
    private static final String password = "leaf";
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    private TablesDirector tablesDirector = new TablesDirector();
    @Override
    public void insert(Object o) {
         Firm firm = (Firm)o;
         Address address = firm.getAddress();

        try {
            con = DriverManager.getConnection(url, user, password);

            String query = "INSERT INTO parks.address (city,street,buildingNum) VALUES (?,?,?)";
            PreparedStatement statement = con.prepareStatement(query);
           // statement.setInt(1,address.getId());
            statement.setString(1,address.getCity());
            statement.setString(2,address.getStreet());
            statement.setInt(3,address.getBuildingNum());
            statement.executeUpdate();

            ResultSet rs = statement.executeQuery("SELECT LAST_INSERT_ID()");
            int addrID = -1;
            if (rs.next()) {
                addrID = rs.getInt(1);
            }

            String firmQuery = "INSERT INTO parks.firm (firmName,idAddress) VALUES (?,?)";
            statement = con.prepareStatement(firmQuery);
            //statement.setInt(1,firm.getId());
            statement.setString(1,firm.getName());
            statement.setInt(2,addrID);
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
    public List<Firm> select() {
        String query = "SELECT parks.firm.idfirm, parks.firm.firmname, parks.address.city, " +
                "parks.address.street, parks.address.buildingNum" +
                " FROM parks.firm INNER JOIN parks.address" +
                " ON parks.firm.idAddress = parks.address.idaddress";

        try {
             con = DriverManager.getConnection(url, user, password);
             stmt = con.createStatement();

             rs = stmt.executeQuery(query);

            List<Firm> firms = new ArrayList<>();
            while (rs.next()){
                Firm firm = new Firm();

                firm.setId(rs.getInt(1));
                firm.setName(rs.getString(2));

                Address address =  new Address();
                address.setCity(rs.getString(3));
                address.setStreet(rs.getString(4));
                address.setBuildingNum(rs.getInt(5));

                firm.setAddress(address);
                firms.add(firm);
            }
            return firms;

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

        Command command = tablesDirector.getCommand("firm");

        String query = command.formDeleteStatement(Integer.toString(id));
        try {
            con = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = con.prepareStatement(query);

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
    public void updateTable(String columnName, Object value, Object row) {


        Command command = tablesDirector.getCommand("firm");

        String query = command.formUpdateStatement(columnName);

        try {
            con = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = con.prepareStatement(query);

            statement.setObject(1,value);
            statement.setObject(2,row);

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
}
