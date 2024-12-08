package UI;

import ObserverPattern.Observer;
import ObserverPattern.TechStores;
import Singleton.InventoryManager;

import javax.swing.*;
import java.awt.*;

/**
 * The UIStoreNotificationsTab class provides a user interface tab for managing store notifications.
 * It allows users to add, edit, and remove stores (observers) from the inventory manager.
 */
public class UIStoreNotificationsTab {
    private JTextArea displayArea;

    public UIStoreNotificationsTab(JTextArea displayArea) {
        this.displayArea = displayArea;
    }

    public JPanel createStoreNotificationsTab() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // List to display observers
        DefaultListModel<String> observerListModel = new DefaultListModel<>();
        JList<String> observerList = new JList<>(observerListModel);
        JScrollPane observerListScrollPane = new JScrollPane(observerList);

        // Load existing observers into the list
        loadObservers(observerListModel);

        // Button to remove an observer
        JButton removeObserverButton = new JButton("Remove Store");
        removeObserverButton.addActionListener(e -> {
            String selectedObserverName = observerList.getSelectedValue();
            if (selectedObserverName != null) {
                Observer selectedObserver = findObserverByName(selectedObserverName);
                if (selectedObserver != null) {
                    // Remove the observer from the inventory manager
                    InventoryManager.getInstance().removeObserver(selectedObserver);
                    // Remove the observer from the list model
                    observerListModel.removeElement(selectedObserverName);
                    displayArea.append("Removed Store: " + selectedObserverName + "\n");
                } else {
                    JOptionPane.showMessageDialog(panel, "Error: Could not find the store.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(panel, "Please select a store to remove.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Button to edit the name of an observer
        JButton editObserverButton = new JButton("Edit Store Name");
        editObserverButton.addActionListener(e -> {
            String selectedObserverName = observerList.getSelectedValue();
            if (selectedObserverName != null) {
                String newName = JOptionPane.showInputDialog(panel, "Enter new name for the store:", selectedObserverName);
                if (newName != null && !newName.trim().isEmpty()) {
                    // Update the observer name
                    InventoryManager.getInstance().editObserverName(selectedObserverName, newName);
                    // Update the list model
                    observerListModel.setElementAt(newName, observerList.getSelectedIndex());
                    displayArea.append("Updated Store name to: " + newName + "\n");
                }
            } else {
                JOptionPane.showMessageDialog(panel, "Please select a store to edit.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Button to add new observer
        JButton addObserverButton = new JButton("Add Store");
        addObserverButton.addActionListener(e -> {
            String observerName = JOptionPane.showInputDialog(panel, "Enter name of new store:");
            if (observerName != null && !observerName.trim().isEmpty()) {
                TechStores newObserver = new TechStores(observerName);
                InventoryManager.getInstance().registerObserver(newObserver);
                // Add observer to list model
                observerListModel.addElement(observerName);
                displayArea.append("New Store added: " + observerName + "\n");
            }
        });

        // Layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addObserverButton);
        buttonPanel.add(editObserverButton);
        buttonPanel.add(removeObserverButton);

        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(observerListScrollPane, BorderLayout.CENTER);

        return panel;
    }

    // Helper method to load existing observers into the list
    private void loadObservers(DefaultListModel<String> observerListModel) {
        for (Observer observer : InventoryManager.getInstance().getObservers()) {
            if (observer instanceof TechStores) {
                observerListModel.addElement(((TechStores) observer).getName());
            }
        }
    }

    // Helper method to find observer by name
    private Observer findObserverByName(String name) {
        for (Observer observer : InventoryManager.getInstance().getObservers()) {
            if (observer instanceof TechStores) {
                TechStores store = (TechStores) observer;
                if (store.getName().equals(name)) {
                    return store;
                }
            }
        }
        return null;
    }
}
