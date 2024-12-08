package UI;

import Singleton.InventoryManager;
import CompositeAndIterator.Hardware;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UIAddToCartTab {

    private JTextArea displayArea;
    private List<Hardware> cart;

    public UIAddToCartTab(JTextArea displayArea) {
        this.displayArea = displayArea;
        this.cart = new ArrayList<>();
    }

    protected JPanel createAddToCartTab() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Dropdown for stock type
        JComboBox<String> stockTypeComboBox = new JComboBox<>();
        InventoryManager.getInstance().getHardwareStocks().keySet().forEach(stockTypeComboBox::addItem);

        // List of items (e.g., hardware items or product descriptions)
        JList<String> itemsList = new JList<>(getItemDescriptions());
        JScrollPane itemsScrollPane = new JScrollPane(itemsList);

        JTextField quantityField = new JTextField();
        JButton addButton = new JButton("Add to Cart");

        panel.add(new JLabel("Select Item:"), BorderLayout.NORTH);
        panel.add(itemsScrollPane, BorderLayout.CENTER);
        panel.add(new JLabel("Quantity:"), BorderLayout.WEST);
        panel.add(quantityField, BorderLayout.EAST);
        panel.add(addButton, BorderLayout.SOUTH);

        // Action Listener for Add Button
        addButton.addActionListener(e -> {
            String selectedItem = itemsList.getSelectedValue();
            String quantityText = quantityField.getText();

            if (selectedItem != null && !quantityText.isBlank()) {
                try {
                    int quantity = Integer.parseInt(quantityText);
                    Hardware hardware = InventoryManager.getInstance().getHardwareStock((String) stockTypeComboBox.getSelectedItem()).findHardwareByDescription(selectedItem);
                    for (int i = 0; i < quantity; i++) {
                        cart.add(hardware);
                    }
                    displayArea.append("Added " + quantity + " of " + selectedItem + " to cart.\n");
                } catch (NumberFormatException ex) {
                    displayArea.setText("Invalid quantity. Please enter a valid number.");
                }
            } else {
                displayArea.setText("Please select an item and enter a quantity.");
            }
        });

        return panel;
    }

    private String[] getItemDescriptions() {
        // Get all hardware items and their descriptions
        List<Hardware> allHardware = InventoryManager.getInstance().getAllHardware();
        return allHardware.stream().map(Hardware::getDescription).toArray(String[]::new);
    }

    public List<Hardware> getCart() {
        return cart;
    }
}