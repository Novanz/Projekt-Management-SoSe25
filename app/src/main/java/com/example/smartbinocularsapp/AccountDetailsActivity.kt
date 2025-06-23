package com.example.smartbinocularsapp

import android.content.ContentValues
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AccountDetailsActivity : AppCompatActivity() {
    private var username: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_details)

        val ivProfileImage = findViewById<ImageView>(R.id.ivProfileImage)
        val tvUsername = findViewById<TextView>(R.id.tvUsername)
        val etDisplayName = findViewById<EditText>(R.id.etDisplayName)
        val etAccountName = findViewById<EditText>(R.id.etAccountName)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnSave = findViewById<Button>(R.id.btnSave)

        val prefs = getSharedPreferences("app_prefs", MODE_PRIVATE)
        val username = prefs.getString("username", null)
        if (username == null) {
            Toast.makeText(this, "No user found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val dbHelper = UserDatabaseHelper(this)
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT displayName, profileImage FROM users WHERE username=?", arrayOf(username))
        if (cursor.moveToFirst()) {
            val displayName = cursor.getString(0)
            tvUsername.text = username
            etDisplayName.setText(displayName)
        }
        cursor.close()
        db.close()

        btnSave.setOnClickListener {
            val newDisplayName = etDisplayName.text.toString().trim()
            val newAccountName = etAccountName.text.toString().trim()
            val newPassword = etPassword.text.toString().trim()

            if (newDisplayName.isEmpty()) {
                Toast.makeText(this, "Display name cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val dbw = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put("displayName", newDisplayName)
                if (newAccountName.isNotEmpty()) put("username", newAccountName)
                if (newPassword.isNotEmpty()) put("password", newPassword)
            }
            dbw.update("users", values, "username=?", arrayOf(username))
            dbw.close()

            if (newAccountName.isNotEmpty()) {
                prefs.edit().putString("username", newAccountName).apply()
                tvUsername.text = newAccountName
            }

            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
        }

        // TODO: Add profile image change logic if needed
    }
}