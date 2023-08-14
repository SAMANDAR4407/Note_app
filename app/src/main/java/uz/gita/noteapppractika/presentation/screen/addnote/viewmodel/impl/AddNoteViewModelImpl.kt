package uz.gita.noteapppractika.presentation.screen.addnote.viewmodel.impl

import androidx.lifecycle.ViewModel
import uz.gita.noteapppractika.data.model.NoteData
import uz.gita.noteapppractika.domain.repository.AppRepository
import uz.gita.noteapppractika.domain.repository.impl.AppRepositoryImpl
import uz.gita.noteapppractika.presentation.screen.addnote.viewmodel.AddNoteViewModel

class AddNoteViewModelImpl : ViewModel(), AddNoteViewModel {
    private val repository: AppRepository = AppRepositoryImpl.getInstance()
    override fun addNote(note : NoteData) {
        repository.addNote(note)
    }

    override fun update(note: NoteData) {
        repository.updateNote(note)
    }
}