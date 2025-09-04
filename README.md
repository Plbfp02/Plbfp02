## JobTracker (Android)

Track jobs and earnings for a self‑employed professional. Built with Jetpack Compose and Room.

### Features
- Add, edit, and delete jobs (client, date, hours, rate, notes, paid)
- View jobs list with running totals
- Monthly/Custom summary of earnings and hours
- Export/share CSV

### Tech
- Jetpack Compose (Material 3)
- Room (SQLite)
- ViewModel + Kotlin Flows
- Navigation-Compose

### Build & Run
1. Open the project in Android Studio (Koala+ recommended).
2. Let it sync Gradle; then run the `app` configuration on a device or emulator.

### Data Model
- Job: id, client, date, hours, hourlyRate, paid, notes
- Earnings = hours × hourlyRate

### License
MIT