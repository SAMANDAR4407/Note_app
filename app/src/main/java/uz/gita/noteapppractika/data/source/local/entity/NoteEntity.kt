package uz.gita.noteapppractika.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.gita.noteapppractika.data.model.NoteData
import java.util.*

@Entity(tableName = "Notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val content: String,
    @ColumnInfo(name = "created_at")
    val createdAt: String,
    @ColumnInfo(name = "on_trash")
    val onTrash: Int = 0,
    @ColumnInfo(name = "on_star")
    val onStar: Int = 0
) {
    fun toNoteData() = NoteData(
        id, title, content, createdAt, onTrash, onStar
    )
}