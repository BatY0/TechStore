package UI;

import Strategy.CargoStrategy;
import Strategy.Order;
import Strategy.StandardShipping;
import Strategy.ExpressShipping;
import CompositeAndIterator.Hardware;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class UISendCartTab {

    private JTextArea displayArea;
    private List<Hardware> cart;

    public UISendCartTab(JTextArea displayArea, List<Hardware> cart) {
        this.displayArea = displayArea;
        this.cart = cart;
    }

    protected JPanel createSendCartTab() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JComboBox<String> shippingComboBox = new JComboBox<>(new String[]{"Standard Shipping", "Express Shipping"});
        JButton sendButton = new JButton("Send to Store");

        panel.add(new JLabel("Select Shipping:"), BorderLayout.NORTH);
        panel.add(shippingComboBox, BorderLayout.CENTER);
        panel.add(sendButton, BorderLayout.SOUTH);

        // Action Listener for Send Button
        sendButton.addActionListener(e -> {
            if (!cart.isEmpty()) {
                CargoStrategy shippingStrategy = getShippingStrategy((String) shippingComboBox.getSelectedItem());
                Order order = new Order(cart.size(), 50.0); // Example distance for shipment (could be dynamic)
                double shippingCost = order.calculateShipping(shippingStrategy);

                sendCartToStore(shippingCost);
            } else {
                displayArea.setText("Cart is empty. Please add products to the cart first.");
            }
        });

        return panel;
    }

    private CargoStrategy getShippingStrategy(String shippingType) {
        if ("Express Shipping".equals(shippingType)) {
            return new ExpressShipping();
        } else {
            return new StandardShipping();
        }
    }

    private void sendCartToStore(double shippingCost) {
        // Logic to send items to stores. For now, display a message.
        displayArea.setText("Sending " + cart.size() + " items to store...\n" +
                "Shipping cost: $" + shippingCost);
        // Add actual logic to send the items to stores based on your system's design
    }
}