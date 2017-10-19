package by.barbarossa.controller.impl;

import by.barbarossa.controller.Controller;
import by.barbarossa.entity.Firm;
import by.barbarossa.entity.Plant;
import by.barbarossa.representation.MainFrame;
import by.barbarossa.representation.listeners.EditTableListener;
import by.barbarossa.representation.listeners.ViewTableListener;
import by.barbarossa.representation.table.GUITools;
import by.barbarossa.representation.table.TableView;
import by.barbarossa.service.ServiceFactory;
import by.barbarossa.service.impl.PlantService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class PlantController implements Observer, Controller {
    private ServiceFactory factory = ServiceFactory.getInstance();
    private PlantService plantService = (PlantService) factory.getService("plant");
    private Map<String, String> columnNameMap;

    public PlantController() {
        columnNameMap = new LinkedHashMap<>();
        initColumnNames();
    }

    private void initColumnNames() {
        String[] header = {"id", "Имя зоны", "Вид", "Возраст", "Дата высадки", "Периодичность", "Норма воды"};
        String[] dbNames = {"idplant", "zoneName", "species", "age", "plantingDate", "periodicity", "waterNorm"};
        int columnCount = header.length;
        for (int element = 0; element < columnCount; element++) {
            columnNameMap.put(header[element], dbNames[element]);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof ViewTableListener) {
            assert plantService != null;
            repaintTable();
            MainFrame.getInstance().setTableNameLabel("Таблица \"Растения\"");
        }
        if (o instanceof EditTableListener) {
            updateData(arg);

        }
    }

    @Override
    public void repaintTable() {
        List<Plant> result = plantService.select();


        String[] header = {"id", "Имя зоны", "Вид", "Возраст", "Дата высадки", "Периодичность", "Норма воды"};
        List<String> headers = Arrays.asList(header);

        List<List<String>> rows = new ArrayList<>();

        for (Plant plant : result) {
            List<String> row = plant.getInfo();
            rows.add(row);
        }

        TableView tableView = MainFrame.getInstance().getTableView();
        JTable table = tableView.getTable();


        DefaultTableModel tableModel = GUITools.createTableModel(headers, rows);
        table.setModel(tableModel);

        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(3).setPreferredWidth(35);
        table.getColumnModel().getColumn(6).setPreferredWidth(35);


        tableView.setTableName("Растения");

        tableModel.fireTableDataChanged();

        tableModel.addTableModelListener(new EditTableListener("Растения"));
    }

    @Override
    public void updateData(Object data) {
        Object[] dataArray = (Object[]) data;
        String columnName = (String) dataArray[0];
        Object value = dataArray[1];
        Object row = dataArray[2];
        plantService.updateTable(columnNameMap.get(columnName), value, row);
        repaintTable();
    }

    @Override
    public void deleteRow(int id) {

    }

    @Override
    public void addRecord(Map<String, String> record) {

    }


}
