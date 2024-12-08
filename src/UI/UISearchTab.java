package UI;

import CompositeAndIterator.HardwareStock;
import Singleton.InventoryManager;
import CompositeAndIterator.Hardware;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The UIMain class is the main frame for the Tech Store Management System application.
 * It initializes the user interface and manages the different tabs for various functionalities.
 */
public class UISearchTab {

    public UISearchTab() {
    }

    protected JPanel createSearchTab() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel for search inputs
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(3, 2, 10, 10));

        JTextField searchField = new JTextField();
        JComboBox<String> factoryComboBox = new JComboBox<>(InventoryManager.getInstance().getFactoryNames());

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
            String searchText = searchField.getText().trim().toLowerCase(); // Case-insensitive search
            boolean filterByBrand = brandFilterCheckBox.isSelected();
            String selectedFactory = (String) factoryComboBox.getSelectedItem();

            List<HardwareSearchResult> results = searchProducts(searchText, filterByBrand ? selectedFactory : null);
            displaySearchResults(results, resultArea);
        });

        return mainPanel;
    }

    private List<HardwareSearchResult> searchProducts(String name, String factory) {
        Map<String, HardwareStock> hardwareStocks = InventoryManager.getInstance().getHardwareStocks(); // Get stock type and hardware stock map

        return hardwareStocks.entrySet().stream()
                .flatMap(entry -> {
                    HardwareStock hardwareStock = entry.getValue();

                    // Get all matching hardware from the stock
                    List<Hardware> matchingHardware = hardwareStock.findHardwaresByDescription(name);

                    // Filter the results by factory and map them to search results
                    return matchingHardware.stream()
                            .filter(hardware -> factory == null || factory.isEmpty() || hardware.getBrand().equalsIgnoreCase(factory))
                            .map(hardware -> new HardwareSearchResult(entry.getKey(), hardware));
                })
                .collect(Collectors.toList());
    }



    // Display search results in the result area
    private void displaySearchResults(List<HardwareSearchResult> results, JTextArea resultArea) {
        if (results.isEmpty()) {
            resultArea.setText("No products found matching the criteria.\n");
        } else {
            StringBuilder resultText = new StringBuilder("Search Results:\n\n");
            for (HardwareSearchResult result : results) {
                int quantity = InventoryManager.getInstance().getHardwareStock(result.getStockType()).getQuantity(result.getHardware());
                resultText.append(result.getHardware().getDescription())
                        .append(" - Stock Type: ").append(result.getStockType())
                        .append(" - Quantity: ").append(quantity).append("\n");
            }
            resultArea.setText(resultText.toString());
        }
    }
}


//The HardwareSearchResult class represents a search result for a hardware item.
class HardwareSearchResult {
    private final String stockType;
    private final Hardware hardware;

    public HardwareSearchResult(String stockType, Hardware hardware) {
        this.stockType = stockType;
        this.hardware = hardware;
    }

    public String getStockType() {
        return stockType;
    }

    public Hardware getHardware() {
        return hardware;
    }
}
