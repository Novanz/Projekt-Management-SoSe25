package com.example.smartbinocularsapp

import android.content.ContentValues
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val etUsername = findViewById<EditText>(R.id.etNewUsername)
        val etPassword = findViewById<EditText>(R.id.etNewPassword)
        val btnSignUp = findViewById<Button>(R.id.btnSignUp)

        btnSignUp.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val dbHelper = UserDatabaseHelper(this)
            val db = dbHelper.writableDatabase

            val values = ContentValues().apply {
                put("username", username)
                put("password", password)
                put("displayName", username)
                put("profileImage", "")
            }

            val newRowId = db.insert("users", null, values)
            if (newRowId == -1L) {
                Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                finish()
            }
            db.close()
        }
    }
}