package uz.gita.noteapppractika.presentation.screen.home.viewmodel.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.noteapppractika.data.model.NoteData
import uz.gita.noteapppractika.domain.repository.AppRepository
import uz.gita.noteapppractika.domain.repository.impl.AppRepositoryImpl
import uz.gita.noteapppractika.presentation.screen.home.viewmodel.HomeViewModel

class HomeViewModelImpl :ViewModel(), HomeViewModel{
    private val repository: AppRepository = AppRepositoryImpl.getInstance()

    override val notesLiveData = MutableLiveData<List<NoteData>>()

    override val openAddNoteScreenLiveData = MutableLiveData<Unit>()

    init {
        getListOfNotes()
    }

    override fun openAddNoteScreen() {
        openAddNoteScreenLiveData.value = Unit
    }

    override fun getListOfNotes() {
        notesLiveData.value = repository.getNotes()
    }

    override fun deleteNote(note: NoteData) {
        repository.deleteNote(note)
        getListOfNotes()
    }

    override fun saveNote(note: NoteData) {
        repository.saveNote(note)
        getListOfNotes()
    }

    override fun search(title: String) : LiveData<List<NoteData>>{
        return repository.search(title)
    }
}