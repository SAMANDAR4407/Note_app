package uz.gita.noteapppractika.domain.repository

import androidx.lifecycle.LiveData
import uz.gita.noteapppractika.data.model.NoteData

interface AppRepository {

    fun addNote(note : NoteData)
    fun updateNote(note : NoteData)
    fun deleteNote(note : NoteData)
    fun retrieveNote(note : NoteData)
    fun saveNote(note : NoteData)
    fun unSaveNote(note : NoteData)
    fun deleteTrashNotes()
    fun getNotes() : List<NoteData>
    fun getNotesInTrash() : LiveData<List<NoteData>>
    fun getFavoriteNotes() : LiveData<List<NoteData>>
    fun search(title: String) : LiveData<List<NoteData>>
    fun delete(id: Int)
}