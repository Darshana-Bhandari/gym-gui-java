package Coursework;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.io.*;

/**
 * GymGUI - Main GUI class for Darshana Gym Management System
 * This class creates a comprehensive Swing-based interface for managing gym members,
 * including regular and premium members with various operations like adding, updating,
 * file operations, and member management.
 */
public class GymGUI extends JFrame {
    // ===============================
    // INSTANCE VARIABLES
    // ===============================
    
    /** ArrayList to store all gym members (both regular and premium) */
    private ArrayList<GymMember> members;
    
    // Personal Information Input Fields
    private JTextField idField, nameField, locationField, phoneField, emailField;
    private JTextField referralField, paidAmountField, removalReasonField, trainerField;
    
    // Membership Details Input Fields
    private JTextField planPriceField, premiumChargeField, discountField;
    
    // Date Selection Components (Birth Date and Membership Start Date)
    private JComboBox<String> dobYearComboBox, dobMonthComboBox, dobDayComboBox;
    private JComboBox<String> msYearComboBox, msMonthComboBox, msDayComboBox;
    
    // Gender Selection Components
    private JRadioButton maleButton, femaleButton;
    
    // Plan Selection Component
    private JComboBox<String> planComboBox;
    
    // ===============================
    // COLOR SCHEME CONSTANTS
    // ===============================
    private Color primaryColor = new Color(0, 38, 77);        // Dark blue for headers
    private Color secondaryColor = new Color(70, 130, 180);   // Steel blue for hover effects
    private Color accentColor = new Color(239, 134, 134);     // Light red accent (unused in current code)
    private Color textColor = new Color(50, 50, 50);          // Dark gray for text
    private Color bgColor = new Color(245, 245, 245);         // Light gray background

    /**
     * Constructor - Initializes the GUI and sets up all components
     */
    public GymGUI() {
        members = new ArrayList<>();                           // Initialize member list
        setTitle("Darshana GYM Management System");           // Set window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       // Exit on close
        setupUI();                                            // Create and arrange UI components
        pack();                                               // Size window to fit components
        setLocationRelativeTo(null);                          // Center window on screen
    }
    
    /**
     * Sets up all date combo boxes for DOB and membership start date
     * Creates dropdown menus for day (1-31), month (January-December), and year (1950-2024)
     */
    private void setupDateComboBoxes() {
        // Create year options (75 years from 1950 to 2024)
        String[] years = new String[75];
        for (int i = 0; i < 75; i++) {
            years[i] = String.valueOf(1950 + i);
        }
        
        // Month names array
        String[] months = {"January", "February", "March", "April", "May", "June",
                          "July", "August", "September", "October", "November", "December"};
        
        // Create day options (1-31)
        String[] days = new String[31];
        for (int i = 0; i < 31; i++) {
            days[i] = String.valueOf(i + 1);
        }
        
        // Initialize Date of Birth combo boxes
        dobYearComboBox = new JComboBox<>(years);
        dobMonthComboBox = new JComboBox<>(months);
        dobDayComboBox = new JComboBox<>(days);
        
        // Initialize Membership Start date combo boxes
        msYearComboBox = new JComboBox<>(years);
        msMonthComboBox = new JComboBox<>(months);
        msDayComboBox = new JComboBox<>(days);
        
        // Apply consistent styling to all combo boxes
        styleComboBox(dobYearComboBox);
        styleComboBox(dobMonthComboBox);
        styleComboBox(dobDayComboBox);
        styleComboBox(msYearComboBox);
        styleComboBox(msMonthComboBox);
        styleComboBox(msDayComboBox);
    }
    
    /**
     * Applies consistent styling to combo box components
     * @param comboBox The combo box to style
     */
    private void styleComboBox(JComboBox<String> comboBox) {
        comboBox.setBackground(Color.WHITE);
        comboBox.setForeground(textColor);
        comboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        ((JComponent) comboBox.getRenderer()).setOpaque(true);
    }
    
    /**
     * Applies consistent styling to button components with hover effects
     * @param button The button to style
     */
    private void styleButton(JButton button) {
        button.setBackground(primaryColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);                        // Remove focus border
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorderPainted(false);                       // Remove default border
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));     // Hand cursor on hover
        
        // Add hover effect listeners
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(secondaryColor);         // Change color on hover
            }
            
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(primaryColor);           // Restore original color
            }
        });
    }
    
    /**
     * Applies consistent styling to text field components
     * @param textField The text field to style
     */
    private void styleTextField(JTextField textField) {
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setForeground(textColor);
        // Create compound border with line border and padding
        textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199)),
            BorderFactory.createEmptyBorder(5, 7, 5, 7)));
    }
    
    /**
     * Applies consistent styling to label components
     * @param label The label to style
     */
    private void styleLabel(JLabel label) {
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(textColor);
    }
    
    /**
     * Applies consistent styling to radio button components
     * @param radioButton The radio button to style
     */
    private void styleRadioButton(JRadioButton radioButton) {
        radioButton.setFont(new Font("Arial", Font.PLAIN, 14));
        radioButton.setForeground(textColor);
        radioButton.setBackground(bgColor);
    }
    
    /**
     * Applies consistent styling to panel components
     * @param panel The panel to style
     */
    private void stylePanel(JPanel panel) {
        panel.setBackground(bgColor);
        // Create compound border with line border and padding
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
    }
    
    /**
     * Main method to set up the entire user interface
     * Creates and arranges all GUI components including:
     * - Header panel with title
     * - Personal information input section
     * - Membership details input section
     * - Action buttons panel
     */
    private void setupUI() {
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(bgColor);
        
        // ===============================
        // MAIN CONTAINER PANEL
        // ===============================
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(bgColor);
        
        // ===============================
        // HEADER PANEL
        // ===============================
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(primaryColor);
        JLabel titleLabel = new JLabel("Darshana Gym Management System", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        
        // ===============================
        // PERSONAL INFORMATION PANEL
        // ===============================
        JPanel personalInfoPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        personalInfoPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(primaryColor, 2),
            "Personal Information",
            0, 0,
            new Font("Arial", Font.BOLD, 16),
            primaryColor));
        stylePanel(personalInfoPanel);
        
        // Member ID Field
        JLabel idLabel = new JLabel("Member ID:");
        styleLabel(idLabel);
        personalInfoPanel.add(idLabel);
        idField = new JTextField(10);
        styleTextField(idField);
        personalInfoPanel.add(idField);
        
        // Name Field
        JLabel nameLabel = new JLabel("Name:");
        styleLabel(nameLabel);
        personalInfoPanel.add(nameLabel);
        nameField = new JTextField(20);
        styleTextField(nameField);
        personalInfoPanel.add(nameField);
        
        // Location Field
        JLabel locationLabel = new JLabel("Location:");
        styleLabel(locationLabel);
        personalInfoPanel.add(locationLabel);
        locationField = new JTextField(20);
        styleTextField(locationField);
        personalInfoPanel.add(locationField);
        
        // Phone Field
        JLabel phoneLabel = new JLabel("Phone:");
        styleLabel(phoneLabel);
        personalInfoPanel.add(phoneLabel);
        phoneField = new JTextField(15);
        styleTextField(phoneField);
        personalInfoPanel.add(phoneField);
        
        // Email Field
        JLabel emailLabel = new JLabel("Email:");
        styleLabel(emailLabel);
        personalInfoPanel.add(emailLabel);
        emailField = new JTextField(20);
        styleTextField(emailField);
        personalInfoPanel.add(emailField);
        
        // Gender Selection (Radio Buttons)
        JLabel genderLabel = new JLabel("Gender:");
        styleLabel(genderLabel);
        personalInfoPanel.add(genderLabel);
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.setBackground(bgColor);
        
        ButtonGroup genderGroup = new ButtonGroup();  // Ensures only one gender can be selected
        maleButton = new JRadioButton("Male");
        femaleButton = new JRadioButton("Female");
        styleRadioButton(maleButton);
        styleRadioButton(femaleButton);
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);
        personalInfoPanel.add(genderPanel);
        
        // Initialize date combo boxes
        setupDateComboBoxes();
        
        // Date of Birth Selection
        JLabel dobLabel = new JLabel("DOB:");
        styleLabel(dobLabel);
        personalInfoPanel.add(dobLabel);
        JPanel dobPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        dobPanel.setBackground(bgColor);
        dobPanel.add(dobDayComboBox);
        dobPanel.add(dobMonthComboBox);
        dobPanel.add(dobYearComboBox);
        personalInfoPanel.add(dobPanel);
        
        // Membership Start Date Selection
        JLabel msLabel = new JLabel("Membership Start Date:");
        styleLabel(msLabel);
        personalInfoPanel.add(msLabel);
        JPanel msPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        msPanel.setBackground(bgColor);
        msPanel.add(msDayComboBox);
        msPanel.add(msMonthComboBox);
        msPanel.add(msYearComboBox);
        personalInfoPanel.add(msPanel);
        
        // ===============================
        // MEMBERSHIP DETAILS PANEL
        // ===============================
        JPanel membershipPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        membershipPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(primaryColor, 2),
            "Membership Details",
            0, 0,
            new Font("Arial", Font.BOLD, 16),
            primaryColor));
        stylePanel(membershipPanel);
        
        // Referral Source Field
        JLabel referralLabel = new JLabel("Referral Source:");
        styleLabel(referralLabel);
        membershipPanel.add(referralLabel);
        referralField = new JTextField(20);
        styleTextField(referralField);
        membershipPanel.add(referralField);
        
        // Plan Selection Combo Box
        JLabel planLabel = new JLabel("Plan:");
        styleLabel(planLabel);
        membershipPanel.add(planLabel);
        planComboBox = new JComboBox<>(new String[]{"Basic", "Standard", "Deluxe"});
        styleComboBox(planComboBox);
        membershipPanel.add(planComboBox);
        
        // Plan combo box event listener - automatically updates price based on selected plan
        planComboBox.addActionListener(e -> {
            String selectedPlan = (String) planComboBox.getSelectedItem();
            switch (selectedPlan.toLowerCase()) {
                case "basic":
                    planPriceField.setText("6500");
                    break;
                case "standard":
                    planPriceField.setText("12500");
                    break;
                case "deluxe":
                    planPriceField.setText("18500");
                    break;
            }
        });
        
        // Regular Plan Price Field (Read-only, auto-populated)
        JLabel planPriceLabel = new JLabel("Regular Plan Price:");
        styleLabel(planPriceLabel);
        membershipPanel.add(planPriceLabel);
        planPriceField = new JTextField("6500");
        planPriceField.setEditable(false);  // Cannot be edited by user
        styleTextField(planPriceField);
        planPriceField.setBackground(new Color(234, 236, 238));  // Gray background for read-only
        membershipPanel.add(planPriceField);
        
        // Premium Charge Field (Read-only, fixed value)
        JLabel premiumChargeLabel = new JLabel("Premium Charge:");
        styleLabel(premiumChargeLabel);
        membershipPanel.add(premiumChargeLabel);
        premiumChargeField = new JTextField("50000");
        premiumChargeField.setEditable(false);  // Fixed premium charge
        styleTextField(premiumChargeField);
        premiumChargeField.setBackground(new Color(234, 236, 238));
        membershipPanel.add(premiumChargeField);
        
        // Paid Amount Field
        JLabel paidAmountLabel = new JLabel("Paid Amount:");
        styleLabel(paidAmountLabel);
        membershipPanel.add(paidAmountLabel);
        paidAmountField = new JTextField(10);
        styleTextField(paidAmountField);
        membershipPanel.add(paidAmountField);
        
        // Discount Amount Field (Read-only, calculated automatically)
        JLabel discountLabel = new JLabel("Discount Amount:");
        styleLabel(discountLabel);
        membershipPanel.add(discountLabel);
        discountField = new JTextField("0");
        discountField.setEditable(false);  // Calculated automatically
        styleTextField(discountField);
        discountField.setBackground(new Color(234, 236, 238));
        membershipPanel.add(discountField);
        
        // Trainer's Name Field (for premium members)
        JLabel trainerLabel = new JLabel("Trainer's Name:");
        styleLabel(trainerLabel);
        membershipPanel.add(trainerLabel);
        trainerField = new JTextField(20);
        styleTextField(trainerField);
        membershipPanel.add(trainerField);
        
        // Removal Reason Field (for member removal)
        JLabel removalReasonLabel = new JLabel("Removal Reason:");
        styleLabel(removalReasonLabel);
        membershipPanel.add(removalReasonLabel);
        removalReasonField = new JTextField(20);
        styleTextField(removalReasonField);
        membershipPanel.add(removalReasonField);
        
        // ===============================
        // ACTION BUTTONS PANEL
        // ===============================
        JPanel buttonPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        buttonPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(primaryColor, 2),
            "Actions",
            0, 0,
            new Font("Arial", Font.BOLD, 16),
            primaryColor));
        buttonPanel.setBackground(bgColor);
        
        // Create all action buttons
        JButton addRegularButton = new JButton("Add Regular Member");
        JButton addPremiumButton = new JButton("Add Premium Member");
        JButton activateButton = new JButton("Activate Membership");
        JButton deactivateButton = new JButton("Deactivate Membership");
        JButton markAttendanceButton = new JButton("Mark Attendance");
        JButton upgradePlanButton = new JButton("Upgrade Plan");
        JButton calculateDiscountButton = new JButton("Calculate Discount");
        JButton revertRegularButton = new JButton("Revert Regular Member");
        JButton revertPremiumButton = new JButton("Revert Premium Member");
        JButton payDueButton = new JButton("Pay Due Amount");
        JButton displayButton = new JButton("Display Members");
        JButton clearButton = new JButton("Clear");
        
        // File Operation Buttons
        JButton saveToFileButton = new JButton("Save to File");
        JButton readFromFileButton = new JButton("Read from File");
        
        // Apply styling to all buttons
        styleButton(addRegularButton);
        styleButton(addPremiumButton);
        styleButton(activateButton);
        styleButton(deactivateButton);
        styleButton(markAttendanceButton);
        styleButton(upgradePlanButton);
        styleButton(calculateDiscountButton);
        styleButton(revertRegularButton);
        styleButton(revertPremiumButton);
        styleButton(payDueButton);
        styleButton(displayButton);
        styleButton(clearButton);
        styleButton(saveToFileButton);
        styleButton(readFromFileButton);
        
        // Special green color for certain buttons
        Color greenColor = new Color(46, 125, 50);
        addRegularButton.setBackground(greenColor);
        addPremiumButton.setBackground(greenColor);
        displayButton.setBackground(greenColor);
        saveToFileButton.setBackground(greenColor);
        readFromFileButton.setBackground(greenColor);
        
        // Add all buttons to the button panel
        buttonPanel.add(addRegularButton);
        buttonPanel.add(addPremiumButton);
        buttonPanel.add(activateButton);
        buttonPanel.add(deactivateButton);
        buttonPanel.add(markAttendanceButton);
        buttonPanel.add(upgradePlanButton);
        buttonPanel.add(calculateDiscountButton);
        buttonPanel.add(revertRegularButton);
        buttonPanel.add(revertPremiumButton);
        buttonPanel.add(payDueButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(saveToFileButton);
        buttonPanel.add(readFromFileButton);
        
        // ===============================
        // ASSEMBLE MAIN LAYOUT
        // ===============================
        mainPanel.add(headerPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));  // Spacing
        mainPanel.add(personalInfoPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));  // Spacing
        mainPanel.add(membershipPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));  // Spacing
        mainPanel.add(buttonPanel);
        
        // Add main panel to scrollable container
        add(new JScrollPane(mainPanel), BorderLayout.CENTER);
        
        // Set preferred window size
        setPreferredSize(new Dimension(1100, 800));
        
        // ===============================
        // BUTTON EVENT HANDLERS
        // ===============================
        addRegularButton.addActionListener(e -> addRegularMember());
        addPremiumButton.addActionListener(e -> addPremiumMember());
        activateButton.addActionListener(e -> activateMembership());
        deactivateButton.addActionListener(e -> deactivateMembership());
        markAttendanceButton.addActionListener(e -> markAttendance());
        upgradePlanButton.addActionListener(e -> upgradePlan());
        calculateDiscountButton.addActionListener(e -> calculateDiscount());
        revertRegularButton.addActionListener(e -> revertRegularMember());
        revertPremiumButton.addActionListener(e -> revertPremiumMember());
        payDueButton.addActionListener(e -> payDueAmount());
        displayButton.addActionListener(e -> displayMembers());
        clearButton.addActionListener(e -> clearFields());
        
        // File operation event handlers
        saveToFileButton.addActionListener(e -> saveToFile());
        readFromFileButton.addActionListener(e -> readFromFile());
    }
    
    // ===============================
    // FILE OPERATION METHODS
    // ===============================
    
    /**
     * Saves all member data to a text file called "MemberDetails.txt"
     * Creates a formatted table with headers and member information
     */
    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("MemberDetails.txt"))) {
            // Write table header as specified in requirements
            writer.printf("%-5s %-15s %-15s %-15s %-25s %-20s %-10s %-10s %-10s %-15s %-10s %-15s %-15s %-15s\n", 
                "ID", "Name", "Location", "Phone", "Email", "Membership Start Date", 
                "Plan", "Price", "Attendance", "Loyalty Points", "Active Status", 
                "Full Payment", "Discount Amount", "Net Amount Paid");
            
            // Write data for each member
            for (GymMember member : members) {
                String plan = "";
                String price = "";
                String fullPayment = "";
                String discountAmount = "";
                String netAmountPaid = "";
                
                // Handle different member types (Regular vs Premium)
                if (member instanceof RegularMember) {
                    RegularMember regMember = (RegularMember) member;
                    plan = regMember.getPlan();
                    price = String.valueOf(regMember.getPrice());
                    fullPayment = "N/A";        // Not applicable for regular members
                    discountAmount = "N/A";     // Not applicable for regular members
                    netAmountPaid = "N/A";      // Not applicable for regular members
                } else if (member instanceof PremiumMember) {
                    PremiumMember premMember = (PremiumMember) member;
                    plan = "Premium";
                    price = String.valueOf(premMember.getPremiumCharge());
                    fullPayment = premMember.getIsFullPayment() ? "Yes" : "No";
                    discountAmount = String.valueOf(premMember.getDiscountAmount());
                    netAmountPaid = String.valueOf(premMember.getPaidAmount());
                }
                
                // Write formatted member data
                writer.printf("%-5d %-15s %-15s %-15s %-25s %-20s %-10s %-10s %-10d %-15.1f %-10s %-15s %-15s %-15s\n",
                    member.getId(),
                    member.getName(),
                    member.getLocation(),
                    member.getPhone(),
                    member.getEmail(),
                    member.getMembershipStartDate(),
                    plan,
                    price,
                    member.getAttendance(),
                    member.getLoyaltyPoints(),
                    member.getActiveStatus() ? "Active" : "Inactive",
                    fullPayment,
                    discountAmount,
                    netAmountPaid);
            }
            
            // Show success message
            JOptionPane.showMessageDialog(this, "Data saved to MemberDetails.txt successfully!", 
                "Save Successful", JOptionPane.INFORMATION_MESSAGE);
                
        } catch (IOException e) {
            // Show error message if file operation fails
            JOptionPane.showMessageDialog(this, "Error saving file: " + e.getMessage(), 
                "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Reads member data from "MemberDetails.txt" file and displays it in a dialog
     * Shows the content in a scrollable text area with monospaced font for proper alignment
     */
    private void readFromFile() {
        File file = new File("MemberDetails.txt");

        // Check if file exists
        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "File 'MemberDetails.txt' does not exist!",
                    "File Not Found", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder fileContent = new StringBuilder();
            String line;

            // Read all lines from file
            while ((line = reader.readLine()) != null) {
                fileContent.append(line).append("\n");
            }

            // Create text area with monospaced font for proper table alignment
            JTextArea textArea = new JTextArea(fileContent.toString());
            textArea.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 13));
            textArea.setEditable(false);    // Read-only
            textArea.setCaretPosition(0);   // Scroll to top

            // Put text area in scrollable container
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(900, 400));
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            // Show in dialog box
            JOptionPane.showMessageDialog(this, scrollPane, "📄 Member Details (Read from File)",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            // Show error message if file reading fails
            JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage(),
                    "Read Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ===============================
    // MEMBER MANAGEMENT METHODS
    // ===============================
    
    /**
     * Adds a new regular member to the system
     * Validates input fields and checks for duplicate IDs
     */
    private void addRegularMember() {
        try {
            // Parse and validate member ID
            int id = Integer.parseInt(idField.getText());
            if (isIdDuplicate(id)) {
                JOptionPane.showMessageDialog(this, "Member ID already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Get input values
            String name = nameField.getText();
            String location = locationField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            String gender = maleButton.isSelected() ? "Male" : "Female";
            
            // Format date strings from combo box selections
            String dob = dobDayComboBox.getSelectedItem() + "-" + 
                        dobMonthComboBox.getSelectedItem() + "-" + 
                        dobYearComboBox.getSelectedItem();
            String startDate = msDayComboBox.getSelectedItem() + "-" + 
                             msMonthComboBox.getSelectedItem() + "-" + 
                             msYearComboBox.getSelectedItem();
            String referral = referralField.getText();
            
            // Validate required fields
            if (name.isEmpty() || location.isEmpty() || phone.isEmpty() || 
                email.isEmpty() || (!maleButton.isSelected() && !femaleButton.isSelected())) {
                JOptionPane.showMessageDialog(this, "Please fill all required fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Create new regular member and add to list
            RegularMember member = new RegularMember(id, name, location, phone, email, 
                                                   gender, dob, startDate, referral);
            members.add(member);
            
            // Show success message and clear form
            JOptionPane.showMessageDialog(this, "Regular Member added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
        
    /**
     * Adds a new premium member to the system
     * Similar to addRegularMember but creates PremiumMember object
     */
    private void addPremiumMember() {
        try {
            // Parse and validate member ID
            int id = Integer.parseInt(idField.getText());
            if (isIdDuplicate(id)) {
                JOptionPane.showMessageDialog(this, "Member ID already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Get input values
            String name = nameField.getText();
            String location = locationField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            String gender = maleButton.isSelected() ? "Male" : "Female";
            
            // Format date strings
            String dob = dobDayComboBox.getSelectedItem() + "-" + 
                        dobMonthComboBox.getSelectedItem() + "-" + 
                        dobYearComboBox.getSelectedItem();
            String startDate = msDayComboBox.getSelectedItem() + "-" + 
                             msMonthComboBox.getSelectedItem() + "-" + 
                             msYearComboBox.getSelectedItem();
            String trainer = trainerField.getText();
            
            // Validate required fields
            if (name.isEmpty() || location.isEmpty() || phone.isEmpty() || 
                email.isEmpty() || (!maleButton.isSelected() && !femaleButton.isSelected())) {
                JOptionPane.showMessageDialog(this, "Please fill all required fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Create new premium member and add to list
            PremiumMember member = new PremiumMember(id, name, location, phone, email, 
                                                   gender, dob, startDate, trainer);
            members.add(member);
            
            // Show success message and clear form
            JOptionPane.showMessageDialog(this, "Premium Member added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
        
    /**
     * Checks if a member ID already exists in the system
     * @param id The ID to check
     * @return true if ID exists, false otherwise
     */
    private boolean isIdDuplicate(int id) {
        for (GymMember member : members) {
            if (member.getId() == id) {
                return true;
            }
        }
        return false;
    }
        
    /**
     * Activates membership for a member with the specified ID
     * Searches for member by ID and calls their activateMembership method
     */
    private void activateMembership() {
       try {
           int id = Integer.parseInt(idField.getText());
           
           // Search for member with matching ID
           for (GymMember member : members) {
               if (member.getId() == id) {
                   member.activateMembership();
                   JOptionPane.showMessageDialog(this, "Membership activated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                   return;
               }
           }
           
           // Member not found
           JOptionPane.showMessageDialog(this, "Member not found!", "Error", JOptionPane.ERROR_MESSAGE);
       } catch (NumberFormatException e) {
           JOptionPane.showMessageDialog(this, "Please enter a valid ID!", "Error", JOptionPane.ERROR_MESSAGE);
       }
    }
        
    /**
     * Deactivates membership for a member with the specified ID
     * Searches for member by ID and calls their deactivateMembership method
     */
    private void deactivateMembership() {
       try {
           int id = Integer.parseInt(idField.getText());
           
           // Search for member with matching ID
           for (GymMember member : members) {
               if (member.getId() == id) {
                   member.deactivateMembership();
                   JOptionPane.showMessageDialog(this, "Membership deactivated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                   return;
               }
           }
           
           // Member not found
           JOptionPane.showMessageDialog(this, "Member not found!", "Error", JOptionPane.ERROR_MESSAGE);
       } catch (NumberFormatException e) {
           JOptionPane.showMessageDialog(this, "Please enter a valid ID!", "Error", JOptionPane.ERROR_MESSAGE);
       }
    }
        
    /**
     * Marks attendance for a member with the specified ID
     * Only allows attendance marking if membership is active
     */
    private void markAttendance() {
        try {
            int id = Integer.parseInt(idField.getText());

            // Search for member with matching ID
            for (GymMember member : members) {
                if (member.getId() == id) {
                    // Check if membership is active before marking attendance
                    if (member.getActiveStatus()) {
                        member.markAttendance();
                        JOptionPane.showMessageDialog(this, "Attendance marked successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Membership is not active!", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                    return;
                }
            }

            // Member not found
            JOptionPane.showMessageDialog(this, "Member not found!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Upgrades the plan for a regular member
     * Only works for regular members with active membership
     */
    private void upgradePlan() {
        try {
            int id = Integer.parseInt(idField.getText());
            String selectedPlan = (String) planComboBox.getSelectedItem();

            // Search for member with matching ID
for (GymMember member : members) {
    if (member.getId() == id) {
        // Check if member is a regular member
        if (member instanceof RegularMember) {
            RegularMember regularMember = (RegularMember) member;
            // Check if membership is active
            if (member.getActiveStatus()) {
                String result = regularMember.upgradePlan(selectedPlan);
                JOptionPane.showMessageDialog(this, result, "Plan Upgrade", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Membership is not active!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Not a Regular Member!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return;
    }
}
            // Member not found
            JOptionPane.showMessageDialog(this, "Member not found!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Calculates discount for a premium member based on their loyalty points
     * Only works for premium members, updates the discount field with calculated amount
     */
    private void calculateDiscount() {
        try {
            int id = Integer.parseInt(idField.getText());

            // Search for member with matching ID
            for (GymMember member : members) {
                if (member.getId() == id) {
                    // Check if member is a premium member
                    if (member instanceof PremiumMember) {
                        PremiumMember premiumMember = (PremiumMember) member;
                        premiumMember.calculateDiscount();
                        // Update discount field with calculated amount
                        discountField.setText(String.valueOf(premiumMember.getDiscountAmount()));
                        JOptionPane.showMessageDialog(this, "Discount calculated!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Not a Premium Member!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    return;
                }
            }

            // Member not found
            JOptionPane.showMessageDialog(this, "Member not found!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Reverts (removes) a regular member from the system
     * Requires a removal reason to be specified
     */
    private void revertRegularMember() {
        try {
            int id = Integer.parseInt(idField.getText());
            String reason = removalReasonField.getText();

            // Validate removal reason is provided
            if (reason.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a removal reason!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Search for member with matching ID
            for (GymMember member : members) {
                if (member.getId() == id) {
                    // Check if member is a regular member
                    if (member instanceof RegularMember) {
                        RegularMember regularMember = (RegularMember) member;
                        regularMember.revertRegularMember(reason);
                        JOptionPane.showMessageDialog(this, "Regular Member reverted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Not a Regular Member!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    return;
                }
            }

            // Member not found
            JOptionPane.showMessageDialog(this, "Member not found!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Reverts (removes) a premium member from the system
     * No removal reason required for premium members
     */
    private void revertPremiumMember() {
        try {
            int id = Integer.parseInt(idField.getText());

            // Search for member with matching ID
            for (GymMember member : members) {
                if (member.getId() == id) {
                    // Check if member is a premium member
                    if (member instanceof PremiumMember) {
                        PremiumMember premiumMember = (PremiumMember) member;
                        premiumMember.revertPremiumMember();
                        JOptionPane.showMessageDialog(this, "Premium Member reverted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Not a Premium Member!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    return;
                }
            }

            // Member not found
            JOptionPane.showMessageDialog(this, "Member not found!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Processes payment for a premium member's due amount
     * Updates the member's payment status and calculates remaining balance
     */
    private void payDueAmount() {
        try {
            int id = Integer.parseInt(idField.getText());
            double amount = Double.parseDouble(paidAmountField.getText());

            // Search for member with matching ID
            for (GymMember member : members) {
                if (member.getId() == id) {
                    // Check if member is a premium member
                    if (member instanceof PremiumMember) {
                        PremiumMember premiumMember = (PremiumMember) member;
                        String result = premiumMember.payDueAmount(amount);
                        JOptionPane.showMessageDialog(this, result, "Payment", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Not a Premium Member!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    return;
                }
            }

            // Member not found
            JOptionPane.showMessageDialog(this, "Member not found!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Displays all members in a new window with formatted information
     * Shows different layouts for regular vs premium members
     * Creates a scrollable dialog with all member details
     */
    private void displayMembers() {
        // Create new window for displaying members
        JFrame displayFrame = new JFrame("Member Details");
        displayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create text area for member information
        JTextArea textArea = new JTextArea(20, 40);
        textArea.setEditable(false);                                    // Read-only
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));      // Monospaced for alignment
        textArea.setBackground(new Color(250, 250, 250));
        textArea.setForeground(textColor);

        // Add scroll capability
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Check if there are any members to display
        if (members.isEmpty()) {
            textArea.setText("No members found!");
        } else {
            StringBuilder displayText = new StringBuilder();

            // Loop through all members and format their information
            for (GymMember member : members) {
                // Add header based on member type
                if (member instanceof RegularMember) {
                    displayText.append("╔══════════════════════════════╗\n");
                    displayText.append("║       REGULAR MEMBER         ║\n");
                    displayText.append("╚══════════════════════════════╝\n\n");
                } else if (member instanceof PremiumMember) {
                    displayText.append("╔══════════════════════════════╗\n");
                    displayText.append("║       PREMIUM MEMBER         ║\n");
                    displayText.append("╚══════════════════════════════╝\n\n");
                }

                // Add common member information
                displayText.append("ID: ").append(member.getId()).append("\n");
                displayText.append("Name: ").append(member.getName()).append("\n");
                displayText.append("Location: ").append(member.getLocation()).append("\n");
                displayText.append("Phone: ").append(member.getPhone()).append("\n");
                displayText.append("Email: ").append(member.getEmail()).append("\n");
                displayText.append("Gender: ").append(member.getGender()).append("\n");
                displayText.append("DOB: ").append(member.getDOB()).append("\n");
                displayText.append("Start Date: ").append(member.getMembershipStartDate()).append("\n");
                displayText.append("Attendance: ").append(member.getAttendance()).append("\n");
                displayText.append("Loyalty Points: ").append(member.getLoyaltyPoints()).append("\n");
                displayText.append("Active Status: ").append(member.getActiveStatus() ? "Active" : "Inactive").append("\n");

                // Add type-specific information
                if (member instanceof RegularMember) {
                    RegularMember regMember = (RegularMember) member;
                    displayText.append("Plan: ").append(regMember.getPlan()).append("\n");
                    displayText.append("Price: ").append(regMember.getPrice()).append("\n");
                    // Show removal reason if member was removed
                    if (!regMember.getRemovalReason().isEmpty()) {
                        displayText.append("Removal Reason: ").append(regMember.getRemovalReason()).append("\n");
                    }
                } else if (member instanceof PremiumMember) {
                    PremiumMember premMember = (PremiumMember) member;
                    displayText.append("Personal Trainer: ").append(premMember.getPersonalTrainer()).append("\n");
                    displayText.append("Paid Amount: ").append(premMember.getPaidAmount()).append("\n");
                    displayText.append("Payment Status: ").append(premMember.getIsFullPayment() ? "Complete" : "Incomplete").append("\n");
                    // Calculate and show remaining amount
                    double remainingAmount = premMember.getPremiumCharge() - premMember.getPaidAmount();
                    displayText.append("Remaining Amount: ").append(remainingAmount).append("\n");

                    // Show discount if payment is complete
                    if (premMember.getIsFullPayment()) {
                        displayText.append("Discount Amount: ").append(premMember.getDiscountAmount()).append("\n");
                    }
                }

                // Add separator between members
                displayText.append("\n═════════════════════════════════════════\n\n");
            }

            textArea.setText(displayText.toString());
        }

        // Set up display window layout
        displayFrame.add(scrollPane);
        displayFrame.setSize(600, 600);
        displayFrame.setLocationRelativeTo(this);  // Center relative to main window

        // Add header panel to display window
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(primaryColor);
        JLabel titleLabel = new JLabel("Member Details", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        displayFrame.add(headerPanel, BorderLayout.NORTH);
        displayFrame.add(scrollPane, BorderLayout.CENTER);

        // Add close button at bottom
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(bgColor);
        JButton closeButton = new JButton("Close");
        styleButton(closeButton);
        closeButton.addActionListener(e -> displayFrame.dispose());  // Close window when clicked
        buttonPanel.add(closeButton);
        displayFrame.add(buttonPanel, BorderLayout.SOUTH);

        // Show the display window
        displayFrame.setVisible(true);
    }

    /**
     * Clears all input fields and resets form to default state
     * Resets text fields, combo boxes, and radio buttons
     */
    private void clearFields() {
        // Clear all text fields
        idField.setText("");
        nameField.setText("");
        locationField.setText("");
        phoneField.setText("");
        emailField.setText("");
        referralField.setText("");
        paidAmountField.setText("");
        removalReasonField.setText("");
        trainerField.setText("");
        discountField.setText("0");

        // Reset plan combo box to first option (Basic)
        planComboBox.setSelectedIndex(0);

        // Reset date combo boxes to first options
        dobDayComboBox.setSelectedIndex(0);
        dobMonthComboBox.setSelectedIndex(0);
        dobYearComboBox.setSelectedIndex(0);

        msDayComboBox.setSelectedIndex(0);
        msMonthComboBox.setSelectedIndex(0);
        msYearComboBox.setSelectedIndex(0);

        // Clear gender selection
        maleButton.setSelected(false);
        femaleButton.setSelected(false);
    }

    // ===============================
    // MAIN METHOD
    // ===============================
    
    /**
     * Main method to start the application
     * Sets system look and feel and creates the main GUI window
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            // Set system look and feel for native appearance
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Create and show GUI on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new GymGUI().setVisible(true);
        });
    }
}