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

class Characters_Design : AppCompatActivity() {
    private val headList = listOf(R.drawable.head1, R.drawable.head2)
    private val torsoList = listOf(R.drawable.torso1, R.drawable.torso2, R.drawable.torso3)
    private val bottomList = listOf(R.drawable.foot1, R.drawable.foot2, R.drawable.foot3)
    private var headIdx = 0
    private var torsoIdx = 0
    private var bottomIdx = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_characters_design)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toBack = findViewById<ImageView>(R.id.btnBack)
        val toNext = findViewById<Button>(R.id.btnNext)
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

        toBack.setOnClickListener {
            val intent = Intent(this, Characters::class.java)
            startActivity(intent)
        }
        toNext.setOnClickListener {
            val intent = Intent(this, Characters_BasicFutures::class.java)
            intent.putExtra("head", headList[headIdx].toString().toInt())
            intent.putExtra("torso", torsoList[torsoIdx].toString().toInt())
            intent.putExtra("bottom", bottomList[bottomIdx].toString().toInt())
            intent.putExtra("characterName", characterName.text.toString())
            intent.putExtra("characterRole", characterRole.text.toString())
            startActivity(intent)
        }
    }
}