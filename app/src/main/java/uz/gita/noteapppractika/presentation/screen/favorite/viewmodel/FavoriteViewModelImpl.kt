package uz.gita.noteapppractika.presentation.screen.favorite.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import uz.gita.noteapppractika.data.model.NoteData
import uz.gita.noteapppractika.domain.repository.impl.AppRepositoryImpl

class FavoriteViewModelImpl: ViewModel(), FavoriteViewModel {

    private val repository = AppRepositoryImpl.getInstance()
    override val starNotesLiveData: LiveData<List<NoteData>> =
        repository.getFavoriteNotes()

    override fun unsave_note(note: NoteData) {
        repository.unSaveNote(note)
    }
}