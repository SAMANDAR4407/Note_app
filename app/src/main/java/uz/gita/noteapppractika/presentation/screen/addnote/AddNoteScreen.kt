package uz.gita.noteapppractika.presentation.screen.addnote

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import jp.wasabeef.richeditor.RichEditor
import uz.gita.noteapppractika.presentation.screen.addnote.viewmodel.AddNoteViewModel
import uz.gita.noteapppractika.presentation.screen.addnote.viewmodel.impl.AddNoteViewModelImpl
import uz.gita.noteapppractika.utils.myApply
import uz.gita.noteapppractika.R
import uz.gita.noteapppractika.data.model.NoteData
import uz.gita.noteapppractika.data.source.local.converter.DateConverter
import uz.gita.noteapppractika.presentation.BaseFragment

class AddNoteScreen : BaseFragment(){
    override var inEditNote: Boolean = false

    private lateinit var editor: RichEditor
    private val args : AddNoteScreenArgs by navArgs()
    private var updateNoteId = 0

    private val viewModel: AddNoteViewModel by viewModels<AddNoteViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (args.note != null){
            inEditNote = args.action
            updateNoteId = args.note!!.id
        }
    }

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.myApply {

        var bool1 = false
        var bool2 = false
        var bool3 = false
        var bool4 = false
        var bool5 = false
        var bool6 = false
        var bool7 = false
        var bool8 = false
        var bool9 = false

        activity?.addMenuProvider(this@AddNoteScreen, viewLifecycleOwner, Lifecycle.State.RESUMED)
        editor = binding.richEditor
        editor.setPlaceholder("Enter your note here...")
        editor.setPadding(5, 10, 5, 8)

        if (inEditNote) (requireActivity() as AppCompatActivity).supportActionBar?.title = "Edit Note"
        else (requireActivity() as AppCompatActivity).supportActionBar?.title = "Add Note"

        binding.myApply{
            etTitle.setText(args.note?.title ?: "")
            editor.html = args.note?.content ?: ""
        }

        actionBold.setOnClickListener {
            bool1 = !bool1
            if (bool1) {
                actionBold.setBackgroundResource(R.color.custom)
                editor.setBold()
            }
            else {
                actionBold.setBackgroundResource(R.color.custom1)
                editor.setBold()
            }
        }

        actionItalic.setOnClickListener {
            bool2 = !bool2
            if (bool2) {
                actionItalic.setBackgroundResource(R.color.custom)
                editor.setItalic()
            }
            else {
                actionItalic.setBackgroundResource(R.color.custom1)
                editor.setItalic()
            }
        }

        actionIndent.setOnClickListener {
            editor.setIndent()
        }

        actionOutdent.setOnClickListener {
            editor.setOutdent()
        }

        actionUnderline.setOnClickListener {
            bool3 = !bool3
            if (bool3) {
                actionUnderline.setBackgroundResource(R.color.custom)
                editor.setUnderline()
            }
            else {
                actionUnderline.setBackgroundResource(R.color.custom1)
                editor.setUnderline()
            }
        }

        actionStrikethrough.setOnClickListener {
            bool4 = !bool4
            if (bool4) {
                actionStrikethrough.setBackgroundResource(R.color.custom)
                editor.setStrikeThrough()
            }
            else {
                actionStrikethrough.setBackgroundResource(R.color.custom1)
                editor.setStrikeThrough()
            }
        }

        actionAlignLeft.setOnClickListener {
            editor.setAlignLeft()
            bool5 = !bool5
            if (bool5) {
                bool6 = false
                bool7 = false
                actionAlignLeft.setBackgroundResource(R.color.custom)
                actionAlignCenter.setBackgroundResource(R.color.custom1)
                actionAlignRight.setBackgroundResource(R.color.custom1)
            }
            else actionAlignLeft.setBackgroundResource(R.color.custom1)
        }

        actionAlignCenter.setOnClickListener {
            editor.setAlignCenter()
            bool6 = !bool6
            if (bool6) {
                bool5 = false
                bool7 = false
                actionAlignCenter.setBackgroundResource(R.color.custom)
                actionAlignLeft.setBackgroundResource(R.color.custom1)
                actionAlignRight.setBackgroundResource(R.color.custom1)
            }
            else {
                actionAlignCenter.setBackgroundResource(R.color.custom1)
                editor.setAlignLeft()
            }
        }

        actionAlignRight.setOnClickListener {
            editor.setAlignRight()
            bool7 = !bool7
            if (bool7) {
                bool5 = false
                bool6 = false
                actionAlignRight.setBackgroundResource(R.color.custom)
                actionAlignCenter.setBackgroundResource(R.color.custom1)
                actionAlignLeft.setBackgroundResource(R.color.custom1)
            }
            else {
                actionAlignRight.setBackgroundResource(R.color.custom1)
                editor.setAlignLeft()
            }
        }

        actionInsertBullets.setOnClickListener {
            editor.setBullets()
            bool8 = !bool8
            if (bool8) {
                bool9 = false
                actionInsertBullets.setBackgroundResource(R.color.custom)
                actionInsertNumbers.setBackgroundResource(R.color.custom1)
            }
            else actionInsertBullets.setBackgroundResource(R.color.custom1)
        }

        actionInsertNumbers.setOnClickListener {
            editor.setNumbers()
            bool9 = !bool9
            if (bool9) {
                bool8 = false
                actionInsertNumbers.setBackgroundResource(R.color.custom)
                actionInsertBullets.setBackgroundResource(R.color.custom1)
            }
            else actionInsertNumbers.setBackgroundResource(R.color.custom1)
        }

    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId) {
            R.id.action_undo -> {
                editor.undo()
                true
            }
            R.id.action_redo -> {
                editor.redo()
                true
            }
            R.id.submit -> {
                val title = binding.etTitle.text.toString()
                val content = editor.html.toString()
                val date = DateConverter.getCurrentTime()

                if (binding.etTitle.text.isNotBlank() && editor.html != null) {
                    if (inEditNote){
                        viewModel.update(NoteData(updateNoteId,title,content,date))
                        Toast.makeText(requireContext(), "Updating Success", Toast.LENGTH_SHORT).show()
                        findNavController().navigateUp()
                    } else {
                        viewModel.addNote(NoteData(0, binding.etTitle.text.toString(), editor.html, DateConverter.getCurrentTime()))
                        findNavController().navigateUp()
                        Toast.makeText(requireContext(), "Adding Success", Toast.LENGTH_SHORT).show()
                    }
                } else Toast.makeText(requireContext(), "Please, provide note title", Toast.LENGTH_SHORT).show()
                true
            }
            else -> {
                findNavController().navigateUp()
                true
            }
        }
    }
}