package com.example.writernoteapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class NoteDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "writernotes.db"
        private const val DATABASE_VERSION = 4

        private const val TABLE_NAME = "notes"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_CONTENT = "content"

        private const val TABLE_NAME2 = "characters"
        private const val COLUMN_ID2 = "character_id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_ROLE = "role"
        private const val COLUMN_HEAD = "head"
        private const val COLUMN_TORSO = "torso"
        private const val COLUMN_BOTTOM = "bottom"
        private const val COLUMN_NICKNAME = "nickname"
        private const val COLUMN_PRONOUNS = "pronouns"
        private const val COLUMN_AGE = "age"
        private const val COLUMN_RACE = "race"
        private const val COLUMN_HEIGHT = "height"
        private const val COLUMN_DOMHAND = "dominant_hand"
        private const val COLUMN_STATUS = "status"
        private const val COLUMN_FIRSTSEEN = "first_seen"
        private const val COLUMN_PERSONALITY = "personality"
        private const val COLUMN_PAST = "past"
        private const val COLUMN_FUTURE = "future"
        private const val COLUMN_ADDITIONALNOTES = "additional_notes"

        private const val TABLE_NAME4 = "chapters"
        private const val COLUMN_ID4 = "chapter_id"
        private const val COLUMN_NAME3 = "chapter_name"
        private const val COLUMN_CHAPTERCONTENT = "chapter_content"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_TITLE TEXT, $COLUMN_CONTENT TEXT)"
        val createTableQuery2 = "CREATE TABLE $TABLE_NAME2 ($COLUMN_ID2 INTEGER PRIMARY KEY, $COLUMN_NAME TEXT, $COLUMN_ROLE TEXT, $COLUMN_HEAD INTEGER, $COLUMN_TORSO INTEGER, $COLUMN_BOTTOM INTEGER, $COLUMN_NICKNAME TEXT, $COLUMN_PRONOUNS TEXT, $COLUMN_AGE TEXT, $COLUMN_RACE TEXT, $COLUMN_HEIGHT TEXT, $COLUMN_DOMHAND TEXT, $COLUMN_STATUS TEXT, $COLUMN_FIRSTSEEN TEXT, $COLUMN_PERSONALITY TEXT, $COLUMN_PAST TEXT, $COLUMN_FUTURE TEXT, $COLUMN_ADDITIONALNOTES TEXT)"
        val createTableQuery4 = "CREATE TABLE $TABLE_NAME4 ($COLUMN_ID4 INTEGER PRIMARY KEY, $COLUMN_NAME3 TEXT, $COLUMN_CHAPTERCONTENT TEXT)"
        db?.execSQL(createTableQuery)
        db?.execSQL(createTableQuery2)
        db?.execSQL(createTableQuery4)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        val dropTableQuery2 = "DROP TABLE IF EXISTS $TABLE_NAME2"
        val dropTableQuery4 = "DROP TABLE IF EXISTS $TABLE_NAME4"
        db?.execSQL(dropTableQuery)
        db?.execSQL(dropTableQuery2)
        db?.execSQL(dropTableQuery4)
        onCreate(db)
    }

    fun insertCharacter(character: Character) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, character.name)
            put(COLUMN_ROLE, character.role)
            put(COLUMN_HEAD, character.head)
            put(COLUMN_TORSO, character.torso)
            put(COLUMN_BOTTOM, character.bottom)
            put(COLUMN_NICKNAME, character.nickname)
            put(COLUMN_PRONOUNS, character.pronouns)
            put(COLUMN_AGE, character.age)
            put(COLUMN_RACE, character.race)
            put(COLUMN_HEIGHT, character.height)
            put(COLUMN_DOMHAND, character.domHand)
            put(COLUMN_STATUS, character.status)
            put(COLUMN_FIRSTSEEN, character.firstSeen)
            put(COLUMN_PERSONALITY, character.personality)
            put(COLUMN_PAST, character.past)
            put(COLUMN_FUTURE, character.future)
            put(COLUMN_ADDITIONALNOTES, character.additionalNotes)
        }
        db.insert(TABLE_NAME2, null, values)
        db.close()
    }

    fun bringCharacterDetails(): List<Character> {
        val characterList = mutableListOf<Character>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME2"
        val cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID2))
            val characterName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
            val characterRole = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ROLE))
            val characterHead = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_HEAD))
            val characterTorso = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_TORSO))
            val characterBottom = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_BOTTOM))
            val characterNickname = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BOTTOM))
            val characterPronouns = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BOTTOM))
            val characterAge = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BOTTOM))
            val characterRace = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RACE))
            val characterHeight = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BOTTOM))
            val characterDomhand = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BOTTOM))
            val characterStatus = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BOTTOM))
            val characterFirstSeen = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BOTTOM))
            val characterPersonality = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BOTTOM))
            val characterPast = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BOTTOM))
            val characterFuture = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BOTTOM))
            val characterAdditionalNotes = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BOTTOM))

            val character =  Character(
                id,
                characterName,
                characterRole,
                characterHead,
                characterTorso,
                characterBottom,
                characterNickname,
                characterPronouns,
                characterAge,
                characterRace,
                characterHeight,
                characterDomhand,
                characterStatus,
                characterFirstSeen,
                characterPersonality,
                characterPast,
                characterFuture,
                characterAdditionalNotes
            )
            characterList.add(character)
        }
        cursor.close()
        db.close()
        return characterList
    }
    fun bringCharacterFirstLooks(): List<CharacterShow> {
        val characterList = mutableListOf<CharacterShow>()
        val db = readableDatabase
        val query = "SELECT $COLUMN_ID2, $COLUMN_NAME, $COLUMN_ROLE, $COLUMN_RACE, $COLUMN_HEAD, $COLUMN_TORSO, $COLUMN_BOTTOM FROM $TABLE_NAME2"
        val cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID2))
            val characterName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
            val characterRole = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ROLE))
            val characterRace = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RACE))
            val characterHead = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_HEAD))
            val characterTorso = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_TORSO))
            val characterBottom = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_BOTTOM))

            val character = CharacterShow(id, characterName, characterRole, characterRace, characterHead.toString().toInt(), characterTorso.toString().toInt(), characterBottom.toString().toInt())
            characterList.add(character)
        }
        cursor.close()
        db.close()
        return characterList
    }

    fun updateCharacter(character: Character) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, character.name)
            put(COLUMN_ROLE, character.role)
            put(COLUMN_HEAD, character.head)
            put(COLUMN_TORSO, character.torso)
            put(COLUMN_BOTTOM, character.bottom)
            put(COLUMN_NICKNAME, character.nickname)
            put(COLUMN_PRONOUNS, character.pronouns)
            put(COLUMN_AGE, character.age)
            put(COLUMN_RACE, character.race)
            put(COLUMN_HEIGHT, character.height)
            put(COLUMN_DOMHAND, character.domHand)
            put(COLUMN_STATUS, character.status)
            put(COLUMN_FIRSTSEEN, character.firstSeen)
            put(COLUMN_PERSONALITY, character.personality)
            put(COLUMN_PAST, character.past)
            put(COLUMN_FUTURE, character.future)
            put(COLUMN_ADDITIONALNOTES, character.additionalNotes)
        }
        val whereClause = "$COLUMN_ID2 = ?"
        val whereArgs = arrayOf(character.id.toString())
        db.update(TABLE_NAME2, values, whereClause, whereArgs)
        db.close()
    }

    fun getCharacterByID(characterId: Int): Character {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME2 WHERE $COLUMN_ID2 = $characterId"
        val cursor = db.rawQuery(query, null)
        cursor.moveToFirst()

        val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID2))
        val characterName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
        val characterRole = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ROLE))
        val characterHead = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_HEAD))
        val characterTorso = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_TORSO))
        val characterBottom = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_BOTTOM))
        val characterNickname = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BOTTOM))
        val characterPronouns = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BOTTOM))
        val characterAge = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BOTTOM))
        val characterRace = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RACE))
        val characterHeight = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BOTTOM))
        val characterDomhand = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BOTTOM))
        val characterStatus = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BOTTOM))
        val characterFirstSeen = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BOTTOM))
        val characterPersonality = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BOTTOM))
        val characterPast = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BOTTOM))
        val characterFuture = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BOTTOM))
        val characterAdditionalNotes = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BOTTOM))

        cursor.close()
        db.close()
        return Character(
            id,
            characterName,
            characterRole,
            characterHead,
            characterTorso,
            characterBottom,
            characterNickname,
            characterPronouns,
            characterAge,
            characterRace,
            characterHeight,
            characterDomhand,
            characterStatus,
            characterFirstSeen,
            characterPersonality,
            characterPast,
            characterFuture,
            characterAdditionalNotes
        )
    }

    fun deleteCharacter(characterId: Int) {
        val db = writableDatabase
        val whereClause ="$COLUMN_ID2 = ?"
        val whereArgs = arrayOf(characterId.toString())
        db.delete(TABLE_NAME2, whereClause, whereArgs)
        db.close()
    }

    fun insertNote(note: Note) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, note.title)
            put(COLUMN_CONTENT, note.content)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun bringNotes(): List<Note> {
        val noteList = mutableListOf<Note>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
            val content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))

            val note = Note(id, title, content)
            noteList.add(note)
        }
        cursor.close()
        db.close()
        return noteList
    }

    fun updateNote(note: Note) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, note.title)
            put(COLUMN_CONTENT, note.content)
        }
        val whereClause = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(note.id.toString())
        db.update(TABLE_NAME, values, whereClause, whereArgs)
        db.close()
    }

    fun getNoteByID(noteID: Int): Note {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = $noteID"
        val cursor = db.rawQuery(query, null)
        cursor.moveToFirst()

        val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
        val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
        val content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))

        cursor.close()
        db.close()
        return Note(id, title, content)
    }

    fun deleteNote(noteId: Int) {
        val db = writableDatabase
        val whereClause = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(noteId.toString())
        db.delete(TABLE_NAME, whereClause, whereArgs)
        db.close()
    }


    fun insertChapter(chapter: Chapter) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME3, chapter.title)
            put(COLUMN_CHAPTERCONTENT, chapter.content)
        }
        db.insert(TABLE_NAME4, null, values)
        db.close()
    }

    fun bringChapters(): List<Chapter> {
        val chapterList = mutableListOf<Chapter>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME4"
        val cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID4))
            val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME3))
            val content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CHAPTERCONTENT))

            val chapter = Chapter(id, title, content)
            chapterList.add(chapter)
        }
        cursor.close()
        db.close()
        return chapterList
    }
    fun updateChapter(chapter: Chapter) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME3, chapter.title)
            put(COLUMN_CHAPTERCONTENT, chapter.content)
        }
        val whereClause = "$COLUMN_ID4 = ?"
        val whereArgs = arrayOf(chapter.id.toString())
        db.update(TABLE_NAME4, values, whereClause, whereArgs)
        db.close()
    }
    fun getChapterByID(chapterId: Int): Chapter {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME4 WHERE $COLUMN_ID4 = $chapterId"
        val cursor = db.rawQuery(query, null)
        cursor.moveToFirst()

        val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID4))
        val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME3))
        val content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CHAPTERCONTENT))

        cursor.close()
        db.close()
        return Chapter(id, title, content)
    }
    fun deleteChapter(chapterId: Int) {
        val db = writableDatabase
        val whereClause = "$COLUMN_ID4 = ?"
        val whereArgs = arrayOf(chapterId.toString())
        db.delete(TABLE_NAME4, whereClause, whereArgs)
        db.close()
    }
}