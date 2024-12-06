package UI;

import CompositeAndIterator.HardwareStock;
import Singleton.InventoryManager;

import javax.swing.*;
import java.awt.*;

public class TechStoreUI extends JFrame {
    private InventoryManager inventoryManager;
    private JTextArea displayArea;
    private UIAddProductTab addProductTab;
    private UIListProducts listProducts;

    public TechStoreUI() {
        inventoryManager = InventoryManager.getInstance();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Tech Store Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // System log area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setBorder(BorderFactory.createTitledBorder("System Log"));
        add(new JScrollPane(displayArea), BorderLayout.SOUTH);

        // Create the tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Add Product Tab
        addProductTab = new UIAddProductTab(displayArea);
        tabbedPane.addTab("Add Product", addProductTab.createAddProductTab());

        // View Inventory Tab
        listProducts = new UIListProducts(displayArea);
        tabbedPane.addTab("View Inventory", listProducts.createListProductsPanel());

        tabbedPane.addChangeListener(e -> {
            if (tabbedPane.getSelectedIndex() == 1) {  // Inventory tab (index 1)
                listProducts.updateProductTable();  // Refresh the table when switching to inventory tab
            }
        });

        add(tabbedPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TechStoreUI().setVisible(true));
    }
}
