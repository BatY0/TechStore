import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class TechStoreUI {
    private JFrame frame;
    private DefaultListModel<String> cartModel;
    private Map<String, Integer> productStock; // Ürün ve stok bilgisi
    private Map<String, Double> productPrices; // Ürün ve fiyat bilgisi
    private Map<String, Integer> cartItems;   // Sepetteki ürünler ve miktarları
    private JLabel totalPriceLabel;
    private double totalPrice = 0.0;
    private String selectedShippingMethod = "Standard"; // Varsayılan gönderim yöntemi
    private double shippingCost = 5.0; // Varsayılan gönderim ücreti

    public TechStoreUI(Map<String, Integer> initialStock, Map<String, Double> prices) {
        this.productStock = initialStock;
        this.productPrices = prices;
        this.cartItems = new HashMap<>();

        // Ana pencere
        frame = new JFrame("TechStore");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Menü çubuğu
        JMenuBar menuBar = new JMenuBar();

        // File Menüsü
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);

        frame.setJMenuBar(menuBar);

        // Ürün listesi paneli
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new BorderLayout());
        JLabel productLabel = new JLabel("Products:");
        JPanel productListPanel = new JPanel(new GridLayout(0, 1));
        JScrollPane scrollPane = new JScrollPane(productListPanel);

        for (Map.Entry<String, Integer> entry : productStock.entrySet()) {
            String productName = entry.getKey();
            int stock = entry.getValue();
            double price = productPrices.get(productName);
            JPanel productRow = createProductRow(productName, stock, price);
            productListPanel.add(productRow);
        }

        productPanel.add(productLabel, BorderLayout.NORTH);
        productPanel.add(scrollPane, BorderLayout.CENTER);

        // Sepet paneli
        JPanel cartPanel = new JPanel();
        cartPanel.setLayout(new BorderLayout());
        JLabel cartLabel = new JLabel("Cart:");
        cartModel = new DefaultListModel<>();
        JList<String> cartList = new JList<>(cartModel);
        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(new CheckoutListener());

        cartPanel.add(cartLabel, BorderLayout.NORTH);
        cartPanel.add(new JScrollPane(cartList), BorderLayout.CENTER);
        cartPanel.add(checkoutButton, BorderLayout.SOUTH);

        // Toplam fiyat alanı
        totalPriceLabel = new JLabel("Total: $0.00 (Shipping: $" + shippingCost + ")");
        cartPanel.add(totalPriceLabel, BorderLayout.NORTH);

        // Shipping paneli
        JPanel shippingPanel = new JPanel();
        shippingPanel.setLayout(new GridLayout(2, 1));
        JLabel shippingLabel = new JLabel("Shipping Method:");
        JRadioButton standardButton = new JRadioButton("Standard ($5.00)");
        JRadioButton expressButton = new JRadioButton("Express ($15.00)");

        ButtonGroup shippingGroup = new ButtonGroup();
        shippingGroup.add(standardButton);
        shippingGroup.add(expressButton);
        standardButton.setSelected(true);

        standardButton.addActionListener(e -> {
            selectedShippingMethod = "Standard";
            shippingCost = 5.0;
            updateTotalPrice();
        });

        expressButton.addActionListener(e -> {
            selectedShippingMethod = "Express";
            shippingCost = 15.0;
            updateTotalPrice();
        });

        shippingPanel.add(shippingLabel);
        shippingPanel.add(standardButton);
        shippingPanel.add(expressButton);

        // Ana panel
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, productPanel, cartPanel);
        splitPane.setDividerLocation(500);

        // Sol alt panel (ürün paneline shipping panelini ekliyoruz)
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(productPanel, BorderLayout.CENTER);
        leftPanel.add(shippingPanel, BorderLayout.SOUTH);

        frame.add(leftPanel, BorderLayout.CENTER);
        frame.add(cartPanel, BorderLayout.EAST);
    }

    private JPanel createProductRow(String productName, int stock, double price) {
        JPanel productRow = new JPanel();
        productRow.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel nameLabel = new JLabel(productName + " ($" + price + ") (Stock: " + stock + ")");
        JButton minusButton = new JButton("-");
        JLabel quantityLabel = new JLabel("0");
        JButton plusButton = new JButton("+");

        minusButton.addActionListener(e -> {
            int currentQuantity = Integer.parseInt(quantityLabel.getText());
            if (currentQuantity > 0) {
                quantityLabel.setText(String.valueOf(currentQuantity - 1));
                productStock.put(productName, productStock.get(productName) + 1);
                updateStockDisplay(nameLabel, productName, price);
            }
        });

        plusButton.addActionListener(e -> {
            int currentQuantity = Integer.parseInt(quantityLabel.getText());
            if (productStock.get(productName) > 0) {
                quantityLabel.setText(String.valueOf(currentQuantity + 1));
                productStock.put(productName, productStock.get(productName) - 1);
                updateStockDisplay(nameLabel, productName, price);
            }
        });

        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.addActionListener(e -> {
            int quantity = Integer.parseInt(quantityLabel.getText());
            if (quantity > 0) {
                cartItems.put(productName, cartItems.getOrDefault(productName, 0) + quantity);
                cartModel.addElement(productName + " x" + quantity);
                totalPrice += quantity * price;
                updateTotalPrice();
                quantityLabel.setText("0");
            }
        });

        productRow.add(nameLabel);
        productRow.add(minusButton);
        productRow.add(quantityLabel);
        productRow.add(plusButton);
        productRow.add(addToCartButton);

        return productRow;
    }

    private void updateStockDisplay(JLabel nameLabel, String productName, double price) {
        nameLabel.setText(productName + " ($" + price + ") (Stock: " + productStock.get(productName) + ")");
    }

    private void updateTotalPrice() {
        totalPriceLabel.setText("Total: $" + String.format("%.2f", totalPrice + shippingCost) + " (Shipping: $" + shippingCost + ")");
    }

    public void display() {
        frame.setVisible(true);
    }

    private class CheckoutListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            StringBuilder receipt = new StringBuilder("Checkout complete! Items:\n");
            for (Map.Entry<String, Integer> entry : cartItems.entrySet()) {
                receipt.append(entry.getKey()).append(" x").append(entry.getValue()).append("\n");
            }
            receipt.append("\nTotal: $").append(String.format("%.2f", totalPrice + shippingCost));
            receipt.append("\nShipping Method: ").append(selectedShippingMethod);
            JOptionPane.showMessageDialog(frame, receipt.toString());
            cartModel.clear();
            cartItems.clear();
            totalPrice = 0.0;
            updateTotalPrice();
        }
    }

    public static void main(String[] args) {

        Map<String, Integer> stock = new HashMap<>();
        Map<String, Double> prices = new HashMap<>();

        stock.put("ASUS GPU RTX 3080", 10);
        prices.put("ASUS GPU RTX 3080", 699.99);

        stock.put("ASUS Case Rog Strix", 5);
        prices.put("ASUS Case Rog Strix", 199.99);

        SwingUtilities.invokeLater(() -> new TechStoreUI(stock, prices).display());
    }
}