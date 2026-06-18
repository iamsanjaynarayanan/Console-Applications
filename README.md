# Console Applications

A collection of robust, production-grade console applications built to demonstrate core Java fundamentals, object-oriented design, algorithmic business logic, and efficient data structuring.

## Projects Overview

### 1. Library Management System
A feature-rich console application designed to handle end-to-end library workflows, featuring a highly automated Fine Engine and modular user access control.

* **Architecture:** Developed a multi-portal architecture separating concerns into Admin, User, and Explore modules, driven by a centralized static data layer.
* **Core Functionality:** Enabled interactive genre-based book discovery, a virtual shopping cart with real-time limit enforcement, and dynamic transaction tracking (borrow/return/lost logs).
* **Advanced Fine Engine:** Engineered complex penalty logic handling standard 15-day timelines, daily flat rates, and automated exponential penalty scaling (doubling every $X$ days) with security deposit deductions.

**Credentials:**  
* **Admin:** `venkat@gmail.com` | **Password:** `1234`  
* **User:** `sanjay@gmail.com` | **Password:** `1234`

---

### 2. Automated Teller Machine (ATM) Simulation
A secure, high-fidelity command-line simulation of an ATM network validating real-time financial restrictions and state-locking mechanisms.

* **Architecture:** Utilizes a centralized data hub acting as a "Single Source of Truth" with map-based architectures for fast lookups and transaction list logging.
* **Security Constraints:** Implemented automated 24-hour security lockouts triggered after 3 consecutive invalid PIN entry attempts.
* **Financial Operations:** Automated strict logic workflows for account balance checking, PIN changes, deposit reserves tracking, and strict currency denomination validation (multiples of 100).
* **Admin Portal:** Equipped with administrative workflows for manual account suspension, user onboarding validation, full financial auditing logs, and reserve liquidity tracking.

**Credentials:**  
* **Admin:** `venkat123` | **PIN:** `1234`  
* **User:** `sanjay123` | **PIN:** `1234`

---

## Technical Highlights & Competencies

* **Java Collections Framework:** Heavily leveraged `HashMap` structures for optimized $O(1)$ lookup times and `TreeMap` for natural-order data sorting.
* **Robust Input Validation:** Standardized application uptime by integrating comprehensive `try-catch` blocks for `InputMismatchException` to elegantly handle erratic user input.
* **Date-Time API Integration:** Automated precise temporal calculations using Java 8 `LocalDate` and `ChronoUnit` frameworks to calculate borrow aging and deadlines.
* **State Management:** Emulated cross-session relational integrity utilizing memory-resident mockup datastores.

## Prerequisites & Installation

**Java Development Kit (JDK) 8** or higher installed.
