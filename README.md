# Steady Habit Tracker (free web app / PWA)

This is a small installable web app prototype designed for Android Chrome. It logs urges and setbacks with timestamps, stores data in browser localStorage, shows rough time-of-day patterns, and includes reminder settings and a website-blocking plan list.

## Install it on your Android home screen
1. Host these files over HTTPS (for example, a static site host such as GitHub Pages or Cloudflare Pages).
2. Open the HTTPS site in Chrome on Android.
3. Open Chrome's three-dot menu and choose **Install app** or **Add to Home screen**.
4. Allow notifications if prompted.

## Important limitations
- This version is a real installable PWA, not a native APK.
- The web app cannot reliably block other websites system-wide. The saved website list is only a reminder list, not an active blocker.
- Daily and random background notifications are not guaranteed by a static PWA. Reliable scheduled notifications while closed need a push backend or native Android implementation; this project does not include a paid service or a backend.
- Data stays in local browser storage and can be lost if browser data is cleared. Use Export data to save a backup.
- No account, analytics, or external API is used.

## Free hosting options
Any HTTPS static hosting that serves these files can work. This zip does not automatically publish itself or install on your phone; you need to deploy it first.
