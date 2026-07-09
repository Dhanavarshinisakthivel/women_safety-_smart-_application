# 🛡️ Women Safety Smart Application

A full-stack safety app: Java Spring Boot backend, plain HTML/CSS/JS frontend,
MySQL database, and Google Gemini AI for smart features.

This README explains everything **from absolute zero** - no prior experience assumed.
Follow the sections in order (A → H). Don't skip ahead.

---

## 📦 What's included in Phase 1 (this zip)

**Core features (all working):**
Register/Login (JWT), Profile editing, Emergency Contacts, One-tap SOS with
live location, Incident Reporting, Alert History, Feedback form, Admin Dashboard.

**AI features (all working, powered by Gemini):**
1. AI Safety Assistant Chatbot
2. AI Emergency Message Generator (auto-writes your SOS text)
3. AI Incident Report Generator (turns your rough notes into a structured report)
4. AI Risk Level Prediction (scores each incident LOW/MEDIUM/HIGH)

**Coming in Phase 2** (ask me to build these next): AI Voice Distress Detection,
AI Safe Travel Planner, AI Contact Recommendation, AI Translation, AI Fake-Alert
Detection (advanced version), real Email/SMS sending, Google Maps route/nearby
police-station finder. The database and code are already structured to support
all of these - Phase 1 focuses on a rock-solid, deployable core first.

---

## A. Install an IDE and open the project

1. Download **IntelliJ IDEA Community Edition** (free): https://www.jetbrains.com/idea/download/
   - It's the easiest tool for Java/Spring Boot beginners. VS Code also works
     but needs extra extensions - IntelliJ has everything built in.
2. Install it (Next → Next → Finish, defaults are fine).
3. Unzip the project folder I gave you somewhere easy to find, e.g. `Desktop/women-safety-app`.
4. Open IntelliJ → **Open** → select the `women-safety-app` folder → click **Trust Project**.
5. IntelliJ will notice `pom.xml` and ask to **"Load Maven Project"** - click yes.
   Wait a few minutes (bottom right progress bar) while it downloads all the
   Java libraries. You need internet for this step only.

You'll know it worked when the left sidebar shows folders like `controller`,
`service`, `repository`, `model` under `src/main/java/com/womensafety`.

---

## B. Install and configure MySQL locally

1. Download **MySQL Community Server**: https://dev.mysql.com/downloads/mysql/
   - During install, it will ask you to set a **root password** - write it down,
     you'll need it in a minute. Use the default port `3306`.
2. Also install **MySQL Workbench** (usually bundled with the installer) - it's
   a visual tool so you don't have to type raw SQL commands.
3. Open MySQL Workbench → connect using `root` and the password you set.
4. Open the file `database/schema.sql` (from this project) in Workbench
   (File → Open SQL Script), then click the ⚡ lightning bolt icon to run it.
   This creates the database and all tables, plus one admin login
   (`admin@womensafety.app` / `Admin@123`).
5. In the project, copy `.env.example` to a new file named `.env` and fill in:
   ```
   DB_PASSWORD=the_password_you_set_in_step_1
   ```
   (Everything else can stay as the default for local testing.)

**Note:** you technically don't even need to run schema.sql by hand - the app
is configured to auto-create tables on first run. Running it manually just
lets you see the structure and get the pre-made admin account.

---

## C. Get a free Gemini API key

1. Go to https://aistudio.google.com/apikey
2. Sign in with any Google account (free).
3. Click **"Create API key"** → copy the long string it gives you.
4. Paste it into your `.env` file:
   ```
   GEMINI_API_KEY=your_actual_key_here
   ```
This key powers all 4 AI features. Google's free tier gives generous
daily limits, plenty for a student project demo.

---

## D. Run the backend and frontend locally

Since `.env` files aren't automatically read by plain Java, the simplest
beginner approach is to set these as **Run Configuration environment
variables** inside IntelliJ:

1. In IntelliJ, find `WomenSafetyApplication.java` (under `src/main/java/com/womensafety`).
2. Right-click it → **Run**. It will fail the first time (missing env vars) - that's expected.
3. Click the dropdown near the top-right (▶ Run icon area) → **Edit Configurations**.
4. Find **WomenSafetyApplication** → click the **Environment Variables** field →
   paste in (semicolon-separated):
   ```
   DB_HOST=localhost;DB_PORT=3306;DB_NAME=women_safety_db;DB_USER=root;DB_PASSWORD=yourpassword;JWT_SECRET=some-long-random-string-here;GEMINI_API_KEY=your_key_here;ALLOWED_ORIGINS=*
   ```
5. Click **OK**, then click the green ▶ Run button.
6. Watch the bottom console - when you see
   `Women Safety App backend is running!` it worked.
7. Open your browser to **http://localhost:8080** - you should see the app's home page.
   (The frontend is served automatically by the same backend - no separate step needed.)

**Test it:** Register a new account → log in → go to Dashboard → try the AI
chatbot → try the SOS button (allow location access when your browser asks).

---

## E. Create a free Railway account and deploy

1. Go to https://railway.app → **Sign up** (use GitHub sign-in, it's fastest).
2. Push this project to a new GitHub repository:
   - Create a new repo on https://github.com/new (keep it Private if you want).
   - In a terminal, inside the project folder, run:
     ```
     git init
     git add .
     git commit -m "Initial commit"
     git branch -M main
     git remote add origin https://github.com/YOUR_USERNAME/YOUR_REPO.git
     git push -u origin main
     ```
3. In Railway, click **New Project** → **Deploy from GitHub repo** → pick your repo.
   Railway detects the `Dockerfile` automatically and starts building.
4. **Add a MySQL database:** In the same Railway project, click **+ New** →
   **Database** → **Add MySQL**. Railway spins up a free MySQL instance for you
   automatically - no separate signup needed.
5. Click on the MySQL service → **Variables** tab → note the values for
   `MYSQLHOST`, `MYSQLPORT`, `MYSQLDATABASE`, `MYSQLUSER`, `MYSQLPASSWORD`
   (Railway generates these for you).

---

## F. Set environment variables in Railway

1. Click on your **backend service** (not the MySQL one) → **Variables** tab.
2. Add each of these (click "New Variable" for each):

   | Variable | Value |
   |---|---|
   | `DB_HOST` | (paste MYSQLHOST from the MySQL service) |
   | `DB_PORT` | (paste MYSQLPORT) |
   | `DB_NAME` | (paste MYSQLDATABASE) |
   | `DB_USER` | (paste MYSQLUSER) |
   | `DB_PASSWORD` | (paste MYSQLPASSWORD) |
   | `JWT_SECRET` | any long random string, e.g. `myFinalYearProject2026SecretKey!` |
   | `GEMINI_API_KEY` | your Gemini key from Section C |
   | `ALLOWED_ORIGINS` | `*` |

   Tip: Railway lets you reference another service's variables directly using
   `${{MySQL.MYSQLHOST}}` syntax instead of copy-pasting - look for the
   "Add Reference" option next to New Variable if you want to try it.

3. Click **Deploy** (or it may redeploy automatically after you save variables).

---

## G. Get your live link and confirm it's working

1. In Railway, click your backend service → **Settings** tab → **Networking**
   → click **Generate Domain**. Railway gives you a free URL like
   `https://women-safety-app-production.up.railway.app`.
2. Open that URL in your browser - you should see the same home page as locally.
3. Register a test account, try the SOS button, try the AI chatbot.
4. Check the **Deployments** tab logs if anything looks broken (see Section H).

🎉 That link is your live, deployed, 100% free project - share it with your
professor/evaluator directly.

---

## H. Common beginner errors and fixes

| Problem | Likely Cause | Fix |
|---|---|---|
| IntelliJ won't load Maven dependencies | No internet, or Maven cache issue | Check your connection; try `View → Tool Windows → Maven → Reload All Maven Projects` |
| App fails to start: "Communications link failure" | MySQL isn't running, or wrong `DB_HOST`/`DB_PORT` | Make sure MySQL service is running locally (check Windows Services / `mysql.server start` on Mac); double check your `.env`/env vars |
| App fails to start: "Access denied for user root" | Wrong `DB_PASSWORD` | Re-check the password you set during MySQL install |
| Login returns "Invalid email or password" right after registering | Password is case-sensitive, or you're testing on the wrong environment (local vs deployed have separate databases) | Re-type carefully; remember local and Railway databases are different accounts |
| AI chatbot replies "AI features are not configured yet" | `GEMINI_API_KEY` missing or blank | Double check the env variable name is exactly `GEMINI_API_KEY` with no extra spaces |
| Railway build fails | Usually a typo in `pom.xml` or Dockerfile, or out-of-memory on free tier | Check the **Build Logs** tab in Railway for the red error line - it tells you exactly what failed |
| SOS button says "location not available" | Browser blocked location access | Click the padlock icon in the browser address bar → Site settings → allow Location; note some browsers require HTTPS (Railway's domain is HTTPS, so this is mainly a local-testing quirk) |
| Admin dashboard says "Admin access required" | Your account isn't an admin | Either log in with the seeded `admin@womensafety.app` / `Admin@123` account, or manually run in MySQL: `UPDATE users SET role='ADMIN' WHERE email='youremail@example.com';` |
| 403 Forbidden on API calls | JWT token expired (24 hr) or missing | Log out and log back in to get a fresh token |
| CORS error in browser console | `ALLOWED_ORIGINS` misconfigured | Since the frontend is served by the same backend in this setup, this shouldn't normally happen - but if you split them onto different domains later, set `ALLOWED_ORIGINS` to your frontend's exact URL |

---

## Project structure (for reference)

```
women-safety-app/
├── pom.xml                     # Maven dependencies
├── Dockerfile                  # How Railway builds/runs the app
├── railway.json                 # Railway deployment config
├── .env.example                # Template for local secrets
├── database/schema.sql         # Full MySQL schema
└── src/main/
    ├── java/com/womensafety/
    │   ├── WomenSafetyApplication.java   # Entry point
    │   ├── controller/          # Receives HTTP requests
    │   ├── service/              # Business logic (incl. AI)
    │   ├── repository/           # Database access (Spring Data JPA)
    │   ├── model/                # Entities = database tables
    │   ├── dto/                  # Request/response shapes
    │   ├── security/             # JWT handling
    │   └── config/                # Security rules, error handling
    └── resources/
        ├── application.properties
        └── static/                # Frontend: plain HTML/CSS/JS
            ├── index.html
            ├── css/style.css
            ├── js/api.js
            └── pages/ (login, register, dashboard, sos, contacts, incidents, profile, admin)
```

---

## Next steps

Want Phase 2? Just ask, and I'll add: real email/SMS notifications, Google
Maps safe-route + nearby police/hospital finder, AI voice distress detection,
AI travel planner, AI contact recommendation, AI translation, and an
upgraded AI fake-alert detector - all wired into this same codebase.
