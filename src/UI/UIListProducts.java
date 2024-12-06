package UI;

import CompositeAndIterator.Hardware;
import CompositeAndIterator.HardwareStock;
import Singleton.InventoryManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.Locale;

public class UIListProducts {

    private JTextArea displayArea;
    private DefaultTableModel tableModel;

    public UIListProducts( JTextArea displayArea) {
        this.displayArea = displayArea;
    }

    protected JPanel createListProductsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Dropdown for stock type
        JComboBox<String> stockTypeComboBox = new JComboBox<>();
        stockTypeComboBox.addItem("All");
        InventoryManager.getInstance().getHardwareStocks().keySet().forEach(stockTypeComboBox::addItem);

        // Editable Table for displaying hardware
        String[] columnNames = { "Type", "Description", "Quantity", "Unit Price", "Total Price" };
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Allow editing only Quantity (column 2) and Unit Price (column 3)
                return column == 2 || column == 3;
            }
        };
        JTable productTable = new JTable(tableModel);

        // Add a listener to update inventory on table edit
        productTable.getModel().addTableModelListener(e -> {
            int row = e.getFirstRow();
            int column = e.getColumn();

            if (column == 2 || column == 3) { // Quantity or Unit Price
                try {
                    String stockType = (String) productTable.getValueAt(row, 0);
                    String description = (String) productTable.getValueAt(row, 1);
                    int newQuantity = Integer.parseInt(productTable.getValueAt(row, 2).toString());
                    double newUnitPrice = Double.parseDouble(productTable.getValueAt(row, 3).toString());

                    // Confirmation Dialog before updating the values
                    int response = JOptionPane.showConfirmDialog(
                            panel,
                            "Are you sure you want to update the following?\n" +
                                    "Hardware: " + description + "\n" +
                                    "Quantity: " + newQuantity + "\n" +
                                    "Unit Price: " + newUnitPrice,
                            "Confirm Update",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE
                    );

                    if (response == JOptionPane.OK_OPTION) {
                        // Update inventory only after user confirms
                        HardwareStock stock = InventoryManager.getInstance().getHardwareStock(stockType);
                        var hardware = stock.findHardwareByDescription(description);

                        if (hardware != null) {
                            // Update values in the inventory after confirmation
                            stock.setQuantity(hardware, newQuantity);
                            hardware.setPrice(newUnitPrice);

                            // Log the update
                            displayArea.append("Updated " + description + " in " + stockType + "\n");

                            // Reload table to reflect updated data
                            updateProductTable((DefaultTableModel) productTable.getModel(), stockType);
                        }
                    } else {
                        // Reload table to discard changes (in case user clicked Cancel)
                        updateProductTable((DefaultTableModel) productTable.getModel(), stockType);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel, "Invalid input. Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
                    updateProductTable((DefaultTableModel) productTable.getModel(), null); // Reload all
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(productTable);

        panel.add(stockTypeComboBox, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Update table when stock type changes
        stockTypeComboBox.addActionListener(e -> {
            String selectedType = (String) stockTypeComboBox.getSelectedItem();
            updateProductTable(tableModel, "All".equals(selectedType) ? null : selectedType);
        });

        // Populate table initially with all products
        updateProductTable(tableModel, null);

        return panel;
    }


    private void updateProductTable(DefaultTableModel tableModel, String stockType) {
        tableModel.setRowCount(0); // Clear the table

        InventoryManager inventoryManager = InventoryManager.getInstance();

        if (stockType == null) { // Display all hardware
            for (HardwareStock stock : inventoryManager.getHardwareStocks().values()) {
                populateTableWithStockData(tableModel, stock);
            }
        } else { // Display hardware for a specific stock type
            HardwareStock stock = inventoryManager.getHardwareStock(stockType);
            if (stock != null) {
                populateTableWithStockData(tableModel, stock);
            }
        }
    }
    protected void updateProductTable() {
        updateProductTable(tableModel, null);
    }

    private void populateTableWithStockData(DefaultTableModel tableModel, HardwareStock stock) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        Iterator<Hardware> iterator = stock.createIterator();
        while (iterator.hasNext()) {
            Hardware hardware = iterator.next();
            int quantity = stock.getQuantity(hardware); // Get the quantity of the hardware
            double unitPrice = hardware.getPrice();
            double totalPrice = quantity * unitPrice;

            tableModel.addRow(new Object[] {
                    stock.getDescription(),       // Stock type
                    hardware.getDescription(),    // Hardware description
                    numberFormat.format(quantity), // Quantity in stock
                    numberFormat.format(unitPrice), // Price per unit
                    numberFormat.format(totalPrice) // Total price
            });
        }
    }
}
