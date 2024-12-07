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

public class UIRemoveProductsTab {
    private JTextArea displayArea;
    private DefaultTableModel tableModel;

    public UIRemoveProductsTab(JTextArea displayArea) {
        this.displayArea = displayArea;
    }

    protected JPanel createRemoveProductsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Dropdown for stock type
        JComboBox<String> stockTypeComboBox = new JComboBox<>();
        stockTypeComboBox.addItem("All");
        InventoryManager.getInstance().getHardwareStocks().keySet().forEach(stockTypeComboBox::addItem);

        // Table for displaying products
        String[] columnNames = { "Select", "Type", "Description", "Quantity", "Unit Price" };
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Only the first column (checkbox) is editable
                return column == 0;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? Boolean.class : super.getColumnClass(columnIndex);
            }
        };
        JTable productTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(productTable);

        // Panel for action buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Remove Button
        JButton removeButton = new JButton("Remove Selected");
        removeButton.addActionListener(e -> {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                boolean isSelected = (Boolean) tableModel.getValueAt(i, 0);
                if (isSelected) {
                    String stockType = (String) tableModel.getValueAt(i, 1);
                    String description = (String) tableModel.getValueAt(i, 2);

                    HardwareStock stock = InventoryManager.getInstance().getHardwareStock(stockType);
                    var hardware = stock.findHardwareByDescription(description);

                    if (hardware != null) {
                        // Show input dialog to specify quantity to remove
                        String input = JOptionPane.showInputDialog(
                                panel,
                                "Enter the quantity to remove for " + description + ":",
                                "Specify Quantity",
                                JOptionPane.QUESTION_MESSAGE
                        );

                        if (input != null && !input.trim().isEmpty()) {
                            try {
                                int quantityToRemove = Integer.parseInt(input);

                                if (quantityToRemove <= 0) {
                                    JOptionPane.showMessageDialog(
                                            panel,
                                            "Please enter a positive number.",
                                            "Invalid Quantity",
                                            JOptionPane.ERROR_MESSAGE
                                    );
                                    continue;
                                }

                                int currentQuantity = stock.getQuantity(hardware);

                                if (quantityToRemove > currentQuantity) {
                                    JOptionPane.showMessageDialog(
                                            panel,
                                            "You cannot remove more than the current stock quantity (" + currentQuantity + ").",
                                            "Quantity Exceeded",
                                            JOptionPane.ERROR_MESSAGE
                                    );
                                    continue;
                                }

                                InventoryManager.getInstance().removeHardware(stockType, hardware, quantityToRemove);
                                displayArea.append("Removed " + quantityToRemove + " units of " + description + " from " + stockType + "\n");

                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(
                                        panel,
                                        "Please enter a valid number.",
                                        "Invalid Input",
                                        JOptionPane.ERROR_MESSAGE
                                );
                            }
                        }
                    }
                }
            }

            // Refresh the table
            String selectedType = (String) stockTypeComboBox.getSelectedItem();
            updateProductTable(tableModel, "All".equals(selectedType) ? null : selectedType);
        });

        // Delete Button
        JButton deleteButton = new JButton("Delete Selected");
        deleteButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(
                    panel,
                    "Are you sure you want to delete the selected products completely?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (response == JOptionPane.YES_OPTION) {
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    boolean isSelected = (Boolean) tableModel.getValueAt(i, 0);
                    if (isSelected) {
                        String stockType = (String) tableModel.getValueAt(i, 1);
                        String description = (String) tableModel.getValueAt(i, 2);

                        HardwareStock stock = InventoryManager.getInstance().getHardwareStock(stockType);
                        var hardware = stock.findHardwareByDescription(description);

                        if (hardware != null) {
                            InventoryManager.getInstance().removeHardware(stockType, hardware, -1); // Delete completely
                            displayArea.append("Deleted " + description + " completely from " + stockType + "\n");
                        }
                    }
                }

                // Refresh the table
                String selectedType = (String) stockTypeComboBox.getSelectedItem();
                updateProductTable(tableModel, "All".equals(selectedType) ? null : selectedType);
            }
        });

        // Add buttons to panel
        buttonPanel.add(removeButton);
        buttonPanel.add(deleteButton);

        // Filter products by stock type
        stockTypeComboBox.addActionListener(e -> {
            String selectedType = (String) stockTypeComboBox.getSelectedItem();
            updateProductTable(tableModel, "All".equals(selectedType) ? null : selectedType);
        });

        // Add components to panel
        panel.add(stockTypeComboBox, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

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

            tableModel.addRow(new Object[] {
                    false,                                  // Select checkbox (initially unchecked)
                    stock.getDescription(),                 // Stock type
                    hardware.getDescription(),              // Hardware description
                    numberFormat.format(quantity),          // Quantity in stock
                    numberFormat.format(unitPrice)          // Price per unit
            });
        }
    }
}
