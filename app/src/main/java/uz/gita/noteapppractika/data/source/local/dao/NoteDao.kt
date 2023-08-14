package uz.gita.noteapppractika.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import uz.gita.noteapppractika.data.model.NoteData
import uz.gita.noteapppractika.data.source.local.entity.NoteEntity

@Dao
interface NoteDao {

    @Insert
    fun addNote(note: NoteEntity)

    @Update
    fun updateNote(note: NoteEntity)

    @Query("SELECT * FROM Notes WHERE on_trash=0 and on_star=0")
    fun getNotes(): List<NoteData>

    @Query("SELECT * FROM Notes WHERE on_trash=1")
    fun getNotesInTrash(): LiveData<List<NoteData>>

    @Query("SELECT * FROM Notes WHERE on_star=1")
    fun getFavoriteNotes(): LiveData<List<NoteData>>

    @Query("delete from Notes where on_trash=1")
    fun deleteNotesCompletely()

    @Query("delete from Notes where on_trash=1 and id = :id")
    fun deleteNoteCompletely(id: Int)

    @Query("update Notes set on_trash=1, on_star=0 where id = :id")
    fun deleteNote(id: Int)

    @Query("select * from Notes where on_trash=0 and title like :title")
    fun searchNote(title: String) : LiveData<List<NoteData>>

    @Query("update Notes set on_star=1, on_trash=0 where id = :id")
    fun starNote(id: Int)

    @Query("update Notes set on_trash=0 where id = :id")
    fun retrieveNote(id: Int)

    @Query("update Notes set on_star=0 where id = :id")
    fun unStarNote(id: Int)
}