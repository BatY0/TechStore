import AbstractFactory.Components.*;
import CompositeAndIterator.HardwareStock;
import Singleton.InventoryManager;
import CompositeAndIterator.Hardware;
import AbstractFactory.Factories.Company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class TechStoreUI extends JFrame {
    private InventoryManager inventoryManager;
    private JTextArea displayArea;

    public TechStoreUI() {
        inventoryManager = InventoryManager.getInstance();
        initializeUI();
    }



    private void initializeUI() {
        setTitle("Tech Store Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Add Product", createAddProductTab());
        tabbedPane.addTab("View Inventory", createListProductsPanel()); // New tab for viewing inventory

        add(tabbedPane, BorderLayout.CENTER);

        // System log area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setBorder(BorderFactory.createTitledBorder("System Log"));
        add(new JScrollPane(displayArea), BorderLayout.SOUTH);
    }

    private JPanel createAddProductTab() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel for component selection buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 6, 5, 5));
        JButton gpuButton = new JButton("Add GPU");
        JButton cpuButton = new JButton("Add CPU");
        JButton memoryButton = new JButton("Add Memory");
        JButton caseButton = new JButton("Add Case");
        JButton coolerButton = new JButton("Add CPU Cooler");
        JButton storageButton = new JButton("Add Storage");
        JButton motherboardButton = new JButton("Add Motherboard");
        JButton powerSupplyButton = new JButton("Add Power Supply");


        buttonPanel.add(gpuButton);
        buttonPanel.add(cpuButton);
        buttonPanel.add(memoryButton);
        buttonPanel.add(caseButton);
        buttonPanel.add(coolerButton);
        buttonPanel.add(storageButton);
        buttonPanel.add(motherboardButton);
        buttonPanel.add(powerSupplyButton);

        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        // Panel for displaying forms dynamically
        JPanel formPanel = new JPanel(new CardLayout());
        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Create forms for each component type
        formPanel.add(createAddComponentPanel("GPU"), "GPU");
        formPanel.add(createAddComponentPanel("CPU"), "CPU");
        formPanel.add(createAddComponentPanel("Memory"), "Memory");
        formPanel.add(createAddComponentPanel("Case"), "Case");
        formPanel.add(createAddComponentPanel("CpuCooler"), "CpuCooler");
        formPanel.add(createAddComponentPanel("Storage"), "Storage");
        formPanel.add(createAddComponentPanel("Motherboard"), "Motherboard");
        formPanel.add(createAddComponentPanel("PowerSupply"), "PowerSupply");

        // Action listeners for buttons
        CardLayout cardLayout = (CardLayout) formPanel.getLayout();
        gpuButton.addActionListener(e -> cardLayout.show(formPanel, "GPU"));
        cpuButton.addActionListener(e -> cardLayout.show(formPanel, "CPU"));
        memoryButton.addActionListener(e -> cardLayout.show(formPanel, "Memory"));
        caseButton.addActionListener(e -> cardLayout.show(formPanel, "Case"));
        coolerButton.addActionListener(e -> cardLayout.show(formPanel, "CpuCooler"));
        storageButton.addActionListener(e -> cardLayout.show(formPanel, "Storage"));
        motherboardButton.addActionListener(e -> cardLayout.show(formPanel, "Motherboard"));
        powerSupplyButton.addActionListener(e -> cardLayout.show(formPanel, "PowerSupply"));

        return mainPanel;
    }

    private JPanel createListProductsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Dropdown for stock type
        JComboBox<String> stockTypeComboBox = new JComboBox<>();
        stockTypeComboBox.addItem("All");
        InventoryManager.getInstance().getHardwareStocks().keySet().forEach(stockTypeComboBox::addItem);

        // Table for displaying hardware
        String[] columnNames = { "Type", "Description", "Quantity", "Unit Price", "Total Price" };
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable productTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(productTable);

        panel.add(stockTypeComboBox, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Update table when stock type changes
        stockTypeComboBox.addActionListener(e -> {
            String selectedType = (String) stockTypeComboBox.getSelectedItem();
            updateProductTable(tableModel, "All".equals(selectedType) ? null : selectedType);
        });

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

    private void populateTableWithStockData(DefaultTableModel tableModel, HardwareStock stock) {
        Iterator<Hardware> iterator = stock.createIterator();
        while (iterator.hasNext()) {
            Hardware hardware = iterator.next();
            int quantity = stock.getQuantity(hardware); // Get the quantity of the hardware
            tableModel.addRow(new Object[] {
                    stock.getDescription(),       // Stock type
                    hardware.getDescription(), // Hardware description
                    quantity,                  // Quantity in stock
                    hardware.getPrice(),       // Price per unit
                    quantity * hardware.getPrice() // Total price
            });
        }
    }


    private JPanel createAddComponentPanel(String componentType) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JComboBox<String> factoryComboBox = new JComboBox<>(inventoryManager.getFactoryNames());
        JTextField productNameField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField quantityField = new JTextField();

        addLabeledField(panel, "Factory:", factoryComboBox);
        addLabeledField(panel, "Product Name:", productNameField);

        switch (componentType) {
            case "GPU" -> {
                JTextField memorySizeField = new JTextField();
                JTextField clockSpeedField = new JTextField();
                addLabeledField(panel, "Memory Size (GB):", memorySizeField);
                addLabeledField(panel, "Clock Speed (GHz):", clockSpeedField);
                addLabeledField(panel, "Price:", priceField);
                addLabeledField(panel, "Quantity:", quantityField);
                addSubmitButton(panel, "Add GPU", e -> {
                    try {
                        addGpu(factoryComboBox, productNameField, memorySizeField, clockSpeedField, priceField, quantityField);
                    } catch (Exception ex) {
                        displayArea.setText("Error: " + ex.getMessage());
                    }
                });
            }
            case "CPU" -> {
                JComboBox<SocketType> socketField = new JComboBox<>(SocketType.values());
                JTextField coresField = new JTextField();
                JTextField threadsField = new JTextField();
                JTextField clockSpeedField = new JTextField();
                addLabeledField(panel, "Socket:", socketField);
                addLabeledField(panel, "Cores:", coresField);
                addLabeledField(panel, "Threads:", threadsField);
                addLabeledField(panel, "Clock Speed (GHz):", clockSpeedField);
                addLabeledField(panel, "Price:", priceField);
                addLabeledField(panel, "Quantity:", quantityField);
                addSubmitButton(panel, "Add CPU", e -> {
                    try {
                        addCpu(factoryComboBox, productNameField, socketField, coresField, threadsField, clockSpeedField, priceField, quantityField);
                    } catch (Exception ex) {
                        displayArea.setText("Error: " + ex.getMessage());
                    }
                });
            }
            case "Memory" -> {
                JTextField capacityField = new JTextField();
                JTextField speedField = new JTextField();
                JComboBox<MemoryType> memoryTypeComboBox = new JComboBox<>(MemoryType.values());
                addLabeledField(panel, "Capacity (GB):", capacityField);
                addLabeledField(panel, "Speed (MHz):", speedField);
                addLabeledField(panel, "Memory Type:", memoryTypeComboBox);
                addLabeledField(panel, "Price:", priceField);
                addLabeledField(panel, "Quantity:", quantityField);
                addSubmitButton(panel, "Add Memory", e -> {
                    try {
                        addMemory(factoryComboBox, productNameField, capacityField, speedField, memoryTypeComboBox, priceField, quantityField);
                    } catch (Exception ex) {
                        displayArea.setText("Error: " + ex.getMessage());
                    }
                });
            }
            case "Case" -> {
                JComboBox<FormFactor> formFactorComboBox = new JComboBox<>(FormFactor.values());
                JTextField fanCountField = new JTextField();
                addLabeledField(panel, "Form Factor:", formFactorComboBox);
                addLabeledField(panel, "Fan Count:", fanCountField);
                addLabeledField(panel, "Price:", priceField);
                addLabeledField(panel, "Quantity:", quantityField);
                addSubmitButton(panel, "Add Case", e -> {
                    try {
                        addCase(factoryComboBox, productNameField, formFactorComboBox, fanCountField, priceField, quantityField);
                    } catch (Exception ex) {
                        displayArea.setText("Error: " + ex.getMessage());
                    }
                });
            }
            case "CpuCooler" -> {
                JComboBox<CoolingType> coolingTypeField = new JComboBox<>(CoolingType.values());
                JTextField fanSpeedField = new JTextField();
                JTextField noiseLevelField = new JTextField();
                JTextField sizeField = new JTextField();
                addLabeledField(panel, "Cooling Type:", coolingTypeField);
                addLabeledField(panel, "Fan Speed (RPM):", fanSpeedField);
                addLabeledField(panel, "Noise Level (dB):", noiseLevelField);
                addLabeledField(panel, "Size:", sizeField);
                addLabeledField(panel, "Price:", priceField);
                addLabeledField(panel, "Quantity:", quantityField);
                addSubmitButton(panel, "Add CPU Cooler", e -> {
                    try {
                        addCpuCooler(factoryComboBox, productNameField, coolingTypeField, fanSpeedField, noiseLevelField, sizeField, priceField, quantityField);
                    } catch (Exception ex) {
                        displayArea.setText("Error: " + ex.getMessage());
                    }
                });
            }
            case "Motherboard" -> {
                JComboBox<SocketType> socketTypeComboBox = new JComboBox<>(SocketType.values());
                JComboBox<MemoryType> memoryTypeComboBox = new JComboBox<>(MemoryType.values());
                JComboBox<FormFactor> formFactorComboBox = new JComboBox<>(FormFactor.values());
                JTextField memorySlotsField = new JTextField();
                addLabeledField(panel, "Socket Type:", socketTypeComboBox);
                addLabeledField(panel, "Memory Type:", memoryTypeComboBox);
                addLabeledField(panel, "Memory Slots:", memorySlotsField);
                addLabeledField(panel, "Form Factor:", formFactorComboBox);
                addLabeledField(panel, "Price:", priceField);
                addLabeledField(panel, "Quantity:", quantityField);
                addSubmitButton(panel, "Add Motherboard", e -> {
                    try {
                        addMotherboard(factoryComboBox, productNameField, socketTypeComboBox, memoryTypeComboBox, memorySlotsField, formFactorComboBox, priceField, quantityField);
                    } catch (Exception ex) {
                        displayArea.setText("Error: " + ex.getMessage());
                    }
                });
            }
            case "PowerSupply" -> {
                JTextField wattageField = new JTextField();
                JCheckBox modularCheckBox = new JCheckBox("Modular");
                addLabeledField(panel, "Wattage (W):", wattageField);
                addLabeledField(panel, "Modular:", modularCheckBox);
                addLabeledField(panel, "Price:", priceField);
                addLabeledField(panel, "Quantity:", quantityField);
                addSubmitButton(panel, "Add Power Supply", e -> {
                    try {
                        addPowerSupply(factoryComboBox, productNameField, wattageField, modularCheckBox, priceField, quantityField);
                    } catch (Exception ex) {
                        displayArea.setText("Error: " + ex.getMessage());
                    }
                });
            }
            case "Storage" -> {
                JTextField capacityField = new JTextField();

                JComboBox<StorageType> storageTypeComboBox = new JComboBox<>(StorageType.values());
                addLabeledField(panel, "Capacity (GB):", capacityField);
                addLabeledField(panel, "Storage Type:", storageTypeComboBox);
                addLabeledField(panel, "Price:", priceField);
                addLabeledField(panel, "Quantity:", quantityField);
                addSubmitButton(panel, "Add Storage", e -> {
                    try {
                        addStorage(factoryComboBox, productNameField, capacityField, storageTypeComboBox, priceField, quantityField);
                    } catch (Exception ex) {
                        displayArea.setText("Error: " + ex.getMessage());
                    }
                });
            }
        }
        return panel;
    }


    private void addLabeledField(JPanel panel, String labelText, JComponent field) {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BorderLayout());
        fieldPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, field.getPreferredSize().height));

        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(100, label.getPreferredSize().height));
        fieldPanel.add(label, BorderLayout.WEST);
        fieldPanel.add(field, BorderLayout.CENTER);

        panel.add(fieldPanel);
        panel.add(Box.createVerticalStrut(5)); // Add spacing between rows
    }

    private void addSubmitButton(JPanel panel, String buttonText, ActionListener actionListener) {
        JButton button = new JButton(buttonText);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(actionListener);
        panel.add(Box.createVerticalStrut(10)); // Add spacing before the button
        panel.add(button);
    }

    private void addGpu(JComboBox<String> factoryComboBox, JTextField productNameField, JTextField memorySizeField,
                        JTextField clockSpeedField, JTextField priceField, JTextField quantityField) {
        Company factory = getSelectedFactory(factoryComboBox);
        String productName = productNameField.getText();
        int memorySize = Integer.parseInt(memorySizeField.getText());
        double clockSpeed = Double.parseDouble(clockSpeedField.getText());
        double price = Double.parseDouble(priceField.getText());
        int quantity = Integer.parseInt(quantityField.getText());

        Hardware gpu = factory.createGpu(productName, memorySize, clockSpeed, price);
        inventoryManager.addHardware("GPU", gpu, quantity);
        displayArea.setText("GPU added successfully.");
    }

    private void addCpu(JComboBox<String> factoryComboBox, JTextField productNameField, JComboBox<SocketType> socketField,
                        JTextField coresField, JTextField threadsField, JTextField clockSpeedField,
                        JTextField priceField, JTextField quantityField) {
        Company factory = getSelectedFactory(factoryComboBox);
        String productName = productNameField.getText();
        String socket = (String) socketField.getSelectedItem();
        int cores = Integer.parseInt(coresField.getText());
        int threads = Integer.parseInt(threadsField.getText());
        double clockSpeed = Double.parseDouble(clockSpeedField.getText());
        double price = Double.parseDouble(priceField.getText());
        int quantity = Integer.parseInt(quantityField.getText());

        Hardware cpu = factory.createCpu(productName, socket, cores, threads, clockSpeed, price);
        inventoryManager.addHardware("CPU", cpu, quantity);
        displayArea.setText("CPU added successfully.");
    }

    private void addMemory(JComboBox<String> factoryComboBox, JTextField productNameField, JTextField capacityField,
                           JTextField speedField, JComboBox<MemoryType> memoryTypeComboBox,
                           JTextField priceField, JTextField quantityField) {
        Company factory = getSelectedFactory(factoryComboBox);
        String productName = productNameField.getText();
        int capacity = Integer.parseInt(capacityField.getText());
        int speed = Integer.parseInt(speedField.getText());
        MemoryType type = (MemoryType) memoryTypeComboBox.getSelectedItem();
        double price = Double.parseDouble(priceField.getText());
        int quantity = Integer.parseInt(quantityField.getText());

        Hardware memory = factory.createMemory(productName, capacity, speed, type, price);
        inventoryManager.addHardware("Memory", memory, quantity);
        displayArea.setText("Memory added successfully.");
    }

    private void addCase(JComboBox<String> factoryComboBox, JTextField productNameField,
                         JComboBox<FormFactor> formFactorComboBox, JTextField fanCountField,
                         JTextField priceField, JTextField quantityField) {
        Company factory = getSelectedFactory(factoryComboBox);
        String productName = productNameField.getText();
        FormFactor formFactor = (FormFactor) formFactorComboBox.getSelectedItem();
        int fanCount = Integer.parseInt(fanCountField.getText());
        double price = Double.parseDouble(priceField.getText());
        int quantity = Integer.parseInt(quantityField.getText());

        Hardware caseHardware = factory.createCase(productName, formFactor, fanCount, price);
        inventoryManager.addHardware("Case", caseHardware, quantity);
        displayArea.setText("Case added successfully.");
    }

    private void addCpuCooler(JComboBox<String> factoryComboBox, JTextField productNameField,
                              JComboBox<CoolingType> coolingTypeField, JTextField fanSpeedField,
                              JTextField noiseLevelField, JTextField sizeField,
                              JTextField priceField, JTextField quantityField) {
        Company factory = getSelectedFactory(factoryComboBox);
        String productName = productNameField.getText();
        CoolingType coolingType = (CoolingType) coolingTypeField.getSelectedItem();
        int fanSpeed = Integer.parseInt(fanSpeedField.getText());
        double noiseLevel = Double.parseDouble(noiseLevelField.getText());
        String size = sizeField.getText();
        double price = Double.parseDouble(priceField.getText());
        int quantity = Integer.parseInt(quantityField.getText());

        Hardware cpuCooler = factory.createCpuCooler(productName, price, coolingType, fanSpeed, noiseLevel, size);
        inventoryManager.addHardware("CpuCooler", cpuCooler, quantity);
        displayArea.setText("CPU Cooler added successfully.");
    }

    private void addMotherboard(JComboBox<String> factoryComboBox, JTextField productNameField,
                                JComboBox<SocketType> socketTypeComboBox, JComboBox<MemoryType> memoryTypeComboBox,
                                JTextField memorySlotsField, JComboBox<FormFactor> formFactorComboBox,
                                JTextField priceField, JTextField quantityField) {
        Company factory = getSelectedFactory(factoryComboBox);
        String productName = productNameField.getText();
        SocketType socket = (SocketType) socketTypeComboBox.getSelectedItem();
        MemoryType memoryType = (MemoryType) memoryTypeComboBox.getSelectedItem();
        int memorySlots = Integer.parseInt(memorySlotsField.getText());
        FormFactor formFactor = (FormFactor) formFactorComboBox.getSelectedItem();
        double price = Double.parseDouble(priceField.getText());
        int quantity = Integer.parseInt(quantityField.getText());

        Hardware motherboard = factory.createMotherboard(productName, price, socket, memoryType, memorySlots, formFactor);
        inventoryManager.addHardware("Motherboard", motherboard, quantity);
        displayArea.setText("Motherboard added successfully.");
    }

    private void addPowerSupply(JComboBox<String> factoryComboBox, JTextField productNameField,
                                JTextField wattageField, JCheckBox modularCheckBox,
                                JTextField priceField, JTextField quantityField) {
        Company factory = getSelectedFactory(factoryComboBox);
        String productName = productNameField.getText();
        int wattage = Integer.parseInt(wattageField.getText());
        boolean modular = modularCheckBox.isSelected();
        double price = Double.parseDouble(priceField.getText());
        int quantity = Integer.parseInt(quantityField.getText());

        Hardware powerSupply = factory.createPowerSupply(productName, price, wattage, modular);
        inventoryManager.addHardware("PowerSupply", powerSupply, quantity);
        displayArea.setText("Power Supply added successfully.");
    }

    private void addStorage(JComboBox<String> factoryComboBox, JTextField productNameField,
                            JTextField capacityField,
                            JComboBox<StorageType> storageTypeComboBox,
                            JTextField priceField, JTextField quantityField) {
        Company factory = getSelectedFactory(factoryComboBox);
        String productName = productNameField.getText();
        int capacity = Integer.parseInt(capacityField.getText());
        StorageType type = (StorageType) storageTypeComboBox.getSelectedItem();
        double price = Double.parseDouble(priceField.getText());
        int quantity = Integer.parseInt(quantityField.getText());

        Hardware storage = factory.createStorage(productName, capacity, type, price);
        inventoryManager.addHardware("Storage", storage, quantity);
        displayArea.setText("Storage added successfully.");
    }


    private Company getSelectedFactory(JComboBox<String> factoryComboBox) {
        String factoryName = (String) factoryComboBox.getSelectedItem();
        Company factory = inventoryManager.getFactory(factoryName);
        if (factory == null) {
            throw new IllegalArgumentException("Factory not found.");
        }
        return factory;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TechStoreUI().setVisible(true));
    }
}
