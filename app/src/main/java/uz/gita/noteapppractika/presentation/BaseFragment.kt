package uz.gita.noteapppractika.presentation

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.noteapppractika.BuildConfig
import uz.gita.noteapppractika.R
import uz.gita.noteapppractika.databinding.ScreenAddNoteBinding

abstract class BaseFragment : Fragment(), MenuProvider {
    abstract var inEditNote: Boolean

    protected val binding by viewBinding(ScreenAddNoteBinding::bind)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.screen_add_note, container, false)


    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        if(inEditNote) {
            // EditNote uchun menu
            menu.clear()
            menuInflater.inflate(R.menu.edit_note_menu, menu)
        } else {
            // AddNote uchun menu
            menu.clear()
            menuInflater.inflate(R.menu.add_note_menu, menu)
        }
    }
}