package by.barbarossa.controller.impl;

import by.barbarossa.controller.Controller;
import by.barbarossa.entity.PlantWorker;
import by.barbarossa.entity.Schedule;
import by.barbarossa.representation.MainFrame;
import by.barbarossa.representation.listeners.EditTableListener;
import by.barbarossa.representation.listeners.ViewTableListener;
import by.barbarossa.representation.table.GUITools;
import by.barbarossa.representation.table.TableView;
import by.barbarossa.service.ServiceFactory;
import by.barbarossa.service.impl.PlantWorkerService;
import by.barbarossa.service.impl.ScheduleService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

public class ScheduleController implements Controller {
    private ServiceFactory factory = ServiceFactory.getInstance();
    private ScheduleService scheduleService = (ScheduleService) factory.getService("schedule");
    private Map<String, String> columnNameMap;
    public ScheduleController(){
        columnNameMap = new HashMap<>();
        initColumnNames();
    }
    private void initColumnNames() {
        String[] header = {"id", "Имя", "Дата", "Растение"};

        String[] dbNames = {"idschedule", "plantworkername", "date","species",};
        int columnCount = header.length;
        for (int element = 0; element < columnCount; element++) {
            columnNameMap.put(header[element], dbNames[element]);
        }
    }
    @Override
    public void repaintTable() {
        List<Schedule> result = scheduleService.select();

        String[] header = {"id", "Имя", "Дата", "Растение"};
        List<String> headers = Arrays.asList(header);

        List<List<String>> rows = new ArrayList<>();

        for (Schedule schedule : result) {
            List<String> row = schedule.getInfo();
            rows.add(row);
        }

        TableView tableView = MainFrame.getInstance().getTableView();
        JTable table = tableView.getTable();

        DefaultTableModel tableModel = GUITools.createTableModel(headers, rows);
        table.setModel(tableModel);
        tableView.setTableName("График");

        tableModel.fireTableDataChanged();

        tableModel.addTableModelListener(new EditTableListener("График"));
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
            assert scheduleService != null;
            repaintTable();
            MainFrame.getInstance().setTableNameLabel("Таблица \"График полива\"");
        }
    }
}
