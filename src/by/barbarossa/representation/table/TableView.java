package by.barbarossa.representation.table;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.List;

public class TableView {
    private JTable table;
    private JPanel holder;
    private List <String> header;

    public TableView(List<String> header){
        this.header = header;
        initTable();
        JScrollPane tableScroll = new JScrollPane(table);

        holder = new JPanel();
        holder.setLayout(new BorderLayout());
        holder.add(tableScroll);
    }
    private void initTable() {
        table =  new JTable();
        DefaultTableModel tableModel = new DefaultTableModel(header.toArray(),0) {
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        table.setModel(tableModel);

        for (String headerValue : header ){
            TableColumnModel columnModel = table.getColumnModel();
            TableColumn column = columnModel.getColumn(header.indexOf(headerValue));
            column.setHeaderValue(headerValue);
        }
        table.setRowSelectionAllowed(false);
        table.setFont(new Font("Helvetica", Font.PLAIN, 14));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.setDefaultRenderer(String.class, centerRenderer);

        table.setRowHeight(30);
    }

    public JPanel getHolder() {
        return holder;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }
}
