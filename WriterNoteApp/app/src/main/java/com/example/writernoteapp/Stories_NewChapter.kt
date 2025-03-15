package com.example.writernoteapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Stories_NewChapter : AppCompatActivity() {
    private lateinit var db: NoteDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_stories_new_chapter)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnSaveChapter = findViewById<Button>(R.id.btnSaveChapter)

        db = NoteDatabaseHelper(this)
        btnSaveChapter.setOnClickListener {
            val etNewChapterTitle = findViewById<EditText>(R.id.etNewChapterTitle).text.toString()
            val etNewChapterContent = findViewById<EditText>(R.id.etNewChapterContent).text.toString()
            val chapter = Chapter(0, etNewChapterTitle, etNewChapterContent)
            db.insertChapter(chapter)
            finish()
            Toast.makeText(this, "Chapter Saved", Toast.LENGTH_SHORT).show()
        }
    }
}