package uz.gita.noteapppractika.presentation.screen.home

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.noteapppractika.R
import uz.gita.noteapppractika.databinding.ScreenHomeBinding
import uz.gita.noteapppractika.presentation.adapter.HomeAdapter
import uz.gita.noteapppractika.presentation.screen.dialog.EventDialogDelete
import uz.gita.noteapppractika.presentation.screen.home.viewmodel.HomeViewModel
import uz.gita.noteapppractika.presentation.screen.home.viewmodel.impl.HomeViewModelImpl
import uz.gita.noteapppractika.utils.myApply

@RequiresApi(Build.VERSION_CODES.O)
class HomeScreen : Fragment(R.layout.screen_home) {
    private val binding by viewBinding(ScreenHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels<HomeViewModelImpl>()
    private val adapter by lazy{HomeAdapter()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.openAddNoteScreenLiveData.observe(this, openAddNoteObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.myApply {
        addNoteBtn.setOnClickListener {
            viewModel.openAddNoteScreen()
        }
        adapter.setUpdateListener {
            findNavController().navigate(HomeScreenDirections.actionHomeScreenToAddNoteScreen(it, true))
        }

        recyclerView.adapter = adapter


        adapter.setListener {
            val bottomDialog = EventDialogDelete()
            bottomDialog.setClickDeleteButtonListener {
                viewModel.deleteNote(it)
                bottomDialog.dismiss()
            }
            bottomDialog.setClickStarButtonListener {
                viewModel.saveNote(it)
                bottomDialog.dismiss()
            }
            bottomDialog.show(parentFragmentManager, "")
        }

        viewModel.notesLiveData.observe(viewLifecycleOwner){
            if (it.isEmpty()){
                placeholder.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            }else{
                placeholder.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
                Log.d("TTT", "list: $it")
                adapter.submitList(it)
            }
        }

        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null && newText.trim().isNotEmpty()) {
                    viewModel.search("%$newText%").observe(viewLifecycleOwner) {
                        if (it.isEmpty() && newText.isNotEmpty()) {
                            placeholder.visibility = View.VISIBLE
                        } else {
                            if (viewModel.notesLiveData.value?.isEmpty() == true) {
                                placeholder.visibility = View.VISIBLE
                            }
                        }
                        adapter.submitList(it)
                    }
                    return true
                } else {
                    viewModel.getListOfNotes()
                    return false
                }
            }
        })

    }

    private val openAddNoteObserver = Observer<Unit>{
        findNavController().navigate(HomeScreenDirections.actionHomeScreenToAddNoteScreen(null, false))
    }
}