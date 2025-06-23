package com.example.smartbinocularsapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ivProfile = findViewById<ImageView>(R.id.ivProfile)
        val fabAdd = findViewById<FloatingActionButton>(R.id.fabAdd)
        val fabEdit = findViewById<FloatingActionButton>(R.id.fabEdit)
        val rvNotes = findViewById<RecyclerView>(R.id.rvNotes)

        // TODO: Set up RecyclerView with notes adapter

        ivProfile.setOnClickListener {
            startActivity(Intent(this, AccountDetailsActivity::class.java))
        }

        fabAdd.setOnClickListener {
            // TODO: Open add note screen
        }

        fabEdit.setOnClickListener {
            // TODO: Open edit note screen
        }
    }
}