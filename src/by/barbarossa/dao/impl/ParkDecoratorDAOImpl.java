package by.barbarossa.dao.impl;

import by.barbarossa.dao.ParksAndRecDAO;
import by.barbarossa.dao.command.Command;
import by.barbarossa.dao.command.TablesDirector;
import by.barbarossa.entity.Address;
import by.barbarossa.entity.ParkDecorator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ParkDecoratorDAOImpl implements ParksAndRecDAO{
    private static final String url = "jdbc:mysql://localhost:3306/parks?autoReconnect=true&useSSL=false";
    private static final String user = "root";
    private static final String password = "leaf";
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    private TablesDirector tablesDirector = new TablesDirector();
    @Override
    public void insert(Object o) {
        ParkDecorator parkDecorator = (ParkDecorator) o;
        Address address = parkDecorator.getAddress();

        try {
            con = DriverManager.getConnection(url, user, password);

            String query = "INSERT INTO parks.address (city,street,buildingNum) VALUES (?,?,?)";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1,address.getCity());
            statement.setString(2,address.getStreet());
            statement.setInt(3,address.getBuildingNum());
            statement.executeUpdate();

            ResultSet rs = statement.executeQuery("SELECT LAST_INSERT_ID()");
            int addrID = -1;
            if (rs.next()) {
                addrID = rs.getInt(1);
            }

            String firmQuery = "INSERT INTO parks.parkdecorator (parkdecoratorName," +
                    "idAddress, education, almamater, phonenumber,category) VALUES (?,?,?,?,?,?)";
            statement = con.prepareStatement(firmQuery);
            statement.setString(1,parkDecorator.getName());
            statement.setInt(2,addrID);
            statement.setString(3,parkDecorator.getEducation());
            statement.setString(4,parkDecorator.getAlmaMater());
            statement.setString(5,parkDecorator.getPhoneNum());
            statement.setString(6,parkDecorator.getCategory());
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
    public List<ParkDecorator> select() {
        String query = "SELECT parks.parkdecorator.idparkdecorator, parks.parkdecorator.parkdecoratorname, " +
                "parks.address.city, parks.address.street, parks.address.buildingNum," +
                "parks.parkdecorator.education, parks.parkdecorator.almamater, " +
                "parks.parkdecorator.phonenumber, parks.parkdecorator.category" +
                " FROM parks.parkdecorator INNER JOIN parks.address" +
                " ON parks.parkdecorator.idAddress = parks.address.idaddress";

        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();

            rs = stmt.executeQuery(query);

            List<ParkDecorator> parkDecorators = new ArrayList<>();
            while (rs.next()){
                ParkDecorator parkDecorator = new ParkDecorator();

                parkDecorator.setId(rs.getInt(1));
                parkDecorator.setName(rs.getString(2));

                Address address =  new Address();
                address.setCity(rs.getString(3));
                address.setStreet(rs.getString(4));
                address.setBuildingNum(rs.getInt(5));

                parkDecorator.setEducation(rs.getString(6));
                parkDecorator.setAlmaMater(rs.getString(7));
                parkDecorator.setPhoneNum(rs.getString(8));
                parkDecorator.setCategory(rs.getString(9));

                parkDecorator.setAddress(address);
                parkDecorators.add(parkDecorator);
            }
            return parkDecorators;

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
        Command command = tablesDirector.getCommand("decorator");

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

        Command command = tablesDirector.getCommand("decorator");

        String query = command.formUpdateStatement(columnName);
        String idQuery = command.formIDStatement(columnName,row);
        try {

            con = DriverManager.getConnection(url, user, password);
            Object id;
            if(idQuery!=null){
                ResultSet rs = con.createStatement().executeQuery(idQuery);
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
}
