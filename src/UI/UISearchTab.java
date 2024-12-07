package UI;

import Singleton.InventoryManager;
import CompositeAndIterator.Hardware;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class UISearchTab {

    private InventoryManager inventoryManager = InventoryManager.getInstance();
    private JTextArea displayArea;

    UISearchTab(JTextArea displayArea) {
        this.displayArea = displayArea;
    }

    protected JPanel createSearchTab() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel for search inputs
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(3, 2, 10, 10));

        JTextField searchField = new JTextField();
        JComboBox<String> factoryComboBox = new JComboBox<>(inventoryManager.getFactoryNames());

        JCheckBox brandFilterCheckBox = new JCheckBox("Filter by Brand");

        JButton searchButton = new JButton("Search");

        searchPanel.add(new JLabel("Product Name:"));
        searchPanel.add(searchField);
        searchPanel.add(brandFilterCheckBox);
        searchPanel.add(new JLabel("Factory:"));
        searchPanel.add(factoryComboBox);
        searchPanel.add(searchButton);

        mainPanel.add(searchPanel, BorderLayout.NORTH);

        // Result area
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Action Listener for Search Button
        searchButton.addActionListener(e -> {
            String searchText = searchField.getText().toLowerCase(); // Case-insensitive
            boolean filterByBrand = brandFilterCheckBox.isSelected();
            String selectedFactory = (String) factoryComboBox.getSelectedItem();

            List<Hardware> results = searchProducts(searchText, filterByBrand ? selectedFactory : null);
            displaySearchResults(results, resultArea);
        });

        return mainPanel;
    }

    private List<Hardware> searchProducts(String name, String brand) {
        return inventoryManager.getAllHardware().stream()
                .filter(product -> (name == null || name.isBlank() || product.getDescription().toLowerCase().contains(name))
                        && (brand == null || product.getBrand().equalsIgnoreCase(brand))) // Compare brand as string
                .collect(Collectors.toList());
    }

    private void displaySearchResults(List<Hardware> results, JTextArea resultArea) {
        if (results.isEmpty()) {
            resultArea.setText("No products found matching the criteria.\n");
        } else {
            StringBuilder resultText = new StringBuilder("Search Results:\n\n");
            for (Hardware hardware : results) {
                resultText.append(hardware.getDescription()).append("\n");
            }
            resultArea.setText(resultText.toString());
        }
    }
}
