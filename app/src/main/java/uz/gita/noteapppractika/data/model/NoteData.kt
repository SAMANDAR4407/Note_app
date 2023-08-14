package uz.gita.noteapppractika.data.model

import androidx.room.ColumnInfo
import uz.gita.noteapppractika.data.source.local.entity.NoteEntity
import java.util.*

data class NoteData(
    val id: Int = 0,
    val title: String,
    val content: String,
    @ColumnInfo(name = "created_at")
    val createdAt: String,
    @ColumnInfo(name = "on_trash")
    val onTrash: Int = 0,
    @ColumnInfo(name = "on_star")
    val onStar: Int = 0
): java.io.Serializable {
    fun toNoteEntity(): NoteEntity = NoteEntity(
        id, title, content, createdAt, onTrash, onStar
    )
}
