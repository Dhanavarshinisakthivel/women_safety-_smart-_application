-- =====================================================================
-- WOMEN SAFETY SMART APPLICATION - DATABASE SCHEMA
-- =====================================================================
-- HOW TO USE THIS FILE:
--   Option A (local MySQL): open MySQL Workbench or the mysql command
--   line, then run this whole file.
--   Option B (recommended for beginners): you don't actually NEED to run
--   this manually! Our Spring Boot app is configured with
--   "spring.jpa.hibernate.ddl-auto=update", which means it will look at
--   the Java model classes and automatically create these same tables
--   for you the first time it starts up. This file is provided so you
--   (1) can see/understand the exact structure, and (2) can run it by
--   hand if you ever need to set up the database manually or restore it.
-- =====================================================================

CREATE DATABASE IF NOT EXISTS women_safety_db;
USE women_safety_db;

-- ---------------------------------------------------------------------
-- USERS
-- Stores login credentials + basic profile info. The "role" column
-- doubles as our simple admin system: role = 'ADMIN' unlocks the admin
-- dashboard endpoints. (No separate admins table needed - one less
-- thing for a beginner to keep in sync.)
-- ---------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS users (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    email           VARCHAR(255) NOT NULL UNIQUE,
    password        VARCHAR(255) NOT NULL,          -- BCrypt hash, never plain text
    full_name       VARCHAR(255) NOT NULL,
    phone           VARCHAR(30),
    address         VARCHAR(500),
    photo_url       VARCHAR(500),
    role            ENUM('USER','ADMIN') NOT NULL DEFAULT 'USER',
    created_at      DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- ---------------------------------------------------------------------
-- EMERGENCY_CONTACTS
-- Trusted people who get notified when a user triggers SOS.
-- ---------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS emergency_contacts (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id         BIGINT NOT NULL,
    name            VARCHAR(255) NOT NULL,
    phone           VARCHAR(30) NOT NULL,
    email           VARCHAR(255),
    relationship    VARCHAR(100),
    priority        INT DEFAULT 1,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- ---------------------------------------------------------------------
-- SOS_ALERTS
-- One row per SOS button press. status moves ACTIVE -> RESOLVED
-- or ACTIVE -> FALSE_ALARM.
-- ---------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS sos_alerts (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id         BIGINT NOT NULL,
    latitude        DOUBLE,
    longitude       DOUBLE,
    message         VARCHAR(1000),
    status          ENUM('ACTIVE','RESOLVED','FALSE_ALARM') NOT NULL DEFAULT 'ACTIVE',
    triggered_at    DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    resolved_at     DATETIME,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- ---------------------------------------------------------------------
-- INCIDENTS
-- Non-realtime incident reports, enriched by AI (structured report +
-- risk level prediction).
-- ---------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS incidents (
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id             BIGINT NOT NULL,
    title               VARCHAR(255) NOT NULL,
    description         TEXT,
    ai_generated_report TEXT,
    photo_url           VARCHAR(500),
    latitude            DOUBLE,
    longitude           DOUBLE,
    risk_level          VARCHAR(20),                -- LOW / MEDIUM / HIGH
    status              ENUM('OPEN','UNDER_REVIEW','CLOSED') NOT NULL DEFAULT 'OPEN',
    created_at          DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- ---------------------------------------------------------------------
-- ALERT_HISTORY
-- Permanent archive of every SOS alert once resolved/closed, so users
-- can browse their full history even if the original row changes.
-- ---------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS alert_history (
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id             BIGINT NOT NULL,
    original_alert_id   BIGINT,
    latitude            DOUBLE,
    longitude           DOUBLE,
    message             VARCHAR(1000),
    final_status        VARCHAR(20),
    triggered_at        DATETIME,
    archived_at         DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- ---------------------------------------------------------------------
-- FEEDBACK
-- Simple contact/support form. user_id is nullable - guests can submit too.
-- ---------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS feedback (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id         BIGINT NULL,
    name            VARCHAR(255),
    email           VARCHAR(255),
    message         TEXT NOT NULL,
    rating          INT,
    submitted_at    DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL
);

-- ---------------------------------------------------------------------
-- Create a starter admin account.
-- Email: admin@womensafety.app   Password: Admin@123
-- (This hash below is a BCrypt hash of "Admin@123" - change it after
-- first login in a real deployment!)
-- ---------------------------------------------------------------------
INSERT INTO users (email, password, full_name, role)
SELECT 'admin@womensafety.app',
       '$2b$10$FWpfVM9/9fPk/kh6s4940OQ8uNvyAPhUDmBnkrG7fGSNWN8nd8ZOa',
       'System Admin', 'ADMIN'
WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = 'admin@womensafety.app');
