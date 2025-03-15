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

class Characters : AppCompatActivity() {
    lateinit var toggle : ActionBarDrawerToggle
    private lateinit var db: NoteDatabaseHelper
    private lateinit var characterAdapter: CharacterAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_characters)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawerLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnCreateCharacter = findViewById<Button>(R.id.btnCreateCharacter)
        val rwCharacters = findViewById<RecyclerView>(R.id.rwCharacters)

        db = NoteDatabaseHelper(this)
        characterAdapter = CharacterAdapter(db.bringCharacterFirstLooks(), this)
        rwCharacters.layoutManager = LinearLayoutManager(this)
        rwCharacters.adapter = characterAdapter


        btnCreateCharacter.setOnClickListener {
            val intent = Intent(this, Characters_Design::class.java)
            startActivity(intent)
        }

        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.navView)

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) return true
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        characterAdapter.refreshData(db.bringCharacterFirstLooks())
    }

}