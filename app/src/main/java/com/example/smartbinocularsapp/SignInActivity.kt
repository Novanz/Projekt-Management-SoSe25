package com.example.smartbinocularsapp

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnSignIn = findViewById<Button>(R.id.btnSignIn)
        val tvSignUp = findViewById<TextView>(R.id.tvSignUp)

        btnSignIn.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val dbHelper = UserDatabaseHelper(this)
            val db = dbHelper.readableDatabase
            val cursor: Cursor = db.rawQuery(
                "SELECT * FROM users WHERE username=? AND password=?",
                arrayOf(username, password)
            )

            if (cursor.moveToFirst()) {
                // Save username in SharedPreferences for later use
                val prefs = getSharedPreferences("app_prefs", MODE_PRIVATE)
                prefs.edit().putString("username", username).apply()
                Toast.makeText(this, "Sign in successful", Toast.LENGTH_SHORT).show()
                // Start MainActivity
                 startActivity(Intent(this, MainActivity::class.java))
                 finish()
            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
            cursor.close()
            db.close()
        }

        tvSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}