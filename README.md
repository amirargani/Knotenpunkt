# 🏥 Worship Service Management - Backend

[![License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](LICENSE)
[![Platform](https://img.shields.io/badge/Platform-Google%20Apps%20Script-lightgrey.svg)]()
[![Language](https://img.shields.io/badge/Language-JavaScript-orange.svg)]()

A robust backend solution built on **Google Apps Script** that serves as the central hub for church administration and community engagement. This project acts as a middleware between Google's productivity suite (Sheets & Calendar) and mobile applications (iOS/Android).

## 🏗 System Architecture

The project leverages Google Cloud Infrastructure to provide a lightweight, scalable, and cost-effective backend:
- **Middleware:** Google Apps Script (JavaScript)
- **Data Storage:** Google Sheets (Relational-style Document DB)
- **Scheduling:** Google Calendar API
- **Client Interface:** JSON via Web App API

## ✨ Features

- **🗓 Real-time Calendar Sync:** Fetches worship schedules and events directly from Google Calendar with quarterly filtering.
- **📆 Automated Data Management:** Manages quarterly service plans stored in structured Google Sheets.
- **🔐 Secure Access:** Token-based authentication for data retrieval and simple user login system.
- **🔄 Instant Updates:** Allows authorized users to update service details (e.g., moderators, speakers, cleaners) directly from the mobile app.

## 🛠 Tech Stack

| Category               | Technology                        |
|------------------------|-----------------------------------|
| **Runtime Environment**| Google Apps Script                |
| **Primary Language**   | JavaScript (ES6+)                 |
| **Data Persistence**   | Google Sheets                     |
| **Service Integration**| Google Calendar API               |
| **Output Format**      | JSON (RESTful API simulation)     |

## 📂 File Overview

| File | Description |
|------|-------------|
| `getCalendar.js` | Fetches events from Google Calendar and returns them as formatted JSON. |
| `getJSON.js` | Retrieves service planning data from quarterly Google Sheets. |
| `getLogin.js` | Handles user authentication against a "Benutzer" (Users) sheet. |
| `getUpdate.js` | Updates specific service details in Google Sheets based on mobile app input. |

## 🚀 Deployment

1. Create a new [Google Apps Script](https://script.google.com/) project.
2. Copy the contents of the `.js` files into your project.
3. Replace the placeholder URLs and IDs (e.g., Spreadsheet URLs, Calendar IDs) with your actual Google Resource details.
4. Deploy as a **Web App** with "Execute as me" and "Access: Anyone (with token)".
5. Use the provided tokens in your mobile application for authorization.

---
*Developed by Amir Argani*
