package uz.gita.noteapppractika.presentation.screen.addnote.viewmodel

import uz.gita.noteapppractika.data.model.NoteData

interface AddNoteViewModel {
    fun addNote(note : NoteData)
    fun update(note : NoteData)
}