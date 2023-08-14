package uz.gita.noteapppractika.domain.repository.impl

import androidx.lifecycle.LiveData
import uz.gita.noteapppractika.data.model.NoteData
import uz.gita.noteapppractika.data.source.local.NoteDatabase
import uz.gita.noteapppractika.domain.repository.AppRepository

class AppRepositoryImpl private constructor(): AppRepository {

    companion object{
        private lateinit var repository: AppRepositoryImpl

        fun getInstance(): AppRepositoryImpl {
            if (!(Companion::repository.isInitialized)){
                repository = AppRepositoryImpl()
            }
            return repository
        }
    }

    private val noteDao = NoteDatabase.getInstance().getNoteDao()

    override fun addNote(note: NoteData) {
        noteDao.addNote(note.toNoteEntity())
    }

    override fun updateNote(note: NoteData) {
        noteDao.updateNote(note.toNoteEntity())
    }

    override fun deleteNote(note: NoteData) {
        noteDao.deleteNote(note.id)
    }

    override fun retrieveNote(note: NoteData) {
        noteDao.retrieveNote(note.id)
    }

    override fun saveNote(note: NoteData) {
        noteDao.starNote(note.id)
    }

    override fun unSaveNote(note: NoteData) {
        noteDao.unStarNote(note.id)
    }

    override fun deleteTrashNotes() {
        noteDao.deleteNotesCompletely()
    }

    override fun getNotes(): List<NoteData> {
        return noteDao.getNotes()
    }

    override fun getNotesInTrash(): LiveData<List<NoteData>> {
        return noteDao.getNotesInTrash()
    }

    override fun getFavoriteNotes(): LiveData<List<NoteData>> {
        return noteDao.getFavoriteNotes()
    }

    override fun search(title: String): LiveData<List<NoteData>> {
        return noteDao.searchNote(title)
    }

    override fun delete(id: Int) {
        noteDao.deleteNoteCompletely(id)
    }
}