package by.barbarossa.controller.impl;

import by.barbarossa.controller.Controller;
import by.barbarossa.entity.Address;
import by.barbarossa.entity.Firm;
import by.barbarossa.representation.MainFrame;
import by.barbarossa.representation.table.TableCreator;
import by.barbarossa.service.ServiceFactory;
import by.barbarossa.service.impl.FirmService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

public class FirmController implements Controller, Observer {

    private ServiceFactory factory = ServiceFactory.getInstance();
    private FirmService firmService =(FirmService) factory.getService("firm");
    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof String) {
            switch ((String)arg) {
                case "select":
                    assert firmService != null;
                    repaintTable();
                    MainFrame.getInstance().setTableNameLabel("Таблица \"Фирма\"");
            }
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

        JTable table =MainFrame.getInstance().getTable();

        DefaultTableModel tableModel = TableCreator.createTableModel(headers,rows);
        table.setModel(tableModel);
        tableModel.fireTableDataChanged();
    }

}
