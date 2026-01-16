# 🙏 Knotenpunkt - Worship Service Management App

[![License](https://img.shields.io/badge/License-Apache_2.0-D22128?style=for-the-badge&logo=apache)](LICENSE)
[![Language](https://img.shields.io/badge/Language-Java-orange.svg?style=for-the-badge&logo=java)](https://www.java.com/)
[![Platform](https://img.shields.io/badge/Platform-Android-green.svg?style=for-the-badge&logo=windows)](https://www.android.com/)
[![SDK](https://img.shields.io/badge/SDK-24%20(Android%207.0)-blue.svg?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com/tools/releases/platforms?hl=us#7.0)

A professional Android application designed to streamline church administration and enhance community engagement. **Knotenpunkt** serves as the central platform for organizing worship services, managing events, and keeping the community connected.

---

## ✨ Features

### 🗓 Worship Schedule (Godiplan)
Full access to the service plan. See who is moderating, preaching, lead the children's moment, or helping with cleaning services.
- **Detailed Roles:** Moderation, KidsGo, Music (optional), Predigt, Putzdienst, and more.
- **Location & Time:** Get clear info on where and when each service takes place.

### 📅 Integrated Calendar
A unified view of all upcoming church events, services, and community activities.
- **Real-time updates:** Synchronized with the central database.
- **Event Details:** Start/End times, titles, descriptions, and locations.

### 🔐 Member Authentication
Secure login for church members to access internal information and personalized services.
- **Auto-login:** Stay logged in for a seamless experience.
- **Secure Backend:** Authentication handled via Google Apps Script.

### 📝 Dynamic Content
- **Excel Fragments:** Integrated viewing of spreadsheet data for administrative tasks.
- **Newspaper View:** Access community news and announcements.
- **Push Notifications:** Stay informed with important updates (Backend integrated).

---

## 🛠 Tech Stack

| Component | Technology |
| :--- | :--- |
| **Platform** | Android (Java) |
| **Networking** | Volley (for API requests) |
| **Backend** | Google Apps Script (Web App) |
| **Database** | Google Sheets (as lightweight DB) |
| **UI Components** | RecyclerView, Fragments, ViewPager2 |

---

## 🚀 Getting Started

### Prerequisites
- Android Studio (Electric Eel or newer recommended)
- JDK 11+
- Android Device or Emulator (API 21+)

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/Android_Knotenpunkt.git
   ```
2. Open the `Knotenpunkt` folder in Android Studio.
3. Wait for Gradle sync to complete.
4. Run the app on your device/emulator.

---

## 🏗 Architecture
The app follows a modern Android architecture using:
- **Fragments** for modular UI components.
- **Volley** for robust network communication with the Google Apps Script backend.
- **Shared Preferences** for lightweight local storage (e.g., login state).

---

## 🤝 Contributing
Contributions are welcome! Please feel free to submit a Pull Request.

---
*Developed by Amir Argani*
