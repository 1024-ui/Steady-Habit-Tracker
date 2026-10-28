package com.example.urgetracker

import android.app.Activity
import android.os.Bundle
import android.widget.*
import android.graphics.Color
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : Activity() {
    private lateinit var stats: TextView
    private lateinit var history: TextView
    private val prefs by lazy { getSharedPreferences("tracker", MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buildUi()
        refresh()
    }

    private fun buildUi() {
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(28, 32, 28, 24)
            setBackgroundColor(Color.WHITE)
        }

        val title = TextView(this).apply {
            text = "Urge Tracker"
            textSize = 30f
            setTextColor(Color.BLACK)
            setPadding(0,0,0,12)
        }
        root.addView(title)

        val subtitle = TextView(this).apply {
            text = "Track urges, setbacks, and the times they happen."
            textSize = 16f
            setPadding(0,0,0,18)
        }
        root.addView(subtitle)

        stats = TextView(this).apply { textSize = 17f; setPadding(0,0,0,18) }
        root.addView(stats)

        val urge = Button(this).apply {
            text = "LOG URGE"
            setOnClickListener { log("Urge") }
        }
        root.addView(urge)

        val setback = Button(this).apply {
            text = "LOG SETBACK"
            setOnClickListener { log("Setback") }
        }
        root.addView(setback)

        val clear = Button(this).apply {
            text = "CLEAR HISTORY"
            setOnClickListener {
                prefs.edit().clear().apply()
                refresh()
            }
        }
        root.addView(clear)

        val h = TextView(this).apply {
            text = "Recent history"
            textSize = 21f
            setPadding(0,20,0,8)
        }
        root.addView(h)

        history = TextView(this).apply { textSize = 15f }
        root.addView(ScrollView(this).apply {
            addView(history)
            layoutParams = LinearLayout.LayoutParams(-1, 0, 1f)
        })

        setContentView(root)
    }

    private fun log(type: String) {
        val now = SimpleDateFormat("MMM d, yyyy  h:mm a", Locale.getDefault()).format(Date())
        val old = prefs.getString("history", "") ?: ""
        val entry = "$type | $now"
        prefs.edit().putString("history", "$entry\n$old").apply()
        refresh()
    }

    private fun refresh() {
        val text = prefs.getString("history", "") ?: ""
        val lines = text.lines().filter { it.isNotBlank() }
        val urges = lines.count { it.startsWith("Urge") }
        val setbacks = lines.count { it.startsWith("Setback") }
        stats.text = "Urges logged: $urges\nSetbacks logged: $setbacks\nEntries: ${lines.size}"
        history.text = if (lines.isEmpty()) "Nothing logged yet." else lines.take(30).joinToString("\n")
    }
}
