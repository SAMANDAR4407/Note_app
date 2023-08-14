package uz.gita.noteapppractika.presentation.screen.trash

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.noteapppractika.R
import uz.gita.noteapppractika.databinding.ScreenTrashBinding
import uz.gita.noteapppractika.presentation.adapter.TrashAdapter
import uz.gita.noteapppractika.presentation.screen.dialog.EventDialogDelete
import uz.gita.noteapppractika.presentation.screen.dialog.EventDialogRetrieve
import uz.gita.noteapppractika.presentation.screen.trash.viewmodel.TrashViewModel
import uz.gita.noteapppractika.presentation.screen.trash.viewmodel.impl.TrashViewModelImpl
import uz.gita.noteapppractika.utils.myApply

class TrashScreen : Fragment(R.layout.screen_trash) {

    private val viewModel : TrashViewModel by viewModels<TrashViewModelImpl>()
    private val binding by viewBinding(ScreenTrashBinding::bind)
    private val adapter = TrashAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.recyclerView.adapter = adapter

        adapter.setLongListener { data ->
            val bottomDialog = EventDialogRetrieve()
            bottomDialog.setRetrieveListener {
                viewModel.retrieve_note(data)
                bottomDialog.dismiss()
            }
            bottomDialog.setDeleteListener {
                viewModel.delete(data.id)
                bottomDialog.dismiss()
            }
            bottomDialog.show(parentFragmentManager, "")
        }

        binding.myApply{
            requireActivity().addMenuProvider(object : MenuProvider {
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menuInflater.inflate(R.menu.trash_menu, menu)
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    return when (menuItem.itemId) {
                        R.id.clear_notes -> {
                            viewModel.empty_trash()
                            true
                        }
                        else -> {
                            false
                        }
                    }
                }
            }, viewLifecycleOwner)
        }

        viewModel.trashNotesLiveData.observe(viewLifecycleOwner){
            if (it.isEmpty()){
                binding.placeholder.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
            }
            else {
                binding.placeholder.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
                adapter.submitList(it)
            }
        }
    }
}