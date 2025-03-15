package com.example.writernoteapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class CharacterAdapter(private var characters: List<CharacterShow>, context: Context) :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private val db: NoteDatabaseHelper = NoteDatabaseHelper(context)

    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val characterName: TextView = itemView.findViewById(R.id.tvcharacterName)
        val characterRole: TextView = itemView.findViewById(R.id.tvcharacterRole)
        val characterRace: TextView = itemView.findViewById(R.id.tvcharacterRace)
        val characterHead: ImageView = itemView.findViewById(R.id.ivcharacterHead)
        val characterTorso: ImageView = itemView.findViewById(R.id.ivcharacterTorso)
        val characterBottom: ImageView = itemView.findViewById(R.id.ivcharacterBottom)

        val ivdelete: ImageView = itemView.findViewById(R.id.ivdelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_characters, parent, false)
        return CharacterViewHolder(view)
    }

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characters[position]
        holder.characterName.text = character.name
        holder.characterRole.text = character.role
        holder.characterRace.text = character.race
        holder.characterHead.setImageResource(character.head)
        holder.characterTorso.setImageResource(character.torso)
        holder.characterBottom.setImageResource(character.bottom)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, Characters_Edit::class.java).apply {
                putExtra("character_id", character.id)
            }
            holder.itemView.context.startActivity(intent)
        }
        holder.ivdelete.setOnClickListener {
            db.deleteCharacter(character.id)
            refreshData(db.bringCharacterFirstLooks())
            Toast.makeText(holder.itemView.context, "Character Deleted", Toast.LENGTH_SHORT).show()
        }
    }
    fun refreshData(newCharacters: List<CharacterShow>) {
        characters =  newCharacters
        notifyDataSetChanged()
    }
}