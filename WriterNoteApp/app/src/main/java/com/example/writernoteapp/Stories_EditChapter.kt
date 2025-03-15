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

class Stories_EditChapter : AppCompatActivity() {
    lateinit var db: NoteDatabaseHelper
    private var chapterId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_stories_edit_chapter)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toBack = findViewById<ImageView>(R.id.btnBack)
        val btnChapterUpdate = findViewById<Button>(R.id.btnChapterUpdate)
        val btnChapterDiscard = findViewById<Button>(R.id.btnChapterDiscard)
        val etChapterName = findViewById<EditText>(R.id.etChapterName)
        val etChapterContent = findViewById<EditText>(R.id.etChapterContent)

        db = NoteDatabaseHelper(this)

        chapterId = intent.getIntExtra("chapter_id", -1)
        if (chapterId == -1) {
            finish()
            return
        }

        val chapter = db.getChapterByID(chapterId)
        etChapterName.setText(chapter.title)
        etChapterContent.setText(chapter.content)

        toBack.setOnClickListener {
            val intent = Intent(this, Stories_Chapters::class.java)
            startActivity(intent)
        }
        btnChapterUpdate.setOnClickListener {
            val newTitle = etChapterName.text.toString()
            val newContent = etChapterContent.text.toString()
            val updateChapter = Chapter(chapterId, newTitle, newContent)
            db.updateChapter(updateChapter)
            finish()
            Toast.makeText(this, "Changes Saved", Toast.LENGTH_SHORT).show()
        }
        btnChapterDiscard.setOnClickListener {
            db.deleteChapter(chapterId)
            finish()
            Toast.makeText(this, "Chapter Deleted", Toast.LENGTH_SHORT).show()
        }
    }
}