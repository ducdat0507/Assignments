package com.example.client;

import com.example.model.Computer;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ComputerClient extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final String SERVICE_URL = "http://localhost:8080/project_3/ComputerService";

    private JTextField txtComID;
    private JTextField txtComName;
    private JTextField txtPrice;
    private JTextField txtManufacturer;
    private JTable table;
    private DefaultTableModel tableModel;

    public ComputerClient() {
        super("Computer WebService Client");
        initUI();
        loadAllComputers();
    }

    private void initUI() {
        txtComID = new JTextField(10);
        txtComName = new JTextField(20);
        txtPrice = new JTextField(10);
        txtManufacturer = new JTextField(20);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 8, 8));
        formPanel.add(new JLabel("comID:"));
        formPanel.add(txtComID);
        formPanel.add(new JLabel("comName:"));
        formPanel.add(txtComName);
        formPanel.add(new JLabel("price:"));
        formPanel.add(txtPrice);
        formPanel.add(new JLabel("manufacturer:"));
        formPanel.add(txtManufacturer);

        JButton btnAdd = new JButton("Add");
        JButton btnFind = new JButton("Find");
        JButton btnShowAll = new JButton("Show All");

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addComputer();
            }
        });
        btnFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findComputer();
            }
        });
        btnShowAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAllComputers();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnFind);
        buttonPanel.add(btnShowAll);

        tableModel = new DefaultTableModel(new Object[] {"comID", "comName", "price", "manufacturer"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 250));

        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    private void validateFields() {
        if (txtComID.getText().trim().isEmpty() || txtComName.getText().trim().isEmpty()
                || txtPrice.getText().trim().isEmpty() || txtManufacturer.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("All fields are required.");
        }
        try {
            Double.parseDouble(txtPrice.getText().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Price must be numeric.");
        }
    }

    private void addComputer() {
        try {
            validateFields();
            String requestBody = "action=add"
                    + "&comID=" + URLEncoder.encode(txtComID.getText().trim(), "UTF-8")
                    + "&comName=" + URLEncoder.encode(txtComName.getText().trim(), "UTF-8")
                    + "&price=" + URLEncoder.encode(txtPrice.getText().trim(), "UTF-8")
                    + "&manufacturer=" + URLEncoder.encode(txtManufacturer.getText().trim(), "UTF-8");
            Document doc = postXml(SERVICE_URL, requestBody);
            String success = getTagValue(doc, "success");
            String message = getTagValue(doc, "message");
            if ("true".equalsIgnoreCase(success)) {
                JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
                loadAllComputers();
            } else {
                JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Validation", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error adding computer: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void findComputer() {
        String comID = JOptionPane.showInputDialog(this, "Enter comID to find:", "Find Computer", JOptionPane.PLAIN_MESSAGE);
        if (comID == null || comID.trim().isEmpty()) {
            return;
        }
        try {
            URL url = new URL(SERVICE_URL + "?action=find&comID=" + URLEncoder.encode(comID.trim(), "UTF-8"));
            Document doc = loadXml(url);
            String success = getTagValue(doc, "success");
            if (success != null) {
                String message = getTagValue(doc, "message");
                JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            List<Computer> computers = parseComputers(doc);
            if (computers.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Computer not found.", "Not found", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Computer computer = computers.get(0);
            txtComID.setText(computer.getComID());
            txtComName.setText(computer.getComName());
            txtPrice.setText(String.valueOf(computer.getPrice()));
            txtManufacturer.setText(computer.getManufacturer());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error finding computer: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadAllComputers() {
        try {
            URL url = new URL(SERVICE_URL + "?action=getAll");
            Document doc = loadXml(url);
            List<Computer> computers = parseComputers(doc);
            reloadTable(computers);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading computers: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void reloadTable(List<Computer> computers) {
        tableModel.setRowCount(0);
        for (Computer computer : computers) {
            tableModel.addRow(new Object[] {
                    computer.getComID(),
                    computer.getComName(),
                    computer.getPrice(),
                    computer.getManufacturer()
            });
        }
    }

    private Document loadXml(URL url) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        connection.connect();
        return parseDocument(connection.getInputStream());
    }

    private Document postXml(String urlString, String body) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        connection.setRequestProperty("Accept", "application/xml");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
        writer.write(body);
        writer.flush();
        writer.close();
        return parseDocument(connection.getInputStream());
    }

    private Document parseDocument(InputStream inputStream) throws Exception {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(false);
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(inputStream);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    private List<Computer> parseComputers(Document document) {
        List<Computer> computers = new ArrayList<Computer>();
        NodeList nodes = document.getElementsByTagName("computer");
        for (int i = 0; i < nodes.getLength(); i++) {
            Element element = (Element) nodes.item(i);
            Computer computer = new Computer();
            computer.setComID(getTagValue(element, "comID"));
            computer.setComName(getTagValue(element, "comName"));
            computer.setPrice(Double.parseDouble(getTagValue(element, "price")));
            computer.setManufacturer(getTagValue(element, "manufacturer"));
            computers.add(computer);
        }
        return computers;
    }

    private String getTagValue(Document document, String tagName) {
        NodeList nodes = document.getElementsByTagName(tagName);
        if (nodes.getLength() == 0) {
            return null;
        }
        return nodes.item(0).getTextContent();
    }

    private String getTagValue(Element element, String tagName) {
        NodeList nodes = element.getElementsByTagName(tagName);
        if (nodes.getLength() == 0) {
            return "";
        }
        return nodes.item(0).getTextContent();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ComputerClient client = new ComputerClient();
                client.setVisible(true);
            }
        });
    }
}
