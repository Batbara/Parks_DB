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
import java.util.Map;

public class FirmDAOImpl implements ParksAndRecDAO {
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    private TablesDirector tablesDirector = new TablesDirector();
    @Override
    public void insert() {

    }

    @Override
    public List<Firm> select() {
         final String url = "jdbc:mysql://localhost:3306/parks?autoReconnect=true&useSSL=false";
         final String user = "root";
         final String password = "leaf";
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
    public void delete() {

    }

    @Override
    public void updateTable(String columnName, Object value, int row) {
        final String url = "jdbc:mysql://localhost:3306/parks?autoReconnect=true&useSSL=false";
        final String user = "root";
        final String password = "leaf";

        Command command = tablesDirector.getCommand("firm");

        String query = command.formStatement(columnName);

        try {
            con = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = con.prepareStatement(query);

            statement.setObject(1,value);
            statement.setInt(2,row+1);

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
