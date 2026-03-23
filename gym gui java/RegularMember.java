package Coursework;

import java.io.Serializable;

/**
 * Represents a Regular Gym Member.
 * Extends GymMember and adds regular-specific attributes and behaviors.
 * Implements Serializable for file-based persistence.
 */
public class RegularMember extends GymMember implements Serializable {
    private static final long serialVersionUID = 1L;

    // Maximum attendance before becoming eligible for upgrade
    private final int attendanceLimit = 30;

    // Regular member-specific fields
    private boolean isEligibleForUpgrade;  // Tracks if the member can upgrade the plan
    private String removalReason;          // Reason for member removal (if any)
    private String referralSource;         // How the member found the gym (e.g., friend, ad)
    private String plan;                   // Current subscription plan: basic, standard, deluxe
    private double price;                  // Price of the current plan

    /**
     * Constructor to initialize a RegularMember with basic and regular-specific attributes.
     *
     * @param id Member ID
     * @param name Member name
     * @param location Address/location
     * @param phone Contact number
     * @param email Email address
     * @param gender Gender
     * @param DOB Date of birth
     * @param membershipStartDate Membership start date
     * @param referralSource Source from where member heard about the gym
     */
    public RegularMember(int id, String name, String location, String phone, String email,
                         String gender, String DOB, String membershipStartDate, String referralSource) {
        super(id, name, location, phone, email, gender, DOB, membershipStartDate);
        this.isEligibleForUpgrade = false;
        this.removalReason = "";
        this.referralSource = referralSource;
        this.plan = "basic";
        this.price = 6500;
    }

    // Getter (accessor) methods for regular-specific attributes
    public int getAttendanceLimit() { return attendanceLimit; }
    public boolean getIsEligibleForUpgrade() { return isEligibleForUpgrade; }
    public String getRemovalReason() { return removalReason; }
    public String getReferralSource() { return referralSource; }
    public String getPlan() { return plan; }
    public double getPrice() { return price; }

    /**
     * Overrides the abstract method to mark attendance.
     * Increments attendance count and adds loyalty points.
     * If attendance reaches the limit, makes member eligible for upgrade.
     */
    @Override
    public void markAttendance() {
        this.attendance++;
        this.loyaltyPoints += 5;

        // Make member eligible for upgrade if attendance limit is reached
        if (this.attendance >= attendanceLimit) {
            this.isEligibleForUpgrade = true;
        }
    }

    /**
     * Returns the price of a given plan.
     * 
     * @param plan The plan name (basic, standard, deluxe)
     * @return The corresponding price or -1 if the plan is invalid
     */
    public double getPlanPrice(String plan) {
        switch (plan.toLowerCase()) {
            case "basic": return 6500;
            case "standard": return 12500;
            case "deluxe": return 18500;
            default: return -1; // Invalid plan
        }
    }

    /**
     * Allows eligible members to upgrade to a new plan.
     *
     * @param newPlan The plan to upgrade to
     * @return A message showing success or failure reason
     */
    public String upgradePlan(String newPlan) {
        if (!isEligibleForUpgrade) {
            return "Not eligible for upgrade. Need more attendance.";
        }

        if (newPlan.equalsIgnoreCase(this.plan)) {
            return "Already subscribed to this plan.";
        }

        double newPrice = getPlanPrice(newPlan);
        if (newPrice == -1) {
            return "Invalid plan selected.";
        }

        // Perform upgrade
        this.plan = newPlan.toLowerCase();
        this.price = newPrice;
        return "Plan upgraded successfully to " + newPlan;
    }

    /**
     * Resets this regular member to default state.
     * Stores reason for removal and resets plan and eligibility.
     *
     * @param removalReason Reason why the member is removed or reset
     */
    public void revertRegularMember(String removalReason) {
        super.resetMember(); // Reset common member attributes
        this.isEligibleForUpgrade = false;
        this.plan = "basic";
        this.price = 6500;
        this.removalReason = removalReason;
    }

    /**
     * Displays all details of the regular member.
     * Includes both base and regular-specific attributes.
     */
    @Override
    public void display() {
        super.display(); // Display base class info
        System.out.println("Plan: " + plan);
        System.out.println("Price: " + price);
        if (!removalReason.isEmpty()) {
            System.out.println("Removal Reason: " + removalReason);
        }
    }
}
