package com.mycompany.supermarketbillingsystem;

import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.util.HashMap;

public class BillingGUI2 extends javax.swing.JFrame {

    double total = 0;
    double discount = 0;
    DefaultTableModel model;

    HashMap<String, String> productMap = new HashMap<>();

    public BillingGUI2() {
        initComponents();

        // Sample barcode data
        productMap.put("1001", "Rice,50");
        productMap.put("1002", "Milk,30");
        productMap.put("1003", "Oil,120");
    }

    private void initComponents() {

        jLabel0 = new javax.swing.JLabel("Scan Barcode");
        txtBarcode = new javax.swing.JTextField(10);

        jLabel1 = new javax.swing.JLabel("Product Name");
        jLabel2 = new javax.swing.JLabel("Price");
        jLabel3 = new javax.swing.JLabel("Quantity");

        jTextField1 = new javax.swing.JTextField(10);
        jTextField2 = new javax.swing.JTextField(10);
        jTextField3 = new javax.swing.JTextField(10);

        jButton1 = new javax.swing.JButton("Add Item");
        jButton2 = new javax.swing.JButton("Apply Discount");
        jButton3 = new javax.swing.JButton("Generate Bill");

        // Table
        model = new DefaultTableModel();
        model.addColumn("Product");
        model.addColumn("Price");
        model.addColumn("Qty");
        model.addColumn("Total");

        jTable1 = new javax.swing.JTable(model);
        jScrollPane1 = new javax.swing.JScrollPane(jTable1);

        // Bill area
        jTextArea1 = new javax.swing.JTextArea(6, 25);
        jScrollPane2 = new javax.swing.JScrollPane(jTextArea1);

        setLayout(new java.awt.FlowLayout());

        // Add components
        add(jLabel0); add(txtBarcode);

        add(jLabel1); add(jTextField1);
        add(jLabel2); add(jTextField2);
        add(jLabel3); add(jTextField3);

        add(jButton1);
        add(jButton2);
        add(jButton3);

        add(jScrollPane1);
        add(jScrollPane2);

        // 🔥 Barcode scan logic
        txtBarcode.addActionListener(e -> {

            String code = txtBarcode.getText();

            if (productMap.containsKey(code)) {

                String data = productMap.get(code);
                String[] parts = data.split(",");

                jTextField1.setText(parts[0]);
                jTextField2.setText(parts[1]);

                txtBarcode.setText("");

            } else {
                JOptionPane.showMessageDialog(this, "Product Not Found");
            }
        });

        // Add item
        jButton1.addActionListener(evt -> {

            String name = jTextField1.getText();
            double price = Double.parseDouble(jTextField2.getText());
            int qty = Integer.parseInt(jTextField3.getText());

            double itemTotal = price * qty;
            total += itemTotal;

            model.addRow(new Object[]{name, price, qty, itemTotal});

            jTextField1.setText("");
            jTextField2.setText("");
            jTextField3.setText("");
        });

        // Apply Discount (User input %)
        jButton2.addActionListener(evt -> {

            String input = JOptionPane.showInputDialog("Enter Discount %:");

            if (input != null && !input.isEmpty()) {
                double percent = Double.parseDouble(input);
                discount = total * (percent / 100);

                jTextArea1.setText(percent + "% Discount Applied: " + discount);
            }
        });

        // Generate bill
        jButton3.addActionListener(evt -> {

            double finalAmount = total - discount;

            StringBuilder bill = new StringBuilder();
            bill.append("===== BILL SUMMARY =====\n");
            bill.append("Total Amount: ").append(total).append("\n");
            bill.append("Discount: ").append(discount).append("\n");
            bill.append("------------------------\n");
            bill.append("Final Amount: ").append(finalAmount);

            jTextArea1.setText(bill.toString());
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        pack();
    }

    public static void main(String[] args) {
        new BillingGUI2().setVisible(true);
    }

    private javax.swing.JLabel jLabel0, jLabel1, jLabel2, jLabel3;
    private javax.swing.JTextField txtBarcode, jTextField1, jTextField2, jTextField3;
    private javax.swing.JButton jButton1, jButton2, jButton3;
    private javax.swing.JTable jTable1;
    private javax.swing.JScrollPane jScrollPane1, jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
}