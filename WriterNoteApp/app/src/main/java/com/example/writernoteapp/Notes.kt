package com.example.writernoteapp

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView

class Notes : AppCompatActivity() {
    lateinit var toggle : ActionBarDrawerToggle
    private lateinit var db: NoteDatabaseHelper
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_notes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawerLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        db = NoteDatabaseHelper(this)
        noteAdapter = NoteAdapter(db.bringNotes(), this)

        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.navView)
        val btnNewNote = findViewById<Button>(R.id.btnCreateNotes)
        val rvNotes = findViewById<RecyclerView>(R.id.rwNotes)

        rvNotes.layoutManager = LinearLayoutManager(this)
        rvNotes.adapter = noteAdapter

        btnNewNote.setOnClickListener {
            val intent = Intent(this, Notes_NewNote::class.java)
            startActivity(intent)
        }

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val toCharacters = Intent(this, Characters::class.java)
        val toNotes = Intent(this, Notes::class.java)
        val toChapters = Intent(this, Stories_Chapters::class.java)

        navView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.navStories ->
                    startActivity(toChapters)
                R.id.navNotes ->
                    startActivity(toNotes)
                R.id.navCharacters ->
                    startActivity(toCharacters)
            }
            true
        }
    }

    override fun onResume() {
        super.onResume()
        noteAdapter.refreshData(db.bringNotes())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) return true
        return super.onOptionsItemSelected(item)
    }

}