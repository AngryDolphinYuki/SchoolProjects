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

class Characters_Edit : AppCompatActivity() {

    lateinit var db: NoteDatabaseHelper
    private var characterId: Int = -1

    private val headList = listOf(R.drawable.head1, R.drawable.head2)
    private val torsoList = listOf(R.drawable.torso1, R.drawable.torso2, R.drawable.torso3)
    private val bottomList = listOf(R.drawable.foot1, R.drawable.foot2, R.drawable.foot3)
    private var headIdx = 0
    private var torsoIdx = 0
    private var bottomIdx = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_characters_edit)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toBack = findViewById<ImageView>(R.id.btnBack)
        val characterName = findViewById<EditText>(R.id.etCharacterName)
        val characterRole = findViewById<EditText>(R.id.etCharacterRole)
        val headPrevious : ImageView = findViewById(R.id.btnHeadPrevious)
        val torsoPrevious : ImageView = findViewById(R.id.btnTopPrevious)
        val bottomPrevious : ImageView = findViewById(R.id.btnBottomPrevious)
        val headNext : ImageView = findViewById(R.id.btnHeadNext)
        val torsoNext : ImageView = findViewById(R.id.btnTopNext)
        val bottomNext : ImageView = findViewById(R.id.bottomNext)
        val ivHead : ImageView = findViewById(R.id.ivHead)
        val ivTorso : ImageView = findViewById(R.id.ivTop)
        val ivBottom : ImageView = findViewById(R.id.ivBottom)
        val nickname : EditText = findViewById(R.id.etNickname)
        val pronouns : EditText = findViewById(R.id.etPronouns)
        val age : EditText = findViewById(R.id.etAge)
        val race : EditText = findViewById(R.id.etRace)
        val height : EditText = findViewById(R.id.etHeight)
        val domHand : EditText = findViewById(R.id.etDominantHand)
        val status : EditText = findViewById(R.id.etStatus)
        val firstSeen : EditText = findViewById(R.id.etFirstSeen)
        val saveCharacter = findViewById<Button>(R.id.btnSaveCharacter)
        val personality : EditText = findViewById(R.id.etPersonality)
        val past : EditText = findViewById(R.id.etPast)
        val future : EditText = findViewById(R.id.etFuture)
        val additionalNotes : EditText = findViewById(R.id.etAdditionalNotes)

        db = NoteDatabaseHelper(this)

        characterId = intent.getIntExtra("character_id", -1)
        if (characterId == -1) {
            finish()
            return
        }

        val character = db.getCharacterByID(characterId)
        characterName.setText(character.name)
        characterRole.setText(character.role)
        ivHead.setImageResource(character.head)
        ivTorso.setImageResource(character.torso)
        ivBottom.setImageResource(character.bottom)
        nickname.setText(character.nickname)
        pronouns.setText(character.pronouns)
        age.setText(character.age)
        race.setText(character.race)
        height.setText(character.height)
        domHand.setText(character.domHand)
        status.setText(character.status)
        firstSeen.setText(character.firstSeen)
        personality.setText(character.personality)
        past.setText(character.past)
        future.setText(character.future)
        additionalNotes.setText(character.additionalNotes)

        toBack.setOnClickListener {
            val intent = Intent(this, Characters::class.java)
            startActivity(intent)
        }
        saveCharacter.setOnClickListener {
            val newName = characterName.text.toString()
            val newRole = characterRole.text.toString()
            val newHead = headList[headIdx]
            val newTorso = torsoList[torsoIdx]
            val newBottom = bottomList[bottomIdx]
            val newNickname = nickname.text.toString()
            val newPronouns = pronouns.text.toString()
            val newAge = age.text.toString()
            val newRace = race.text.toString()
            val newHeight = height.text.toString()
            val newDomhand = domHand.text.toString()
            val newStatus = status.text.toString()
            val newFirstSeen = firstSeen.text.toString()
            val newPersonality = personality.text.toString()
            val newPast = past.text.toString()
            val newFuture = future.text.toString()
            val newAdditionalNotes = additionalNotes.text.toString()
            val updateCharacter = Character(characterId, newName, newRole, newHead, newTorso, newBottom, newNickname, newPronouns, newAge, newRace, newHeight, newDomhand, newStatus, newFirstSeen, newPersonality, newPast, newFuture, newAdditionalNotes)
            db.updateCharacter(updateCharacter)
            finish()
            Toast.makeText(this, "Changes Saved", Toast.LENGTH_SHORT).show()
        }

        headPrevious.setOnClickListener {
            headIdx--
            if (headIdx < 0)
                headIdx = headList.size - 1
            ivHead.setImageResource(headList[headIdx])
        }
        headNext.setOnClickListener {
            headIdx++
            if (headIdx >= headList.count())
                headIdx = 0
            ivHead.setImageResource(headList[headIdx])
        }
        torsoPrevious.setOnClickListener {
            torsoIdx--
            if (torsoIdx < 0)
                torsoIdx = torsoList.size - 1
            ivTorso.setImageResource(torsoList[torsoIdx])
        }
        torsoNext.setOnClickListener {
            torsoIdx++
            if (torsoIdx >= torsoList.count())
                torsoIdx = 0
            ivTorso.setImageResource(torsoList[torsoIdx])
        }
        bottomPrevious.setOnClickListener {
            bottomIdx--
            if (bottomIdx < 0)
                bottomIdx = bottomList.size - 1
            ivBottom.setImageResource(bottomList[bottomIdx])
        }
        bottomNext.setOnClickListener {
            bottomIdx++
            if (bottomIdx >= bottomList.count())
                bottomIdx = 0
            ivBottom.setImageResource(bottomList[bottomIdx])
        }

    }
}