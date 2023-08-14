package uz.gita.noteapppractika.app

import android.app.Application
import uz.gita.noteapppractika.data.source.local.NoteDatabase

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        NoteDatabase.init(this)
    }
}