# Library Management System (Java)

A feature-rich, console-based Library Management System designed to handle end-to-end library operations. This project features a robust Fine Engine, Tiered User Access, and a Centralized Data Layer to manage book inventories and member activities efficiently.

## Core Features

### User Module (Users.java & Explore.java)

- Interactive Exploration: Browse books categorized by 6 distinct genres (Science/Tech, Classics, Sci-Fi, etc.).
- Cart & Checkout System: Add multiple books to a digital cart, modify selections, and checkout once the session is authenticated.
- Tenure Extension: Request to extend a book's return deadline (up to 2 times) directly from the user dashboard.
- Security Deposit Management: View and top-up the mandatory security deposit required for borrowing.
- Loss Reporting: Report lost books or membership cards with automated fine calculations based on book cost.

### Admin Module (Admins.java)

- Inventory Control: Add, modify, or delete books. Search books by Name or ISBN.
- Data Analytics: Track the "Most Read" and "Least Read" books to understand library trends.
- Account Management: Create and manage both User and Admin accounts.
- Fine Policy Editor: Real-time modification of global library rules, including return limits, daily increments, and exponential fine rates.

## Advanced Fine Engine

The system enforces a sophisticated fine logic to ensure timely returns:
- Return Limit: Standard 15-day borrowing window.
- Daily Increment: A base fine (e.g., Rs. 2) added for every day overdue.
- Exponential Growth: Overdue fines double every X days (e.g., every 10 days) to encourage prompt returns.
- Security Deposit Integration: Fines can be automatically deducted from the user's security deposit.

## Technical Architecture

- Database.java: Acts as the data hub using Static Collections (HashMap, TreeMap, ArrayList) to maintain state across different sessions.
- Explore.java: Manages the "Shopping Cart" logic and genre-based filtering.
- Main.java: The central controller handling authentication and the primary application loop.
- Admins.java / Users.java: Encapsulates logic specific to user roles, ensuring a clean separation of concerns.

## Data Structure Overview

- isbnbook: Stores book details (Author, Borrow Count, Quantity, Price, Genre) keyed by ISBN.
- userborrowhistory: Tracks every book a user has ever interacted with, including its return status (Returned, Not Returned, or Lost).
- userfines: A historical log of all fines levied against a user account.

## Credentials

Admin ID: venkat@gmail.com, suda@gmail.com | Password: 1234, 
User ID: sanjay@gmail.com, padmesh@gmail.com | Password: 1234

Prerequisites

- Java Development Kit (JDK) 8 or higher.
