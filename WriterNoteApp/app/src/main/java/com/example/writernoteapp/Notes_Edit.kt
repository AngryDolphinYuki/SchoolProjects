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

class Notes_Edit : AppCompatActivity() {

    lateinit var db: NoteDatabaseHelper
    private var noteId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_notes_edit)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toBack = findViewById<ImageView>(R.id.btnBack)
        val btnSave = findViewById<Button>(R.id.btnUpdateSave)
        val btnDiscard = findViewById<Button>(R.id.btnDiscard)
        val noteTitle = findViewById<EditText>(R.id.etNoteTitle)
        val noteContent = findViewById<EditText>(R.id.etNoteContent)

        db = NoteDatabaseHelper(this)

        noteId = intent.getIntExtra("note_id", -1)
        if (noteId == -1) {
            finish()
            return
        }

        val note = db.getNoteByID(noteId)
        noteTitle.setText(note.title)
        noteContent.setText(note.content)

        toBack.setOnClickListener {
            val intent = Intent(this, Notes::class.java)
            startActivity(intent)
        }
        btnSave.setOnClickListener {
            val newTitle = noteTitle.text.toString()
            val newContent = noteContent.text.toString()
            val updateNote = Note(noteId, newTitle, newContent)
            db.updateNote(updateNote)
            finish()
            Toast.makeText(this, "Changes Saved", Toast.LENGTH_SHORT).show()
        }
        btnDiscard.setOnClickListener {
            db.deleteNote(noteId)
            finish()
            Toast.makeText(this, "Note Deleted", Toast.LENGTH_SHORT).show()
        }
    }
}