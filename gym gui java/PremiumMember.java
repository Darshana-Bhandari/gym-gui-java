package Coursework;

import java.io.Serializable;

/**
 * Represents a Premium Gym Member.
 * Inherits from the GymMember abstract class and adds premium-specific features.
 * Implements Serializable for object persistence.
 */
public class PremiumMember extends GymMember implements Serializable {
    private static final long serialVersionUID = 1L;

    // Fixed premium charge for all premium members
    private final double premiumCharge = 50000;

    // Premium-specific attributes
    private String personalTrainer;
    private boolean isFullPayment;
    private double paidAmount;
    private double discountAmount;

    /**
     * Constructor to initialize a PremiumMember with basic and premium attributes.
     *
     * @param id Member ID
     * @param name Full name
     * @param location Address/location of the member
     * @param phone Contact number
     * @param email Email address
     * @param gender Gender
     * @param DOB Date of birth
     * @param membershipStartDate Membership start date
     * @param personalTrainer Assigned personal trainer
     */
    public PremiumMember(int id, String name, String location, String phone, String email,
                         String gender, String DOB, String membershipStartDate, String personalTrainer) {
        super(id, name, location, phone, email, gender, DOB, membershipStartDate);
        this.personalTrainer = personalTrainer;
        this.isFullPayment = false;
        this.paidAmount = 0.0;
        this.discountAmount = 0.0;
    }

    // Accessor methods for all attributes
    public double getPremiumCharge() {
        return premiumCharge;
    }

    public String getPersonalTrainer() {
        return personalTrainer;
    }

    public boolean getIsFullPayment() {
        return isFullPayment;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    /**
     * Overrides the abstract markAttendance method.
     * Increases attendance and rewards loyalty points by 5 per visit.
     */
    @Override
    public void markAttendance() {
        this.attendance++;
        this.loyaltyPoints += 5;
    }

    /**
     * Handles payment for the premium membership.
     *
     * @param paidAmount The amount to be paid in this transaction.
     * @return Message indicating whether the payment was successful or invalid.
     */
    public String payDueAmount(double paidAmount) {
        // Case 1: Payment already completed
        if (this.isFullPayment) {
            return "Payment is already complete. No further payment required.";
        }

        // Case 2: Prevent overpayment
        if (this.paidAmount + paidAmount > premiumCharge) {
            return "Payment amount exceeds the premium charge. Maximum payable: " +
                   (premiumCharge - this.paidAmount);
        }

        // Case 3: Invalid amount (zero or negative)
        if (paidAmount <= 0) {
            return "Invalid payment amount. Please enter a positive value.";
        }

        // Case 4: Valid payment, update total paid
        this.paidAmount += paidAmount;

        // Mark payment complete if total paid reaches or exceeds premium charge
        if (this.paidAmount >= premiumCharge) {
            this.isFullPayment = true;
        }

        // Show remaining amount or success message
        double remainingAmount = premiumCharge - this.paidAmount;

        if (this.isFullPayment) {
            return "Payment successful. Payment completed in full!";
        } else {
            return String.format("Payment successful. Remaining amount: %.2f", remainingAmount);
        }
    }

    /**
     * Calculates discount (10%) if full payment is completed.
     * Displays a message based on eligibility.
     */
    public void calculateDiscount() {
        if (isFullPayment) {
            this.discountAmount = premiumCharge * 0.10; // 10% discount
            System.out.println("Discount calculated successfully: " +
                               String.format("%.2f", discountAmount));
        } else {
            this.discountAmount = 0.0; // No discount if not fully paid
            System.out.println("No discount available. Complete full payment to receive 10% discount.");
        }
    }

    /**
     * Resets this premium member to default state.
     * Also resets all premium-specific fields in addition to base member fields.
     */
    public void revertPremiumMember() {
        super.resetMember(); // Reset base class fields
        this.personalTrainer = "";
        this.isFullPayment = false;
        this.paidAmount = 0.0;
        this.discountAmount = 0.0;
    }

    /**
     * Displays all member details, including premium-related data.
     * Calls the base display method and adds premium information.
     */
    @Override
    public void display() {
        super.display(); // Show base details
        System.out.println("Personal Trainer: " +
                           (personalTrainer.isEmpty() ? "Not assigned" : personalTrainer));
        System.out.println("Premium Charge: " + String.format("%.2f", premiumCharge));
        System.out.println("Paid Amount: " + String.format("%.2f", paidAmount));
        System.out.println("Payment Status: " + (isFullPayment ? "Complete" : "Incomplete"));

        double remainingAmount = premiumCharge - paidAmount;
        System.out.println("Remaining Amount: " + String.format("%.2f", remainingAmount));

        if (isFullPayment && discountAmount > 0) {
            System.out.println("Discount Amount: " + String.format("%.2f", discountAmount));
        }
    }
}
