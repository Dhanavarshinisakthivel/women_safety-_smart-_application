/* ===================================================================
   api.js - ONE place that knows how to talk to our backend.
   Every page includes this file so we never repeat fetch() code.
   =================================================================== */

// If the frontend is served from the SAME backend (recommended, and how
// this project is set up), we can just use relative paths like "/api/...".
const API_BASE = "";

function getToken() {
  return localStorage.getItem("token");
}

function saveSession(authResponse) {
  localStorage.setItem("token", authResponse.token);
  localStorage.setItem("fullName", authResponse.fullName);
  localStorage.setItem("email", authResponse.email);
  localStorage.setItem("role", authResponse.role);
}

function clearSession() {
  localStorage.clear();
}

function isLoggedIn() {
  return !!getToken();
}

function requireLogin() {
  if (!isLoggedIn()) {
    window.location.href = "/pages/login.html";
  }
}

/**
 * Central fetch wrapper.
 * - Automatically attaches the JWT token (if logged in)
 * - Automatically parses JSON
 * - Throws a readable Error if the backend returns an error
 */
async function apiFetch(path, options = {}) {
  const headers = options.headers || {};
  headers["Content-Type"] = "application/json";

  const token = getToken();
  if (token) headers["Authorization"] = "Bearer " + token;

  const response = await fetch(API_BASE + path, { ...options, headers });
  const contentType = response.headers.get("content-type") || "";
  const data = contentType.includes("application/json") ? await response.json() : null;

  if (!response.ok) {
    const message = (data && (data.error || Object.values(data)[0])) || "Something went wrong.";
    throw new Error(message);
  }
  return data;
}

function showAlert(elementId, message, type = "error") {
  const el = document.getElementById(elementId);
  if (!el) return;
  el.textContent = message;
  el.className = "alert-banner " + (type === "error" ? "alert-error" : "alert-success");
  el.style.display = "block";
}

/** Renders the shared nav bar depending on login state. Call on every page. */
function renderNav() {
  const nav = document.getElementById("nav");
  if (!nav) return;

  if (isLoggedIn()) {
    const role = localStorage.getItem("role");
    nav.innerHTML = `
      <div><a href="/pages/dashboard.html" style="font-size:18px;">🛡️ Women Safety App</a></div>
      <div>
        <a href="/pages/dashboard.html">Dashboard</a>
        <a href="/pages/sos.html">SOS</a>
        <a href="/pages/contacts.html">Contacts</a>
        <a href="/pages/incidents.html">Incidents</a>
        <a href="/pages/profile.html">Profile</a>
        ${role === "ADMIN" ? '<a href="/pages/admin.html">Admin</a>' : ""}
        <a href="#" onclick="logout()">Logout</a>
      </div>`;
  } else {
    nav.innerHTML = `
      <div><a href="/index.html" style="font-size:18px;">🛡️ Women Safety App</a></div>
      <div>
        <a href="/pages/login.html">Login</a>
        <a href="/pages/register.html">Register</a>
      </div>`;
  }
}

function logout() {
  clearSession();
  window.location.href = "/index.html";
}
