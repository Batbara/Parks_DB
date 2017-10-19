package by.barbarossa.controller.impl;

import by.barbarossa.controller.Controller;
import by.barbarossa.entity.Firm;
import by.barbarossa.representation.MainFrame;
import by.barbarossa.representation.listeners.AddRecordListener;
import by.barbarossa.representation.listeners.DeleteRecordListener;
import by.barbarossa.representation.listeners.EditTableListener;
import by.barbarossa.representation.listeners.ViewTableListener;
import by.barbarossa.representation.table.GUITools;
import by.barbarossa.representation.table.TableView;
import by.barbarossa.service.ServiceFactory;
import by.barbarossa.service.impl.FirmService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
    private FirmService firmService = (FirmService) factory.getService("firm");
    private Map<String, String> columnNameMap;

    public FirmController() {
        columnNameMap = new HashMap<>();
        initColumnNames();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof ViewTableListener) {
            assert firmService != null;
            repaintTable();
            MainFrame.getInstance().setTableNameLabel("Таблица \"Фирма\"");
        }
        if (o instanceof EditTableListener) {
            updateData(arg);

        }
        if (o instanceof DeleteRecordListener) {
            deleteRow((int) arg);
        }
        if(o instanceof AddRecordListener){
            if(arg instanceof Map) {
                Map<String,String> argMap = (Map<String, String>)arg;
                addRecord(argMap);
            }
        }


    }

    private void initColumnNames() {
        String[] header = {"id", "Имя фирмы", "Город", "Улица", "Номер здания"};
        String[] dbNames = {"idfirm", "firmName", "city", "street", "buildingNum"};
        int columnCount = header.length;
        for (int element = 0; element < columnCount; element++) {
            columnNameMap.put(header[element], dbNames[element]);
        }
    }

    public void repaintTable() {
        List<Firm> result = firmService.select();

        String[] header = {"id", "Имя фирмы", "Город", "Улица", "Номер здания"};
        List<String> headers = Arrays.asList(header);

        List<List<String>> rows = new ArrayList<>();

        for (Firm firm : result) {
            List<String> row = firm.getInfo();
            rows.add(row);
        }

        TableView tableView = MainFrame.getInstance().getTableView();
        JTable table = tableView.getTable();

        DefaultTableModel tableModel = GUITools.createTableModel(headers, rows);
        table.setModel(tableModel);
        tableView.setTableName("Фирма");

        tableModel.fireTableDataChanged();

        tableModel.addTableModelListener(new EditTableListener("Фирма"));
    }

    public void updateData(Object data) {
        Object[] dataArray = (Object[]) data;
        String columnName = (String) dataArray[0];
        Object value = dataArray[1];
        Object row = dataArray[2];
        firmService.updateTable(columnNameMap.get(columnName), value, row);
        repaintTable();
    }

    public void deleteRow(int id) {
        firmService.delete(id);
        int rowNum = 0;
        DefaultTableModel tableModel = (DefaultTableModel) MainFrame.getInstance().getTable().getModel();
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            String value = tableModel.getValueAt(row, 0).toString();
            if (Integer.parseInt(value) == id) {
                rowNum = row;
                break;
            }
        }
        tableModel.removeRow(rowNum);
    }

    public void addRecord(Map<String, String> record){
        Firm firm = new Firm();
        firm.setInfo(record);
        firmService.insert(firm);

        DefaultTableModel tableModel = (DefaultTableModel) MainFrame.getInstance().getTable().getModel();
        List<String> values = new ArrayList<>();
        values.add(Integer.toString(tableModel.getRowCount()+1));
        for (String key : record.keySet()){
            values.add(record.get(key));
        }
        tableModel.addRow(new Vector(values));
    }


}
