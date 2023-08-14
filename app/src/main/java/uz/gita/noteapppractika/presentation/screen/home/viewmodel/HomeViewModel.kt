package uz.gita.noteapppractika.presentation.screen.home.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.noteapppractika.data.model.NoteData

interface HomeViewModel {
    val notesLiveData: LiveData<List<NoteData>>
    val openAddNoteScreenLiveData: LiveData<Unit>

    fun openAddNoteScreen()
    fun getListOfNotes()
    fun deleteNote(note: NoteData)
    fun saveNote(note: NoteData)
    fun search(title: String) : LiveData<List<NoteData>>
}