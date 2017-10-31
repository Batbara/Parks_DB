package by.barbarossa.controller.impl;

import by.barbarossa.controller.Controller;
import by.barbarossa.entity.ParkDecorator;
import by.barbarossa.entity.PlantWorker;
import by.barbarossa.representation.MainFrame;
import by.barbarossa.representation.listeners.EditTableListener;
import by.barbarossa.representation.listeners.ViewTableListener;
import by.barbarossa.representation.table.GUITools;
import by.barbarossa.representation.table.TableView;
import by.barbarossa.service.ServiceFactory;
import by.barbarossa.service.impl.FirmService;
import by.barbarossa.service.impl.PlantWorkerService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class PlantWorkerController implements Controller, Observer {

    private ServiceFactory factory = ServiceFactory.getInstance();
    private PlantWorkerService pWorkerService = (PlantWorkerService) factory.getService("plantworker");
    private Map<String, String> columnNameMap;
    public PlantWorkerController(){
        columnNameMap = new HashMap<>();
        initColumnNames();
    }
    private void initColumnNames() {
        String[] header = {"id", "Имя", "Город", "Улица", "Номер здания", "Телефон"};

        String[] dbNames = {"idplantworker", "plantworkername", "city", "street", "buildingNum",
               "phoneNumber"};
        int columnCount = header.length;
        for (int element = 0; element < columnCount; element++) {
            columnNameMap.put(header[element], dbNames[element]);
        }
    }
    @Override
    public void repaintTable() {
        List<PlantWorker> result = pWorkerService.select();

        String[] header = {"id", "Имя", "Город", "Улица", "Номер здания", "Телефон"};
        List<String> headers = Arrays.asList(header);

        List<List<String>> rows = new ArrayList<>();

        for (PlantWorker plantWorker : result) {
            List<String> row = plantWorker.getInfo();
            rows.add(row);
        }

        TableView tableView = MainFrame.getInstance().getTableView();
        JTable table = tableView.getTable();

        DefaultTableModel tableModel = GUITools.createTableModel(headers, rows);
        table.setModel(tableModel);
        tableView.setTableName("Cлужители парка");

        tableModel.fireTableDataChanged();

        tableModel.addTableModelListener(new EditTableListener("Служители парка"));
    }

    @Override
    public void updateData(Object data) {

    }

    @Override
    public void deleteRow(int id) {

    }

    @Override
    public void addRecord(Map<String, String> record) {

    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof ViewTableListener) {
            assert pWorkerService != null;
            repaintTable();
            MainFrame.getInstance().setTableNameLabel("Таблица \"Служители парка\"");
        }
    }
}
