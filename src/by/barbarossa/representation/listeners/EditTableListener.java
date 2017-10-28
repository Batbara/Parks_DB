package by.barbarossa.representation.listeners;

import by.barbarossa.controller.Controller;
import by.barbarossa.controller.ControllerFactory;
import by.barbarossa.representation.table.TableView;
import javafx.scene.control.Tab;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.util.Observable;

public class EditTableListener extends Observable implements TableModelListener {
    public EditTableListener(String tableName){
        ControllerFactory factory = ControllerFactory.getInstance();
        Controller controller = factory.getController(tableName);

        if (tableName.equals("Фирма") || tableName.equals("Растения")
                || tableName.equals("Парки и зоны") || tableName.equals("Декораторы парка")) {
            // FirmController firmController = (FirmController) factory.getController("firm");
            //this.menuName = menuName;
            this.addObserver(controller);
        }
    }

    @Override
    public void tableChanged(TableModelEvent e) {
//        Object source = e.getSource();
//        if(source instanceof )
        if(e.getType()==TableModelEvent.UPDATE) {
            System.out.println("table is changing");
            int row = e.getFirstRow();
            int column = e.getColumn();
            TableModel model = (TableModel) e.getSource();
            String columnName = model.getColumnName(column);
            Object data = model.getValueAt(row, column);

            Object[] dataToPass = new Object[3];
            dataToPass[0] = columnName;
            dataToPass[1] = data;
            dataToPass[2] = model.getValueAt(row,0);

            setChanged();
            notifyObservers(dataToPass);
        }
    }
}
