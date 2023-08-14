package uz.gita.noteapppractika.presentation.screen.trash.viewmodel.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import uz.gita.noteapppractika.data.model.NoteData
import uz.gita.noteapppractika.domain.repository.AppRepository
import uz.gita.noteapppractika.domain.repository.impl.AppRepositoryImpl
import uz.gita.noteapppractika.presentation.screen.trash.viewmodel.TrashViewModel

class TrashViewModelImpl: ViewModel(), TrashViewModel {

    private val repository : AppRepository = AppRepositoryImpl.getInstance()

    override val trashNotesLiveData: LiveData<List<NoteData>> =
        repository.getNotesInTrash()

    override fun empty_trash() {
        repository.deleteTrashNotes()
    }

    override fun retrieve_note(note: NoteData) {
        repository.retrieveNote(note)
    }

    override fun delete(id: Int) {
        repository.delete(id)
    }


}