package com.example.writernoteapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Characters_Details : AppCompatActivity() {

    private lateinit var db: NoteDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_characters_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toBack = findViewById<ImageView>(R.id.btnBack)
        val saveCharacter = findViewById<Button>(R.id.btnSaveCharacter)
        val personality : EditText = findViewById(R.id.etPersonality)
        val past : EditText = findViewById(R.id.etPast)
        val future : EditText = findViewById(R.id.etFuture)
        val additionalNotes : EditText = findViewById(R.id.etAdditionalNotes)

        val headIdx = intent.extras?.getInt("head")
        val torsoIdx = intent.extras?.getInt("torso")
        val bottomIdx = intent.extras?.getInt("bottom")
        val name = intent.extras?.getString("characterName")
        val role = intent.extras?.getString("characterRole")
        val nickname = intent.extras?.getString("nickname")
        val pronouns = intent.extras?.getString("pronouns")
        val age = intent.extras?.getString("age")
        val race = intent.extras?.getString("race")
        val height = intent.extras?.getString("height")
        val domHand = intent.extras?.getString("domHand")
        val status = intent.extras?.getString("status")
        val firstSeen = intent.extras?.getString("firstSeen")

        db = NoteDatabaseHelper(this)

        toBack.setOnClickListener {
            val intent = Intent(this, Characters_BasicFutures::class.java)
            startActivity(intent)
        }
        saveCharacter.setOnClickListener {
            val character = Character(
                0,
                name.toString(),
                role.toString(),
                headIdx.toString().toInt(),
                torsoIdx.toString().toInt(),
                bottomIdx.toString().toInt(),
                nickname.toString(),
                pronouns.toString(),
                age.toString(),
                race.toString(),
                height.toString(),
                domHand.toString(),
                status.toString(),
                firstSeen.toString(),
                personality.text.toString(),
                past.text.toString(),
                future.text.toString(),
                additionalNotes.text.toString()
            )
            db.insertCharacter(character)
            val intent = Intent(this, Characters::class.java)
            startActivity(intent)
            Toast.makeText(this, "Character Saved", Toast.LENGTH_SHORT).show()
        }
    }
}