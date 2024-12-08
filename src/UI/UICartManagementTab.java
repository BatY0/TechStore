package UI;

import Singleton.InventoryManager;
import CompositeAndIterator.Hardware;
import Strategy.CargoStrategy;
import Strategy.Order;
import Strategy.StandardShipping;
import Strategy.ExpressShipping;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the cart management tab in the UI.
 * It allows users to add items to the cart, remove items from the cart, and complete an order.
 */

public class UICartManagementTab {

    private JTextArea displayArea;
    private List<Hardware> cart;
    private JList<String> cartList;
    private DefaultListModel<String> cartListModel;

    public UICartManagementTab(JTextArea displayArea) {
        this.displayArea = displayArea;
        this.cart = new ArrayList<>();
        this.cartListModel = new DefaultListModel<>();
    }

    protected JPanel createCartManagementTab() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel for adding products to the cart
        JPanel addProductPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // Dropdown for selecting stock type
        JComboBox<String> stockTypeComboBox = new JComboBox<>();
        InventoryManager.getInstance().getHardwareStocks().keySet().forEach(stockTypeComboBox::addItem);

        // List of items (hardware descriptions)
        JList<String> itemsList = new JList<>(getItemDescriptions());
        JScrollPane itemsScrollPane = new JScrollPane(itemsList);

        // Quantity field and button to add to the cart
        JTextField quantityField = new JTextField(5);
        JButton addButton = new JButton("Add to Cart");

        addProductPanel.add(new JLabel("Select Item:"));
        addProductPanel.add(itemsScrollPane);
        addProductPanel.add(new JLabel("Quantity:"));
        addProductPanel.add(quantityField);
        addProductPanel.add(addButton);


        addButton.addActionListener(e -> {
            String selectedItem = itemsList.getSelectedValue();
            String quantityText = quantityField.getText();

            if (selectedItem != null && !quantityText.isBlank()) {
                try {
                    int quantity = Integer.parseInt(quantityText);
                    String selectedStockType = (String) stockTypeComboBox.getSelectedItem();

                    // Retrieve the hardware item from the inventory based on the selected stock and item description
                    Hardware hardware = InventoryManager.getInstance().getHardwareStock(selectedStockType).findHardwareByDescription(selectedItem);

                    if (hardware != null) {
                        // Check if there's enough stock available
                        int availableStock = InventoryManager.getInstance().getHardwareStock(selectedStockType).getQuantity(hardware);
                        if (availableStock >= quantity) {
                            for (int i = 0; i < quantity; i++) {
                                cart.add(hardware);
                                cartListModel.addElement(hardware.getDescription()); // Add item to the cart list view
                            }

                            // Reduce the inventory by the added quantity
                            InventoryManager.getInstance().setQuantity(selectedStockType, hardware, availableStock - quantity);

                            displayArea.append("Added " + quantity + " of " + selectedItem + " to cart.\n");
                        } else {
                            displayArea.setText("Not enough stock available. Only " + availableStock + " items in stock.");
                        }
                    } else {
                        displayArea.setText("Item not found in inventory.");
                    }
                } catch (NumberFormatException ex) {
                    displayArea.setText("Invalid quantity. Please enter a valid number.");
                }
            } else {
                displayArea.setText("Please select an item and enter a quantity.");
            }
        });

        // Panel for displaying the cart and removing items
        JPanel cartPanel = new JPanel(new BorderLayout());
        cartList = new JList<>(cartListModel);
        JScrollPane cartScrollPane = new JScrollPane(cartList);
        cartPanel.add(new JLabel("Cart Items:"), BorderLayout.NORTH);
        cartPanel.add(cartScrollPane, BorderLayout.CENTER);

        JButton removeButton = new JButton("Remove from Cart");
        removeButton.addActionListener(e -> {
            String selectedItem = cartList.getSelectedValue();
            if (selectedItem != null) {
                Hardware hardwareToRemove = findHardwareByDescription(selectedItem);
                if (hardwareToRemove != null) {
                    cart.remove(hardwareToRemove);
                    cartListModel.removeElement(selectedItem); // Remove from the cart list view
                    displayArea.append("Removed " + selectedItem + " from the cart.\n");
                }
            } else {
                displayArea.setText("Please select an item to remove.");
            }
        });

        cartPanel.add(removeButton, BorderLayout.SOUTH);

        // Panel for shipping method selection and completing the order
        JPanel orderPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JComboBox<String> shippingComboBox = new JComboBox<>(new String[]{"Standard Shipping", "Express Shipping"});
        JButton completeOrderButton = new JButton("Complete Order");

        orderPanel.add(new JLabel("Select Shipping:"));
        orderPanel.add(shippingComboBox);
        orderPanel.add(completeOrderButton);


        completeOrderButton.addActionListener(e -> {
            if (!cart.isEmpty()) {
                CargoStrategy shippingStrategy = getShippingStrategy((String) shippingComboBox.getSelectedItem());
                Order order = new Order(cart.size(), 50.0); // Example distance for shipment (could be dynamic)
                double shippingCost = order.calculateShipping(shippingStrategy);

                completeOrder(shippingCost);
            } else {
                displayArea.setText("Cart is empty. Please add products to the cart first.");
            }
        });

        panel.add(addProductPanel, BorderLayout.CENTER);
        panel.add(cartPanel, BorderLayout.WEST);
        panel.add(orderPanel, BorderLayout.SOUTH);

        return panel;
    }

    private Hardware findHardwareByDescription(String description) {
        for (Hardware hardware : cart) {
            if (hardware.getDescription().equals(description)) {
                return hardware;
            }
        }
        return null;
    }

    private String[] getItemDescriptions() {
        // Get all hardware items and their descriptions
        List<Hardware> allHardware = InventoryManager.getInstance().getAllHardware();
        return allHardware.stream().map(Hardware::getDescription).toArray(String[]::new);
    }

    private CargoStrategy getShippingStrategy(String shippingType) {
        if ("Express Shipping".equals(shippingType)) {
            return new ExpressShipping();
        } else {
            return new StandardShipping();
        }
    }

    private void completeOrder(double shippingCost) {
        // Logic to complete the order we can change this to a more functional approach
        displayArea.setText("Order completed!\n" +
                "Total items: " + cart.size() + "\n" +
                "Shipping cost: $" + shippingCost + "\n" +
                "Your order will be shipped soon. Thank you for your purchase!");

        cart.clear();
        cartListModel.clear();

        displayArea.append("\nThe cart has been cleared.");
    }

    public List<Hardware> getCart() {
        return cart;
    }
}