package by.barbarossa.representation.table;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.List;
import java.util.Vector;

public class TableCreator {
    public static DefaultTableModel createTableModel(List<String> header, List<List<String>> rows) {

        DefaultTableModel tableModel = new DefaultTableModel(header.toArray(), 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }
        };
        for (List<String> row : rows) {
            tableModel.addRow(new Vector(row));
        }
        return tableModel;

    }

}
