package UI;

import Singleton.InventoryManager;

import javax.swing.*;
import java.awt.*;

public class UIMain extends JFrame {
    private InventoryManager inventoryManager;
    private JTextArea displayArea;
    private UIAddProductTab addProductTab;
    private UIListProductsTab listProducts;
    private UIStoreNotificationsTab addObserverTab;
    private UIRemoveProductsTab removeProductsTab;

    public UIMain() {
        inventoryManager = InventoryManager.getInstance();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Tech Store Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // System log area
        displayArea = new JTextArea(5, 20);
        displayArea.setEditable(false);
        displayArea.setBorder(BorderFactory.createTitledBorder("System Log"));
        add(new JScrollPane(displayArea), BorderLayout.SOUTH);

        // Create the tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Add Product Tab
        addProductTab = new UIAddProductTab(displayArea);
        tabbedPane.addTab("Add Product", addProductTab.createAddProductTab());

        // View Inventory Tab
        listProducts = new UIListProductsTab(displayArea);
        tabbedPane.addTab("View Inventory", listProducts.createListProductsPanel());

        // Remove Products Tab
        removeProductsTab = new UIRemoveProductsTab(displayArea);
        tabbedPane.addTab("Remove Products", removeProductsTab.createRemoveProductsPanel());

        // Add Observer Tab
        addObserverTab = new UIStoreNotificationsTab(displayArea);
        tabbedPane.addTab("Manage Stores", addObserverTab.createStoreNotificationsTab());

        // Search Tab
        UISearchTab searchTab = new UISearchTab(displayArea);
        tabbedPane.addTab("Search", searchTab.createSearchTab());

        tabbedPane.addChangeListener(e -> {
            if (tabbedPane.getSelectedIndex() == 1) {  // Inventory tab (index 1)
                listProducts.updateProductTable();// Refresh the table when switching to inventory tab
            } else if (tabbedPane.getSelectedIndex() == 2) {  // Store Notifications tab (index 3)
                removeProductsTab.updateProductTable(); // Refresh the table when switching to store notifications tab

            }
        });

        add(tabbedPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UIMain().setVisible(true));
    }
}
