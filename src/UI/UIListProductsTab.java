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

/**
 * The UIListProductsTab class provides a user interface tab for listing products in the inventory.
 * It allows users to view the type, description, quantity, unit price, and total price of hardware items.
 */
public class UIListProductsTab {

    private DefaultTableModel tableModel;

    protected JPanel createListProductsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Dropdown for stock type
        JComboBox<String> stockTypeComboBox = new JComboBox<>();
        stockTypeComboBox.addItem("All");
        InventoryManager.getInstance().getHardwareStocks().keySet().forEach(stockTypeComboBox::addItem);

        // Table for displaying hardware
        String[] columnNames = { "Type", "Description", "Quantity", "Unit Price", "Total Price" };
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make all cells non-editable
                return false;
            }
        };
        JTable productTable = new JTable(tableModel);

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
        tableModel.setRowCount(0);

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
                    stock.getDescription(),        // Stock type
                    hardware.getDescription(),     // Hardware description
                    numberFormat.format(quantity), // Quantity in stock
                    "$"+numberFormat.format(unitPrice), // Price per unit
                    "$"+numberFormat.format(totalPrice) // Total price
            });
        }
    }
}
