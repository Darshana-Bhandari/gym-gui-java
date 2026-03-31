#  Gym Management System
A Java-based desktop application with a graphical user interface (GUI) designed to manage gym members, track attendance, handle membership plans, and process payments efficiently.


##  Overview
The **Gym Management System** is a multi-functional application built using Java and Object-Oriented Programming (OOP) principles. It provides an organized way to manage gym operations such as member registration, attendance tracking, membership upgrades, and payment handling.

The system distinguishes between **Regular Members** and **Premium Members**, each offering different features and benefits.

## Project Structure

```id="x1k9ab"
Gym-Management-System/
│
├── GymGUI.java
├── GymMember.java
├── RegularMember.java
├── PremiumMember.java
│
├── package.bluej
├── *.ctxt
├── *.class
│
└── documentation.pdf
```

---

##  Features

###  Member Management
* Add and manage member details
* Store personal information (name, contact, email, DOB)
* Track membership start date

### Attendance System
* Record member attendance
* Automatically increase loyalty points
* Track attendance for upgrade eligibility

### Regular Member Features
* Subscription plans: **Basic, Standard, Deluxe**
* Plan upgrade based on attendance (after 30 visits)
* Referral source tracking
* Dynamic pricing based on selected plan

### Premium Member Features
* Fixed premium membership plan
* Payment tracking system
* Discount applied after full payment
* Additional premium benefits

### Payment Handling
* Track payments made by members
* Prevent overpayment
* Display remaining balance
* Apply discounts when conditions are met

### Graphical User Interface
* Interactive and user-friendly interface
* Built using Java Swing/AWT
* Form-based input system for easy data entry


## System Design
The application follows **Object-Oriented Design Principles**:
* **Encapsulation** → Member data is securely managed within classes
* **Inheritance** → `RegularMember` and `PremiumMember` extend `GymMember`
* **Abstraction** → Common functionality defined in abstract base class
* **Polymorphism** → Different implementations of attendance and payment methods

##  Technologies Used
* **Java** – Core programming language
* **Swing / AWT** – GUI development
* **OOP Concepts** – Core design structure
* **Serialization** – Data persistence
* **BlueJ IDE** – Development environment

##  How to Run
1. Open the project in any Java IDE (recommended: BlueJ, IntelliJ, Eclipse)
2. Compile all Java files:

   ```
   GymGUI.java
   GymMember.java
   RegularMember.java
   PremiumMember.java
   ```

3. Run the application:
   * Execute `GymGUI.java`

4. Use the graphical interface to manage members and operations


## Documentation
Detailed explanation of the system, design, and implementation is available in:
```id="8v4k2m"
Darshana Bhandari(24045861)documentation.pdf
```


##  Future Improvements

* Integration with a database (MySQL)
* User authentication system
* Online payment gateway
* Advanced reporting and analytics
* Improved UI/UX design

## Author

**Darshana Bhandari**
## 📄 License

This project is created for educational purposes and is not intended for commercial use.

---
