package by.barbarossa.controller.impl;

import by.barbarossa.controller.Controller;
import by.barbarossa.entity.Firm;
import by.barbarossa.entity.Park;
import by.barbarossa.entity.ParkZone;
import by.barbarossa.representation.MainFrame;
import by.barbarossa.representation.dialogs.DialogsFactory;
import by.barbarossa.representation.dialogs.impl.AddToParksDialog;
import by.barbarossa.representation.listeners.AddRecordListener;
import by.barbarossa.representation.listeners.DeleteRecordListener;
import by.barbarossa.representation.listeners.EditTableListener;
import by.barbarossa.representation.listeners.ShowDialogListener;
import by.barbarossa.representation.listeners.ViewTableListener;
import by.barbarossa.representation.table.GUITools;
import by.barbarossa.representation.table.TableView;
import by.barbarossa.service.ServiceFactory;
import by.barbarossa.service.impl.ParksService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

public class ParksController implements Controller, Observer{
    private ServiceFactory factory = ServiceFactory.getInstance();
    private ParksService parksService = (ParksService) factory.getService("parks");
    private Map<String, String> columnNameMap;
    public ParksController(){
        columnNameMap = new LinkedHashMap<>();
        initColumnNames();
    }

    private void initColumnNames() {
        String[] header = {"id", "Имя зоны", "Имя парка"};
        String[] dbNames = {"idZone", "zoneName", "parkName"};
        int columnCount = header.length;
        for (int element = 0; element < columnCount; element++) {
            columnNameMap.put(header[element], dbNames[element]);
        }
    }

    @Override
    public void repaintTable() {
        List<Park> result = parksService.select();

        String[] header = {"id", "Имя зоны", "Имя парка"};
        List<String> headers = Arrays.asList(header);

        List<List<String>> rows = new ArrayList<>();

        for (Park park : result) {
            for(ParkZone parkZone : park.getZones()) {
                List<String> row = new ArrayList<>();
                String zoneName = parkZone.getZoneName();
                row.add(Integer.toString(parkZone.getId()));
                row.add(zoneName);
                row.add(park.getParkName());
                rows.add(row);
            }
        }

        TableView tableView = MainFrame.getInstance().getTableView();
        JTable table = tableView.getTable();

        DefaultTableModel tableModel = GUITools.createTableModel(headers, rows);
        table.setModel(tableModel);
        tableView.setTableName("Парки и зоны");

        tableModel.fireTableDataChanged();

        tableModel.addTableModelListener(new EditTableListener("Парки и зоны"));
    }

    @Override
    public void updateData(Object data) {
        Object[] dataArray = (Object[]) data;
        String columnName = (String) dataArray[0];
        Object value = dataArray[1];
        Object row = dataArray[2];
        parksService.updateTable(columnNameMap.get(columnName), value, row);
        repaintTable();
    }

    @Override
    public void deleteRow(int id) {
        parksService.delete(id);
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

    @Override
    public void addRecord(Map<String, String> record) {
        ParkZone parkZone = new ParkZone();
        parkZone.setInfo(record);
        parksService.insert(parkZone);
        DefaultTableModel tableModel = (DefaultTableModel) MainFrame.getInstance().getTable().getModel();
        List<String> values = new ArrayList<>();
        values.add(Integer.toString(tableModel.getRowCount()+1));
        for (String key : record.keySet()){
            values.add(record.get(key));
        }
        tableModel.addRow(new Vector(values));
    }
    private void sendParkInfo(){
        List<String> parksInfo = parksService.getParksInfo();
        DialogsFactory dialogsFactory = DialogsFactory.getInstance();
        AddToParksDialog addToPlantDialog = (AddToParksDialog) dialogsFactory.getDialog("Парки и зоны");
        addToPlantDialog.setParksInfo(parksInfo);
    }
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof ViewTableListener) {
            assert parksService != null;
            repaintTable();
            MainFrame.getInstance().setTableNameLabel("Таблица \"Парки\"");
        }
        if (o instanceof EditTableListener) {
            updateData(arg);

        }
        if (o instanceof DeleteRecordListener) {
            deleteRow((int) arg);
        }
        if(o instanceof ShowDialogListener){
            sendParkInfo();
        }
        if(o instanceof AddRecordListener){
            if(arg instanceof Map) {
                Map<String,String> argMap = (Map<String, String>)arg;
                addRecord(argMap);
            }
        }
    }
}
