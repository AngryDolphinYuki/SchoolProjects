package com.example.writernoteapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Characters_BasicFutures : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_characters_basic_futures)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toBack = findViewById<ImageView>(R.id.btnBack)
        val toNext = findViewById<Button>(R.id.btnNext)

        val nickname : EditText = findViewById(R.id.etNickname)
        val pronouns : EditText = findViewById(R.id.etPronouns)
        val age : EditText = findViewById(R.id.etAge)
        val race : EditText = findViewById(R.id.etRace)
        val height : EditText = findViewById(R.id.etHeight)
        val domHand : EditText = findViewById(R.id.etDominantHand)
        val status : EditText = findViewById(R.id.etStatus)
        val firstSeen : EditText = findViewById(R.id.etFirstSeen)

        val headIdx = intent.extras?.getInt("head")
        val torsoIdx = intent.extras?.getInt("torso")
        val bottomIdx = intent.extras?.getInt("bottom")
        val name = intent.extras?.getString("characterName")
        val role = intent.extras?.getString("characterRole")


        toBack.setOnClickListener {
            val intent = Intent(this, Characters_Design::class.java)
            startActivity(intent)
        }
        toNext.setOnClickListener {
            val intent = Intent(this, Characters_Details::class.java)
            intent.putExtra("head", headIdx.toString().toInt())
            intent.putExtra("torso", torsoIdx.toString().toInt())
            intent.putExtra("bottom", bottomIdx.toString().toInt())
            intent.putExtra("characterName", name)
            intent.putExtra("characterRole", role)
            intent.putExtra("nickname", nickname.text.toString())
            intent.putExtra("pronouns", pronouns.text.toString())
            intent.putExtra("age", age.text.toString())
            intent.putExtra("race", race.text.toString())
            intent.putExtra("height", height.text.toString())
            intent.putExtra("domHand", domHand.text.toString())
            intent.putExtra("status", status.text.toString())
            intent.putExtra("firstSeen", firstSeen.text.toString())
            startActivity(intent)
        }
    }
}