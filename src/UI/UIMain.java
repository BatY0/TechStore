package UI;

import javax.swing.*;
import java.awt.*;

/**
 * The UIMain class is the main frame for the Tech Store Management System application.
 * It initializes the user interface and manages the different tabs for various functionalities.
 */
public class UIMain extends JFrame {
    private JTextArea displayArea;


    public UIMain() {
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
        UIAddProductTab addProductTab = new UIAddProductTab(displayArea);
        tabbedPane.addTab("Add Product", addProductTab.createAddProductTab());

        // View Inventory Tab
        UIListProductsTab listProducts = new UIListProductsTab();
        tabbedPane.addTab("View Inventory", listProducts.createListProductsPanel());

        // Remove Products Tab
        UIEditProductsTab removeProductsTab = new UIEditProductsTab(displayArea);
        tabbedPane.addTab("Edit Products", removeProductsTab.createRemoveProductsPanel());

        // Add Observer Tab
        UIStoreNotificationsTab addObserverTab = new UIStoreNotificationsTab(displayArea);
        tabbedPane.addTab("Manage Stores", addObserverTab.createStoreNotificationsTab());

        // Search Tab
        UISearchTab searchTab = new UISearchTab();
        tabbedPane.addTab("Search", searchTab.createSearchTab());

        // Cart Tab
        UICartManagementTab cartManagementTab = new UICartManagementTab(displayArea);
        tabbedPane.addTab("Cart", cartManagementTab.createCartManagementTab());



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

    }
}
