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

class ChapterAdapter(private var chapters: List<Chapter>, context: Context) :
    RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder>() {

    private val db: NoteDatabaseHelper = NoteDatabaseHelper(context)

    class ChapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvChapterTitle: TextView = itemView.findViewById(R.id.tvChapterName)
        val tvChapterContent: TextView = itemView.findViewById(R.id.tvChapterContent)
        val ivDelete: ImageView = itemView.findViewById(R.id.ivdeletechapter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chapters, parent, false)
        return ChapterViewHolder(view)
    }

    override fun getItemCount(): Int = chapters.size

    override fun onBindViewHolder(holder: ChapterViewHolder, position: Int) {
        val chapter = chapters[position]
        holder.tvChapterTitle.text = chapter.title
        holder.tvChapterContent.text = chapter.content

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, Stories_EditChapter::class.java).apply {
                putExtra("chapter_id", chapter.id)
            }
            holder.itemView.context.startActivity(intent)
        }
        holder.ivDelete.setOnClickListener {
            db.deleteChapter(chapter.id)
            refreshData(db.bringChapters())
            Toast.makeText(holder.itemView.context, "Chapter Deleted", Toast.LENGTH_SHORT).show()
        }
    }

    fun refreshData(newChapter: List<Chapter>) {
        chapters = newChapter
        notifyDataSetChanged()
    }
}