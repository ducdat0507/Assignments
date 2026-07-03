package com.example.client;

import com.example.model.Computer;
import com.example.service.ComputerService;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
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
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 * JAX-WS Client for Computer Service
 * Communicates with the Computer Web Service via JAX-WS
 */
public class ComputerClient extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final String SERVICE_URL = "http://localhost:8080/project_3/ComputerService?wsdl";
    private static final String SERVICE_NAMESPACE = "http://com.example.service/";
    private static final String SERVICE_NAME = "ComputerService";

    private JTextField txtComID;
    private JTextField txtComName;
    private JTextField txtPrice;
    private JTextField txtManufacturer;
    private JTable table;
    private DefaultTableModel tableModel;
    private ComputerService proxy;

    public ComputerClient() {
        super("Computer Service - JAX-WS Client");
        try {
            initializeWebService();
            initUI();
            loadAllComputers();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Failed to initialize web service: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    /**
     * Initialize the JAX-WS web service proxy
     */
    private void initializeWebService() throws MalformedURLException {
        try {
            URL wsdlLocation = new URL(SERVICE_URL);
            QName serviceName = new QName(SERVICE_NAMESPACE, SERVICE_NAME);
            Service service = Service.create(wsdlLocation, serviceName);
            proxy = service.getPort(ComputerService.class);
        } catch (Exception e) {
            throw new RuntimeException("Unable to connect to web service at " + SERVICE_URL, e);
        }
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

        tableModel = new DefaultTableModel(new Object[]{"comID", "comName", "price", "manufacturer"}, 0);
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
            Computer computer = new Computer(
                    txtComID.getText().trim(),
                    txtComName.getText().trim(),
                    Double.parseDouble(txtPrice.getText().trim()),
                    txtManufacturer.getText().trim()
            );
            boolean success = proxy.addComputer(computer);
            if (success) {
                JOptionPane.showMessageDialog(this, 
                    "Computer added successfully!", 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                loadAllComputers();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Failed to add computer.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, 
                ex.getMessage(), 
                "Validation Error", 
                JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Error adding computer: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void findComputer() {
        String comID = JOptionPane.showInputDialog(this, 
            "Enter comID to find:", 
            "Find Computer", 
            JOptionPane.PLAIN_MESSAGE);
        
        if (comID == null || comID.trim().isEmpty()) {
            return;
        }
        
        try {
            Computer computer = proxy.findComputerById(comID.trim());
            if (computer == null) {
                JOptionPane.showMessageDialog(this, 
                    "Computer not found.", 
                    "Not Found", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            txtComID.setText(computer.getComID());
            txtComName.setText(computer.getComName());
            txtPrice.setText(String.valueOf(computer.getPrice()));
            txtManufacturer.setText(computer.getManufacturer());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Error finding computer: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadAllComputers() {
        try {
            List<Computer> computers = proxy.getAllComputers();
            reloadTable(computers);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Error loading computers: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void reloadTable(List<Computer> computers) {
        tableModel.setRowCount(0);
        if (computers != null) {
            for (Computer computer : computers) {
                tableModel.addRow(new Object[]{
                        computer.getComID(),
                        computer.getComName(),
                        computer.getPrice(),
                        computer.getManufacturer()
                });
            }
        }
    }

    private void clearForm() {
        txtComID.setText("");
        txtComName.setText("");
        txtPrice.setText("");
        txtManufacturer.setText("");
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


