package by.barbarossa.controller.impl;

import by.barbarossa.controller.Controller;
import by.barbarossa.entity.Address;
import by.barbarossa.entity.Firm;
import by.barbarossa.representation.MainFrame;
import by.barbarossa.representation.listeners.EditTableListener;
import by.barbarossa.representation.listeners.ViewTableListener;
import by.barbarossa.representation.table.TableCreator;
import by.barbarossa.representation.table.TableView;
import by.barbarossa.service.ServiceFactory;
import by.barbarossa.service.impl.FirmService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

public class FirmController implements Controller, Observer {

    private ServiceFactory factory = ServiceFactory.getInstance();
    private FirmService firmService =(FirmService) factory.getService("firm");
    private Map<String, String> columnNameMap;
    public FirmController(){
        columnNameMap = new HashMap<>();
        initColumnNames();
    }
    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof ViewTableListener){
            assert firmService != null;
            repaintTable();
            MainFrame.getInstance().setTableNameLabel("Таблица \"Фирма\"");
        } else
        {
            if(o instanceof EditTableListener){
                updateData(arg);

            }
        }

    }
    private void initColumnNames(){
        String[] header = {"id","Имя фирмы","Город","Улица","Номер здания"};
        String[] dbNames = {"idfirm","firmName","city","street","buildingNum"};
        int columnCount = header.length;
        for(int element = 0; element<columnCount; element++){
            columnNameMap.put(header[element],dbNames[element]);
        }
    }
    private void repaintTable(){
        List<Firm> result = firmService.select();

        String[] header = {"id","Имя фирмы","Город","Улица","Номер здания"};
        List<String> headers = Arrays.asList(header);

        List<List<String>> rows = new ArrayList<>();

        for(Firm firm : result){
            List <String> row = firm.getInfo();
            rows.add(row);
        }

        TableView tableView = MainFrame.getInstance().getTableView();
        JTable table = tableView.getTable();

        DefaultTableModel tableModel = TableCreator.createTableModel(headers,rows);
        table.setModel(tableModel);
        tableView.setTableName("Фирма");

        tableModel.fireTableDataChanged();

        tableModel.addTableModelListener(new EditTableListener("Фирма"));
    }
    private void updateData(Object data){
        Object[] dataArray = (Object[]) data;
        String columnName = (String)dataArray[0];
        Object value = dataArray[1];
        int row = (int)dataArray[2];
        firmService.updateTable(columnNameMap.get(columnName),value,row);
    }

}
