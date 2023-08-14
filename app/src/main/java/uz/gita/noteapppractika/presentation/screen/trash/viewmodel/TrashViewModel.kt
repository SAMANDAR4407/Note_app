package uz.gita.noteapppractika.presentation.screen.trash.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.noteapppractika.data.model.NoteData

interface TrashViewModel {

    val trashNotesLiveData: LiveData<List<NoteData>>

    fun empty_trash()
    fun retrieve_note(note: NoteData)
    fun delete(id:Int)
}