package uz.gita.noteapppractika.presentation.screen.favorite.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.noteapppractika.data.model.NoteData

interface FavoriteViewModel {

    val starNotesLiveData: LiveData<List<NoteData>>

    fun unsave_note(note: NoteData)
}