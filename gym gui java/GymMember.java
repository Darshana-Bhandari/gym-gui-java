package Coursework;

import java.io.Serializable; // Enables object serialization for saving/loading objects

/**
 * Abstract class representing a Gym Member.
 * Implements Serializable to allow objects to be written to and read from a file.
 */
public abstract class GymMember implements Serializable {
    
    // Serial version ID used during deserialization to verify compatibility
    private static final long serialVersionUID = 1L;

    // Basic member details
    protected int id;
    protected String name;
    protected String location;
    protected String phone;
    protected String email;
    protected String gender;
    protected String DOB;
    protected String membershipStartDate;

    // Additional fields related to membership
    protected int attendance;
    protected double loyaltyPoints;
    protected boolean activeStatus;

    /**
     * Constructor to initialize a GymMember object.
     * 
     * @param id Member ID
     * @param name Full name of the member
     * @param location Address/location of the member
     * @param phone Contact number
     * @param email Email address
     * @param gender Gender of the member
     * @param DOB Date of birth
     * @param membershipStartDate Membership start date
     */
    public GymMember(int id, String name, String location, String phone, String email, 
                     String gender, String DOB, String membershipStartDate) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.DOB = DOB;
        this.membershipStartDate = membershipStartDate;
        this.attendance = 0;
        this.loyaltyPoints = 0;
        this.activeStatus = false;
    }

    // Getter methods (Accessors) to retrieve member information
    public int getId() { return id; }
    public String getName() { return name; }
    public String getLocation() { return location; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getGender() { return gender; }
    public String getDOB() { return DOB; }
    public String getMembershipStartDate() { return membershipStartDate; }
    public int getAttendance() { return attendance; }
    public double getLoyaltyPoints() { return loyaltyPoints; }
    public boolean getActiveStatus() { return activeStatus; }

    /**
     * Abstract method that must be implemented by subclasses to define how attendance is marked.
     */
    public abstract void markAttendance();

    /**
     * Activates the member's gym membership.
     */
    public void activateMembership() {
        this.activeStatus = true;
    }

    /**
     * Deactivates the member's gym membership if it's currently active.
     */
    public void deactivateMembership() {
        if (this.activeStatus) {
            this.activeStatus = false;
        }
    }

    /**
     * Resets the member's attendance and loyalty points and deactivates membership.
     */
    public void resetMember() {
        this.activeStatus = false;
        this.attendance = 0;
        this.loyaltyPoints = 0;
    }

    /**
     * Displays all member details to the console.
     */
    public void display() {
        System.out.println("Member ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Location: " + location);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
        System.out.println("Gender: " + gender);
        System.out.println("DOB: " + DOB);
        System.out.println("Membership Start Date: " + membershipStartDate);
        System.out.println("Attendance: " + attendance);
        System.out.println("Loyalty Points: " + loyaltyPoints);
        System.out.println("Active Status: " + activeStatus);
    }
}
